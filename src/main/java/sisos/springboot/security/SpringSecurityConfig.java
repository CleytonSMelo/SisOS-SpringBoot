package sisos.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.thymeleaf.dialect.springdata.SpringDataDialect;





@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http
         .authorizeRequests()
         .antMatchers( "/meucss/**","/img/**","/vendor/**","/js/**").permitAll()
         .antMatchers("/Home/CadastroUser").hasRole("ADMIN")
         .anyRequest().authenticated()
         .and()
         .formLogin().permitAll()
         .loginPage("/Home/login")
         .defaultSuccessUrl("/Home/dashboard/index",true)
         .permitAll()
         .failureUrl("/redirectErroLogin")
         .and()
         .logout().logoutSuccessUrl("/Home/login")
         .permitAll()
         .logoutRequestMatcher(new AntPathRequestMatcher("/Home/logout"))
         .and().csrf().disable(); 
	}
    
     // conexao somente com o BD
	@Autowired
	private ImplementacaoUserDetailService implementacaoUserDetailService; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(implementacaoUserDetailService)
		.passwordEncoder(new BCryptPasswordEncoder());
	}
		

	@Bean
	public SpringDataDialect springDataDialect() {
	        return new SpringDataDialect();
    }
	/*@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/*.css");
		web.ignoring().antMatchers("/*.js");
		web.ignoring().antMatchers("/*.png");
		
		web.ignoring().antMatchers( "/static/**");
	}*/
	
	
}