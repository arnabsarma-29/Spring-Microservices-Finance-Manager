package com.finance_manager.service;
import java.util.UUID;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
public interface JwtService
{
	UUID extractUserId (String token);
	String extractEmail (String token);
	<T> T extractClaim (String token, Function <Claims, T> claimsResolver);
	String generateToken (UUID userId, String email);
	boolean isTokenValid (String token);
}