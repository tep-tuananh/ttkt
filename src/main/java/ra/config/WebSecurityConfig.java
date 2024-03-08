package ra.config;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import ra.security.CustomDetailService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    private CustomDetailService customDetailService;

    @Bean
    DefaultSecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf(AbstractHttpConfigurer::disable).
                authenticationProvider(authenticationProvider()).authorizeHttpRequests(
                        (auth)->auth.requestMatchers("/*").permitAll()
                                .requestMatchers("/admin/**").hasAuthority("ADMIN")
                                .requestMatchers("/user/**").hasAuthority("USER")
                                .anyRequest().authenticated()
                ).formLogin(login ->login.loginPage("/login")
                        .loginProcessingUrl("/login").loginProcessingUrl("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                                .successHandler(roleBasedAuthenticationSuccessHandler())
                     //   .defaultSuccessUrl("/admin",true));
                )
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login"))
                .build();
    }
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web -> web.ignoring().requestMatchers("/accsets/**", "/fe/**", "/templates/uploads/**"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationSuccessHandler roleBasedAuthenticationSuccessHandler() {
        return new RoleBasedAuthenticationSuccessHandler();
    }
}
