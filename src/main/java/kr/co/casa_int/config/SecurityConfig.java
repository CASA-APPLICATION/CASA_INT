package kr.co.casa_int.config;

import kr.co.casa_int.service.UserMgService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    //private final JwtTokenProvider jwtTokenProvider;
    //private final CustomOAuth2UserService customOAuth2UserService;
    //private final UserMgService service;
    private static final String[] AUTH_WHITELIST = {
            // -- Static resources
            "/css/**",
            "/images/**",
            "/js/**",
            // -- Swagger UI v2
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (Open API)
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };

//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().requestMatchers("/static/css/**, /static/js/**, *.ico");
//
//        // swagger
//        web.ignoring().requestMatchers(
//                "/v2/api-docs",  "/configuration/ui",
//                "/swagger-resources", "/configuration/security",
//                "/swagger-ui.html", "/webjars/**","/swagger/**");
//    }

    //20221220
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        logger.info("##### Access filterChain #####");

        /**
         * 2023-08-13 로그인 구현
         *
         */
        //http.cors().disable();
        http.csrf().disable();
        //http.formLogin().disable();




        //http.httpBasic().disable()
                http.authorizeHttpRequests()

                .requestMatchers("/admin/**").hasRole("ROLE_ADMIN")
                .requestMatchers("/member/**").hasAuthority("ROLE_USER")
                //.requestMatchers("/test/getMapping", "/login","/join").permitAll()
                .requestMatchers("/noUser/**","/user/no/**","/user/**", "/test/**",
                        // -- Static resources
                        "/css/**",
                        "/images/**",
                        "/js/**",
                        // -- Swagger UI v2
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        // -- Swagger UI v3 (Open API)
                        "/v3/api-docs/**",
                        "/swagger-ui/**",
                        "/login").permitAll()

                //.anyRequest().authenticated()
                .and();
//                .formLogin()
//                    .loginPage("/login")
//                    .loginProcessingUrl("/loginPro")
//                    .defaultSuccessUrl("/");
                //.and()
                //.build();

                // security 전에 jwt 토큰 검사가 진행된다.

                // jwt 부분 추가해야함
                //.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                  //      UsernamePasswordAuthenticationFilter.class);
        //        .httpBasic(withDefaults());
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {

        return (web) -> web.ignoring().requestMatchers( "/ignore1","/ignore2");
    }

    // 비밀번호 암호화
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
