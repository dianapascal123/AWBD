package proiect.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@Profile("h2")
public class SecurityH2Config extends WebSecurityConfigurerAdapter  {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("client")
                .password(passwordEncoder().encode("1234"))
                .roles("CLIENT")
                .and()
                .withUser("admin@test.com")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/product/**").hasAnyRole("CLIENT", "ADMIN")
                .antMatchers("/account").hasAnyRole("CLIENT", "ADMIN")
                .antMatchers("/address").hasAnyRole("CLIENT", "ADMIN")
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/category/**").hasRole("ADMIN")
                .antMatchers("/order/**").hasAnyRole("CLIENT", "ADMIN")
                .antMatchers("/cart/**").hasAnyRole("CLIENT", "ADMIN")
                .antMatchers("/finalizeOrder").hasAnyRole("CLIENT", "ADMIN")
                .and()
                .formLogin().loginPage("/login")
                .usernameParameter("email")
                .loginProcessingUrl("/authUser")
                .failureUrl("/login-error").permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");

        http.csrf().ignoringAntMatchers("/h2-console/**");
        http.headers()
                .frameOptions()
                .sameOrigin();

        http.csrf().disable();

    }
}
