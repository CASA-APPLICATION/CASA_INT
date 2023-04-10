package kr.co.casa_int.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author gyutae park
 * @since 2023.04.10
 */

public class casaTokenInterceptor implements HandlerInterceptor{
        private Logger logger = LoggerFactory.getLogger(this.getClass());

        // key
        private final String CASA_TOKEN = "casa-token";
        // value 임시로 token 값은 "casa" 이다.
        private final String ACCESS_TOKEN = "casa";

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws  Exception{
            final String userTryToken = request.getHeader(CASA_TOKEN);
            logger.info("###### Checking Token ######");
            if ( !userTryToken.equals(ACCESS_TOKEN) || userTryToken.isEmpty() ){
                //return new ResponseEntity<>("Fail : " + userTryToken, HttpStatus.INTERNAL_SERVER_ERROR);
                return false;
            }
            else {
                //return new ResponseEntity<>("Success : " + userTryToken, HttpStatus.OK);
                return true;// new ResponseEntity<>("Error", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws  Exception{
            logger.info("====================== END ======================");
        }

}
