package com.finance_manager.service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import org.springframework.stereotype.Service;
import com.finance_manager.config.JwtConfig;
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
	public UUID extractUserId (String token)
	{
		String userIdString = extractClaim (token, claims -> claims.get ("userId", String.class));
		return userIdString != null ? UUID.fromString(userIdString) : null;
	}
	@Override
	public String extractEmail (String token)
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
	public String generateToken (UUID userId, String email)
	{
		Map <String, Object> extraClaims = new HashMap <> ();
		extraClaims.put ("userId", userId.toString ());
		return Jwts.builder ().claims (extraClaims).subject (email).issuedAt (new Date (System.currentTimeMillis ())).expiration (new Date (System.currentTimeMillis () + jwtConfig.getExpiration ())).signWith (getSignInKey ()).compact ();
	}
	@Override
	public boolean isTokenValid (String token)
	{
		return !isTokenExpired (token);
	}
	private boolean isTokenExpired (String token)
	{
		return extractExpiration (token).before (new Date ());
	}
	private Date extractExpiration (String token)
	{
		return extractClaim (token, Claims :: getExpiration);
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