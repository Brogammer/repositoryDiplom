package kurswork.remaster.KursWorkKpiApp.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employee_id;
	
	@Column(name = "employee_name", nullable = false)
	private String name;
	@Column(name = "employee_surname", nullable = false)
	private String surname;
	@Column(name = "employee_login", nullable = false, unique = true)
	private String login;
	@Column(name = "employee_password", nullable = false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name = "position_id", nullable = false)
	private Position position;
	@ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinTable(
			name = "employee_role",
			joinColumns = @JoinColumn(name = "employee_id"), 
			inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	private Set<UserRole> userRoles;
	
	
	@OneToMany(mappedBy = "employee")
	private Set<InsertedVariable> insertedVariables;
	

	@OneToMany(mappedBy = "employee")
	private Set<CalculatedDomact> calculatedDomacts;

	

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public Set<InsertedVariable> getInsertedVariables() {
		return insertedVariables;
	}

	public void setInsertedVariables(Set<InsertedVariable> insertedVariables) {
		this.insertedVariables = insertedVariables;
	}

	public Set<CalculatedDomact> getCalculatedDomacts() {
		return calculatedDomacts;
	}

	public void setCalculatedDomacts(Set<CalculatedDomact> calculatedDomacts) {
		this.calculatedDomacts = calculatedDomacts;
	}

	

	public Employee(int employee_id, String name, String surname, String login, String password, Position position,
			Set<UserRole> userRoles, Set<InsertedVariable> insertedVariables, Set<CalculatedDomact> calculatedDomacts) {
		super();
		this.employee_id = employee_id;
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.position = position;
		this.userRoles = userRoles;
		this.insertedVariables = insertedVariables;
		this.calculatedDomacts = calculatedDomacts;
		
	}

	public Employee() {
		super();
	}
	
	
}
