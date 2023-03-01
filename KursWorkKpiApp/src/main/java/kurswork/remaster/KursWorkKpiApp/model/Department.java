package kurswork.remaster.KursWorkKpiApp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "departments")
public class Department {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int department_id;

	@Column(name = "department_name", nullable = false)
	private String name;

	@OneToMany(mappedBy = "department")
	private Set<Position> positions;

	public int getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Position> getPositions() {
		return positions;
	}

	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	public Department(int department_id, String name, Set<Position> positions) {
		super();
		this.department_id = department_id;
		this.name = name;
		this.positions = positions;
	}

	public Department() {
		super();
	}
	
	
}
