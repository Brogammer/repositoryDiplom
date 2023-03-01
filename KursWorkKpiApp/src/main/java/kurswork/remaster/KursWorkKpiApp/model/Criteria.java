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
@Table(name = "criterias")
public class Criteria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int criteria_id;
	@Column(name = "criteria_name", nullable = false)
	private String criteria_name;
	@Column(name = "criteria_descr", nullable = false)
	private String criteria_descr;
	
	@ManyToOne
	@JoinColumn(name = "subgroup_id", nullable = false)
	private Subgroup subgroup;
	
	@OneToMany(mappedBy = "criteria",fetch = FetchType.EAGER)
	private Set<CriteriaFormula> criteriaFormulas;
	

	@OneToMany(mappedBy = "criteria",fetch = FetchType.EAGER)
	private Set<CriteriaComment> comments;


	public int getCriteria_id() {
		return criteria_id;
	}


	public void setCriteria_id(int criteria_id) {
		this.criteria_id = criteria_id;
	}


	public String getCriteria_name() {
		return criteria_name;
	}


	public void setCriteria_name(String criteria_name) {
		this.criteria_name = criteria_name;
	}


	public String getCriteria_descr() {
		return criteria_descr;
	}


	public void setCriteria_descr(String criteria_descr) {
		this.criteria_descr = criteria_descr;
	}


	public Subgroup getSubgroup() {
		return subgroup;
	}


	public void setSubgroup(Subgroup subgroup) {
		this.subgroup = subgroup;
	}


	public Set<CriteriaFormula> getCriteriaFormulas() {
		return criteriaFormulas;
	}


	public void setCriteriaFormulas(Set<CriteriaFormula> criteriaFormulas) {
		this.criteriaFormulas = criteriaFormulas;
	}


	public Set<CriteriaComment> getComments() {
		return comments;
	}


	public void setComments(Set<CriteriaComment> comments) {
		this.comments = comments;
	}


	public Criteria(int criteria_id, String criteria_name, String criteria_descr, Subgroup subgroup,
			Set<CriteriaFormula> criteriaFormulas, Set<CriteriaComment> comments) {
		super();
		this.criteria_id = criteria_id;
		this.criteria_name = criteria_name;
		this.criteria_descr = criteria_descr;
		this.subgroup = subgroup;
		this.criteriaFormulas = criteriaFormulas;
		this.comments = comments;
	}


	public Criteria() {
		super();
	}
	
}
