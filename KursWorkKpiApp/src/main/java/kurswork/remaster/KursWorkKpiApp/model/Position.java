package kurswork.remaster.KursWorkKpiApp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "positions")
public class Position {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int position_id;
	
	@Column(name = "position_name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "position")
	private Set<Employee> Employees;
	@ManyToOne
	@JoinColumn(name = "department_id", nullable = false)
	private Department department;
	
	@OneToMany(mappedBy = "position")
	private Set<DomactPositionCalif> DPCs;

	public int getPosition_id() {
		return position_id;
	}

	public void setPosition_id(int position_id) {
		this.position_id = position_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Employee> getEmployees() {
		return Employees;
	}

	public void setEmployees(Set<Employee> employees) {
		Employees = employees;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set<DomactPositionCalif> getDPCs() {
		return DPCs;
	}

	public void setDPCs(Set<DomactPositionCalif> dPCs) {
		DPCs = dPCs;
	}

	public Position(int position_id, String name, Set<Employee> employees, Department department,
			Set<DomactPositionCalif> dPCs) {
		super();
		this.position_id = position_id;
		this.name = name;
		Employees = employees;
		this.department = department;
		DPCs = dPCs;
	}

	public Position() {
		super();
	}
	
	
	
	
}
