package jwt;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import customer.Credentials;

public class JwtHelper {

	private static JwtHelper jwtHelper;
	private static final long EXPIRATION_LIMIT = 7; // Temps de validité du token
	private static final Algorithm algorithm = generateRSA256();

	private JwtHelper() {
	}

	public static JwtHelper getInstance() {
		if (jwtHelper == null) {
			jwtHelper = new JwtHelper();
		}
		return jwtHelper;
	}

	public static String generateToken(Credentials credentials) {
		return JWT.create().withSubject("MY APP")
				.withAudience("MY COMPANY")
				.withIssuer("https://mondomaine.com")
				.withIssuedAt(new Date(System.currentTimeMillis()))
				.withClaim("login", credentials.getLogin())
				.withExpiresAt(getExpirationDate())
				.sign(algorithm);
	}

	private static Date getExpirationDate() {
		long currentTimeInMillis = System.currentTimeMillis();
		long expMilliSeconds = TimeUnit.DAYS.toMillis(EXPIRATION_LIMIT);
		return new Date(currentTimeInMillis + expMilliSeconds);
	}

	public static void checkJwtValidity(String token) throws JWTVerificationException {
		JWTVerifier verifier = JWT.require(algorithm).withIssuer("https://mondomaine.com").build();
		DecodedJWT jwt = verifier.verify(token);
		Date expiresAt = jwt.getExpiresAt();
		Date nowDate = new Date(System.currentTimeMillis());
		if (nowDate.after(expiresAt)) {
			throw new JWTVerificationException("Le token est expiré.");
		}
	}

	private static Algorithm generateRSA256() {
		try {
			KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
			kpg.initialize(1024);
			KeyPair kp = kpg.generateKeyPair();
			RSAPublicKey rsaPublicKey = (RSAPublicKey) kp.getPublic();
			RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kp.getPrivate();
			return Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("You need to enable Algorithm.RSA256");
		}
	}
}