package ru.cft.shift.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.cft.shift.model.User;
import ru.cft.shift.model.dto.SignInForm;
import ru.cft.shift.repositories.UserRepository;
import ru.cft.shift.security.details.AccountUserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;



public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String TOKEN = "token";
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper, UserRepository userRepository) {
        super(authenticationManager);
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
            SignInForm form = objectMapper.readValue(request.getReader(), SignInForm.class);

            //если появится email лучше добавить его в токен вместо lastname
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(form.getLastName(), form.getPassword());
            return super.getAuthenticationManager().authenticate(token);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AccountUserDetails userDetails = (AccountUserDetails) authResult.getPrincipal();
        User user = userDetails.getUser();
        String token = JWT.create()
                .withSubject(user.getId().toString())
                .withClaim("first name", user.getFirstName())
                .withClaim("last name", user.getLastName())
                .withClaim("role", user.getRole().toString())
                .sign(Algorithm.HMAC256("secret_key_01"));
        user.setToken(token);
        userRepository.save(user);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), Collections.singletonMap(TOKEN, token));
    }
}
