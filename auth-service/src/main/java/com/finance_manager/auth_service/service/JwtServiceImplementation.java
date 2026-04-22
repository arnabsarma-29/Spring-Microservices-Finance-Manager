package com.finance_manager.auth_service.service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.finance_manager.auth_service.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class JwtServiceImplementation implements JwtService
{
	private final JwtConfig jwtConfig;
	@Override
	public String extractUsername (String token)
	{
		return extractClaim (token, Claims :: getSubject);
	}
	@Override
	public <T> T extractClaim (String token, Function <Claims, T> claimsResolver)
	{
		final Claims claims = extractAllClaims (token);
		return claimsResolver.apply (claims);
	}
	@Override
	public String generateToken (UserDetails userDetails)
	{
		return generateToken (new HashMap <> (), userDetails);
	}
	@Override
	public String generateToken (Map <String, Object> extraClaims, UserDetails userDetails)
	{
		return Jwts.builder ().claims (extraClaims).subject (userDetails.getUsername ()).issuedAt (new Date( System.currentTimeMillis ())).expiration (new Date (System.currentTimeMillis () + jwtConfig.getExpiration ())).signWith (getSignInKey ()).compact ();
	}
	@Override
	public boolean isTokenValid (String token, UserDetails userDetails)
	{
		final String username = extractUsername(token);
		return (username.equals (userDetails.getUsername ())) && !isTokenExpired (token);
	}
	private boolean isTokenExpired (String token)
	{
		return extractExpiration (token).before (new Date ());
	}
	private Date extractExpiration (String token)
	{
		return extractClaim (token, Claims::getExpiration);
	}
	private Claims extractAllClaims (String token)
	{
		return Jwts.parser ().verifyWith ((javax.crypto.SecretKey) getSignInKey ()).build ().parseSignedClaims (token).getPayload ();
	}
	private Key getSignInKey ()
	{
		byte [] keyBytes = Decoders.BASE64.decode (jwtConfig.getSecretKey ());
		return Keys.hmacShaKeyFor (keyBytes);
	}
}