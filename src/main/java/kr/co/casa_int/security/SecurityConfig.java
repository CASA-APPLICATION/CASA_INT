package kr.co.casa_int.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
@Log4j2
public class SecurityConfig  {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    private final CustomUserDetailService customUserDetailsService;

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
            "/swagger-ui/**",

            // spring all user
            "/article/post/member/article",
            "/login",
            "/noUser/**"



    };

    //20221220
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("##### Access filterChain #####");



        /**
         * 2023-08-13 로그인 구현
         *
         */
        // api 형식이기에 off 한다.
        http.csrf().disable();
            // page auth
            http.authorizeHttpRequests()

                .requestMatchers("/admin/**","/test/api").hasAuthority("ADMIN")
                //.requestMatchers("/user/**","/swagger-ui/index.html").hasAuthority("ROLE_USERx")
                    .requestMatchers("/user/**").hasAuthority("ROLE_USER")
                //.requestMatchers("/test/getMapping", "/login","/join").permitAll()
                .requestMatchers(AUTH_WHITELIST).permitAll()
            .and()
//                // Login
            .formLogin()
                    .usernameParameter("uid")
                    .passwordParameter("upw")

                    // 로그인 성공했을때 진입할 경로
                    //.defaultSuccessUrl("/swagger-ui/index.html",true)
                    // 로그인 성공시 이동될 페이지는 허용
                    .permitAll()
//                .loginPage("/login1")
//                .loginProcessingUrl("/loginPro")
//                .defaultSuccessUrl("/")
//                .failureForwardUrl("/login")
            .and()
                // Logout
                    // 로그아웃으로 인증 해제
            .logout(Customizer.withDefaults());
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                // 세션 무효화
//                .invalidateHttpSession(true)
//                // 쿠키 삭제 설정
//                .deleteCookies("JSESSIONID");

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }



    // 비밀번호 암호화
    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }



}
