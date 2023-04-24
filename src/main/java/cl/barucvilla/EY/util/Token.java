package cl.barucvilla.EY.util;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Token {

	private static final String SECRET_KEY = "a2OznK0ZwP8fVh14Og1+GYJmKjR1EJvYXHdddsetttdlCfKm7ZlJW2tB3zqK5yN+5yD1GJ8hXbXcADeM7Tx+Dmcw5QrObQ==";
	private static final long EXPIRATION_TIME = 86400000;

	public static String generateToken(String email) {
		Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);

		@SuppressWarnings("deprecation")
		String token = Jwts.builder().setSubject(email).setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

		return token;
	}
}