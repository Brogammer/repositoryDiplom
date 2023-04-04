package kurswork.remaster.KursWorkKpiApp.config;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.services.EmployeeService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private EmployeeService employeeService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(employeeService);
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/EmployeeRegistration**", "/img/**", "/DepartmentSelection**").permitAll()
				.and().authorizeRequests()
				.antMatchers("/Test", "/CriteriaSelectionForInsert", "/insertDate", "/insertNormaStiintifica",
						"/VariablesListOfCriteria", "/InsertVariable", "/CloseVariableList",
						"/NextVariablesListOfCriteria", "/DeleteCriteriaValues", "/InsertMultVariable",
						"/CriteriaEvaluationResult", "/selectDate")
				.hasAnyRole("ADMIN", "USER", "MANAGERICS").antMatchers("/UserPage").hasAnyRole("USER")
				.antMatchers("/AdminPage", "/FormulaCreation", "/DepartmentRegistration", "/PositionRegistration",
						"/DepartmentSelection", "/DomactRegistration", "/SubgroupList","/selectDateForAllResults","/AllCriteriaResultByDate")
				.hasAnyRole("ADMIN").antMatchers("/ManagerPage").hasAnyRole("MANAGERICS").anyRequest().authenticated()
				.and().formLogin().loginPage("/EmployeeLogin").permitAll().and().logout().invalidateHttpSession(true)
				.clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/EmployeeLogin?logout").permitAll();
	}

}
