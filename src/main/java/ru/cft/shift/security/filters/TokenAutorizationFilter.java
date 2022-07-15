package ru.cft.shift.security.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.cft.shift.model.User;
import ru.cft.shift.repositories.UserRepository;
import ru.cft.shift.security.config.SecurityConfiguration;
import ru.cft.shift.security.details.AccountUserDetails;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@RequiredArgsConstructor
public class TokenAutorizationFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;
    private final UserDetailsService accountUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //если совпал с этим то просто отправляем запрос дальше
        if (request.getRequestURI().equals(SecurityConfiguration.LOGIN_FILTER_PROCESSES_URL)) {
            filterChain.doFilter(request, response);
        } else {
            //нам нужно получить заголовок
            String tokenHeader = request.getHeader("Authorization");
            //если на самом деле пришел токен
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                String token = tokenHeader.substring("Bearer ".length());

                String lastName = null;
                //проверка токена
                try {
                    DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("secret_key_01"))
                            .build()
                            .verify(token);
                    lastName = decodedJWT.getClaim("last name").asString();
                } catch (JWTVerificationException e) {
                    //печать в консоль
                    System.err.println("Неверный токен");
                    //печать в респонс
                    printWrongToken(request, response);
                    return;
                }

                //внутри будет обращаться к базе и искать по емейл
                AccountUserDetails accountUserDetails = (AccountUserDetails) accountUserDetailsService.loadUserByUsername(lastName);
                /**
                 * UsernamePasswordAuthenticationToken(экземпляр интерфейса Authentication) мы его уже передавали
                 * экземпляру AuthenticationManager для проверки в методе attemptAuthentication() в TokenAuthenticationFilter
                 * и если аутентификация прошла успешно то возвращается полностью заполненный объект Authentication
                 */
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(accountUserDetails, null, accountUserDetails.getAuthorities());

                /**
                 * Для пользователя устанавливается контекст безопасности путем вызова
                 * метода SecurityContextHolder.getContext().setAuthentication(…),
                 * куда передается объект который вернул AuthenticationManager
                 */
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);

            } else {
                //если токен не встретился то прокидываем запрос дальше
                logger.warn("Token is missing");
                filterChain.doFilter(request, response);
            }
        }
    }

    private void printWrongToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //если токен неправильный
        logger.warn("Wrong token");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        //статус "запрещено"
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        objectMapper.writeValue(response.getWriter(), Collections.singletonMap("error", "user not found with token"));
    }

}
