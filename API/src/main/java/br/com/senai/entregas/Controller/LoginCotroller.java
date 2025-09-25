package br.com.senai.entregas.Controller;

import br.com.senai.entregas.dto.LoginRequest;
import br.com.senai.entregas.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
public class LoginCotroller {
    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;

    public LoginCotroller(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
    }

    @PostMapping()
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var authToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getSenha());
        Authentication auth = authenticationManager.authenticate(authToken);

        Instant now = Instant.now();
        long validade = 3600L;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("urbanswift-api") // Quem emitiu o token.
                .issuedAt(now) // Quando foi emitido.
                .expiresAt(now.plusSeconds(validade)) // Quando expira.
                .subject(auth.getName()) // A quem o token pertence (o email do usuário).
                .claim("roles", auth.getAuthorities()) // Informações extras, como os perfis do usuário.
                .build();

        JwsHeader jwsHeader = JwsHeader.with(MacAlgorithm.HS256).build();
        String token = this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
        return ResponseEntity.ok(token);

    }
}
