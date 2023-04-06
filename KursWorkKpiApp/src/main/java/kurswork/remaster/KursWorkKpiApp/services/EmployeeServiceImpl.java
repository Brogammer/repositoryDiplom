package kurswork.remaster.KursWorkKpiApp.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kurswork.remaster.KursWorkKpiApp.dto.EmployeeRegistrationDTO;
import kurswork.remaster.KursWorkKpiApp.model.CalculatedDomact;
import kurswork.remaster.KursWorkKpiApp.model.Employee;
import kurswork.remaster.KursWorkKpiApp.model.InsertedVariable;
import kurswork.remaster.KursWorkKpiApp.model.UserRole;
import kurswork.remaster.KursWorkKpiApp.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	private UserRoleService userRoleService;

	private BCryptPasswordEncoder encoder;

	@Autowired
	public EmployeeServiceImpl(@Lazy BCryptPasswordEncoder encoder, UserRoleService userRoleService,
			EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
		this.encoder = encoder;
		this.userRoleService = userRoleService;

	}

	public EmployeeServiceImpl() {
	}

	@Override
	public Employee findEmployeeById(int id) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(id).get();
	}

	@Override
	public Boolean hasAlreadyLoginExisted(String login) {
		long q = employeeRepository.findAll().stream().filter(e -> e.getLogin().equals(login)).count();
		return q > 0;

	}

	@Override
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee save(EmployeeRegistrationDTO dto) {

		Set<UserRole> roles = new HashSet<>();
		Set<InsertedVariable> insertedVariables = new HashSet<>();
		Set<CalculatedDomact> calculatedDomacts = new HashSet<>();

		String encodedPassword = encoder.encode(dto.getPassword());

		Employee employee = new Employee(0, dto.getName(), dto.getSurname(), dto.getLogin(), encodedPassword,
				dto.getPosition(), dto.getDepartment(), roles, insertedVariables, calculatedDomacts);

		Employee savedEmployee = employeeRepository.save(employee);
		userRoleService.assignEmployeeRole(savedEmployee.getEmployee_id(), 1); // 1 - User role, 2 - Admin role

		return savedEmployee;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Employee employee = employeeRepository.findByLogin(username);
		System.out.println(username);
		if (employee == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}
		return new User(employee.getLogin(), employee.getPassword(), mapRolesToAuthorities(employee.getUserRoles()));

	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<UserRole> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole_descr()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee findByLogin(String login) {
		// TODO Auto-generated method stub
		return employeeRepository.findByLogin(login);
	}

	@Override
	public Employee update(Employee employee) {
		// TODO Auto-generated method stub
		employee.setPassword(encoder.encode(employee.getPassword()));

		return employeeRepository.save(employee);
	}

}
