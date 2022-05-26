package proiect.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@Profile("mysql")
public class SecurityJdbcConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select email,password,active "
                        + "from user "
                        + "where email = ?")
                .authoritiesByUsernameQuery("select email, authority "
                        + "from authorities "
                        + "where email = ?");
        ;
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
        http.csrf().disable();

    }
}
