package nl.molens;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().requireCsrfProtectionMatcher(request -> request.getRequestURI().startsWith("/api/crud")).and().authorizeRequests()
        .antMatchers("/","/*.js","/*.css","/*.map","/*.ttf","/*.woff","/favicon.ico",
        "/fotos/molenfoto/*", "/api/molens/*/*",
        "/login", "/perform_login","/api/login","/api/loginError","/api/csrf"
        ).permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
        .loginProcessingUrl("/perform_login")
				.defaultSuccessUrl("/api/login", true)
        .failureUrl("/api/login?error=true")
				.and()
			.logout()
        .logoutUrl("/perform_logout")
        .deleteCookies("JSESSIONID")
        .and().headers().frameOptions().disable(); //For h2 console
	}

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
        .withUser("test").password(passwordEncoder().encode("test")).roles("USER")
        .and()
        .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles("USER")
        .and()
        .withUser("admin").password(passwordEncoder().encode("adminPass")).roles("ADMIN");
  }

  @Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
}
