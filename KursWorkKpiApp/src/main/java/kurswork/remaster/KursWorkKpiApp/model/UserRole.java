package kurswork.remaster.KursWorkKpiApp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity 
@Table(name = "roles")
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int role_id;
	@Column(name = "role_descr", nullable = false)
	private String role_descr; 
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "userRoles") 
	private Set<Employee> employees;

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole_descr() {
		return role_descr;
	}

	public void setRole_descr(String role_descr) {
		this.role_descr = role_descr;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

	public UserRole(int role_id, String role_descr, Set<Employee> employees) {
		super();
		this.role_id = role_id;
		this.role_descr = role_descr;
		this.employees = employees;
	}

	public UserRole() {
		super();
	}
	
}
