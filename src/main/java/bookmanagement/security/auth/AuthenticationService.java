package bookmanagement.security.auth;
import bookmanagement.security.config.JwtService;
import bookmanagement.security.token.Token;
import bookmanagement.security.token.TokenRepository;
import bookmanagement.security.token.TokenType;
import bookmanagement.security.customer.Role;
import bookmanagement.security.customer.Customer;
import bookmanagement.security.customer.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
	
  private final CustomerRepository repository;
  
  private final TokenRepository tokenRepository;
  
  private final PasswordEncoder passwordEncoder;
  
  private final JwtService jwtService;
  
  private final AuthenticationManager authenticationManager;
  
  public AuthenticationResponse register(RegisterRequest request) {
    var user = Customer.builder()
        .firstName(request.getFirstName())
        .lastName(request.getLastName())
        .email(request.getEmail())
        .pswd(passwordEncoder.encode(request.getPassword()))
        .role(request.getRole())
        .build();
    
    var savedUser = repository.save(user);
    
    var jwtToken = jwtService.generateToken(user);
    
    var refreshToken = jwtService.generateRefreshToken(user);
    
    saveUserToken(savedUser, jwtToken);
    
    
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    
    var jwtToken = jwtService.generateToken(user);
    
    var refreshToken = jwtService.generateRefreshToken(user);
    
    revokeAllUserTokens(user);
    
    saveUserToken(user, jwtToken);
    
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  private void saveUserToken(Customer user, String jwtToken) {
    var token = Token.builder()
        .customer(user)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(Customer user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getCustomer_id());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    refreshToken = authHeader.substring(7);
    userEmail = jwtService.extractUserName(refreshToken);
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      if (jwtService.isTokenValid(refreshToken, user)) {
        var accessToken = jwtService.generateToken(null);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      }
    }
  }
}