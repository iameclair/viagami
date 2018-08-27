package co.uk.eclair.viagami.security.filters;

import co.uk.eclair.viagami.security.JWTTokenProvider;
import co.uk.eclair.viagami.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by ${Eclair} on 8/27/2018.
 */
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private static Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Autowired
    private JWTTokenProvider tokenProvider;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtFromRequest(req);
            if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)){
                Long userId = tokenProvider.getUserIdFromJWT(jwt);

                UserDetails userDetails = userDetailsService.loadByUserId(userId);
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception ex){
            logger.error("Could not set user authentication in security context", ex);
        }
        filterChain.doFilter(req, res);
    }

    private String getJwtFromRequest(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
}
