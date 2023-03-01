package kurswork.remaster.KursWorkKpiApp.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "subgroups")
public class Subgroup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subgroup_id;
	
	@Column(name = "subgroup_name", nullable = false)
	private String subgroup_name;
	
	@ManyToOne
	@JoinColumn(name = "domact_id", nullable = false)
	private Domact domact;
	@OneToMany(mappedBy = "subgroup",fetch = FetchType.EAGER)
	private Set<Criteria> criterias;
	public int getSubgroup_id() {
		return subgroup_id;
	}
	public void setSubgroup_id(int subgroup_id) {
		this.subgroup_id = subgroup_id;
	}
	public String getSubgroup_name() {
		return subgroup_name;
	}
	public void setSubgroup_name(String subgroup_name) {
		this.subgroup_name = subgroup_name;
	}
	public Domact getDomact() {
		return domact;
	}
	public void setDomact(Domact domact) {
		this.domact = domact;
	}
	public Set<Criteria> getCriterias() {
		return criterias;
	}
	public void setCriterias(Set<Criteria> criterias) {
		this.criterias = criterias;
	}
	public Subgroup(int subgroup_id, String subgroup_name, Domact domact, Set<Criteria> criterias) {
		super();
		this.subgroup_id = subgroup_id;
		this.subgroup_name = subgroup_name;
		this.domact = domact;
		this.criterias = criterias;
	}
	public Subgroup() {
		super();
	}
	
	
}
