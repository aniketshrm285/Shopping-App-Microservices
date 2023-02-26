package com.example.discoveryserver.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

//    @Value("${eureka.username}")
//    private String username;
//    @Value("${eureka.password}")
//    private String password;
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username(username)
//                .password(password)
//                .authorities("USER")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain2(HttpSecurity http) throws Exception {
//
////        authenticationManagerBuilder.inMemoryAuthentication()
////                .passwordEncoder(NoOpPasswordEncoder.getInstance())
////                .withUser("eureka").password("password")
////                .authorities("USER");
//
//        http.csrf().disable()
//                .authorizeRequests().anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
//        return http.build();
//    }
}
