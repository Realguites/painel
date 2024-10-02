package com.tasktime.springboot.security;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.tasktime.springboot.model.Usuario;

@Service
public class JwtService {
  private final JwtEncoder encoder;

  public JwtService(JwtEncoder encoder) {
    this.encoder = encoder;
  }

  public String generateToken(Authentication authentication) {
    Instant now = Instant.now();
    long expiry = 36000L;

    Object principal = authentication.getPrincipal();
    Usuario usuario = null;
    try{
      usuario = ((UserAuthenticated)((Object) principal)).getUsuario();
    }catch(Exception e){
    }
    

    String scope = authentication
        .getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors
            .joining(" "));

    JwtClaimsSet claims = JwtClaimsSet.builder()
        .issuer("spring-security-jwt")
        .issuedAt(now)
        .expiresAt(now.plusSeconds(expiry))
        .subject(authentication.getName())
        .claim("scope", scope)
        .claim("tipoUsuario", usuario != null ? usuario.getTipoUsuario() : null)
        .claim("nomeUsuario", usuario != null ? usuario.getNome() : null)
        .claim("needUpdatePass", usuario != null ? usuario.getNeedUpdatePass() : null)
        .build();

    return encoder.encode(
        JwtEncoderParameters.from(claims))
        .getTokenValue();
  }

}
