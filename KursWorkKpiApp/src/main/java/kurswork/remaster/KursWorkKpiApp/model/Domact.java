package kurswork.remaster.KursWorkKpiApp.model;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "domact")
public class Domact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int domact_id;
	@Column(name = "domact_name", nullable = false)
	private String domact_name;

	@OneToMany(mappedBy = "domact")
	private Set<DomactPositionCalif> DPCs;
	@OneToMany(mappedBy = "domact")
	private List<Subgroup> Subgroups;
	
	@OneToMany(mappedBy = "domact")
	private Set<CalculatedDomact> calculatedDomacts;

	public int getDomact_id() {
		return domact_id;
	}

	public void setDomact_id(int domact_id) {
		this.domact_id = domact_id;
	}

	public String getDomact_name() {
		return domact_name;
	}

	public void setDomact_name(String domact_name) {
		this.domact_name = domact_name;
	}

	public Set<DomactPositionCalif> getDPCs() {
		return DPCs;
	}

	public void setDPCs(Set<DomactPositionCalif> dPCs) {
		DPCs = dPCs;
	}

	public List<Subgroup> getSubgroups() {
		return Subgroups;
	}

	public void setSubgroups(List<Subgroup> subgroups) {
		Subgroups = subgroups;
	}

	public Set<CalculatedDomact> getCalculatedDomacts() {
		return calculatedDomacts;
	}

	public void setCalculatedDomacts(Set<CalculatedDomact> calculatedDomacts) {
		this.calculatedDomacts = calculatedDomacts;
	}

	public Domact(int domact_id, String domact_name, Set<DomactPositionCalif> dPCs, List<Subgroup> subgroups,
			Set<CalculatedDomact> calculatedDomacts) {
		super();
		this.domact_id = domact_id;
		this.domact_name = domact_name;
		DPCs = dPCs;
		Subgroups = subgroups;
		this.calculatedDomacts = calculatedDomacts;
	}

	public Domact() {
		super();
	}
	
	
}
