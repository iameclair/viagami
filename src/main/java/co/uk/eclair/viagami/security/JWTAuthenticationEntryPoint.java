package co.uk.eclair.viagami.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ${Eclair} on 8/27/2018.
 */
@Component
public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationEntryPoint.class);
    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res,
                         AuthenticationException e) throws IOException, ServletException {
        logger.error("Responding with unauthorised error. Message - {}", e.getMessage());
        res.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                "Sorry, you are not authorized to access this resource.");
    }
}
