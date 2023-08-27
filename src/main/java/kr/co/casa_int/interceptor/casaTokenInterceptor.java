package kr.co.casa_int.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

@Component
public class casaTokenInterceptor implements HandlerInterceptor{
        private Logger logger = LoggerFactory.getLogger(this.getClass());

        // key
        private final String CASA_TOKEN = "casa-token";
        // value 임시로 token 값은 "casa" 이다.
        private final String ACCESS_TOKEN = "casa";

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws  Exception{
            logger.info(request.getPathInfo());
            logger.info(request.getRequestURI());
            String userTryToken = request.getHeader(CASA_TOKEN);
//            if ( request.getRequestURI().contains("swagger")) {
//                userTryToken = ACCESS_TOKEN;
//            }ca
            userTryToken = ACCESS_TOKEN;
            logger.info("###### Checking Token ######");
            if ( !userTryToken.equals(ACCESS_TOKEN) || userTryToken.isEmpty() ){
                //return new ResponseEntity<>("Fail : " + userTryToken, HttpStatus.INTERNAL_SERVER_ERROR);
                //return false;
                //return new ResponseEntity<>("Fail : " + userTryToken, HttpStatus.INTERNAL_SERVER_ERROR);
                throw new AuthenticationException("casa token is fail");
            }
            else {
                //return new ResponseEntity<>("Success : " + userTryToken, HttpStatus.OK);
                return true;
            }
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws  Exception{
            logger.info("====================== END ======================");
        }

}
