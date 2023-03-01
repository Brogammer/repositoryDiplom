package kurswork.remaster.KursWorkKpiApp.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "criteria_comments")
public class CriteriaComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int comment_id;
	
	@Column(name = "comment_string", nullable = false)
	private String comment_string;
	
	@Column(name = "comment_date", nullable = false)
	private Date comment_date;
	
	@ManyToOne
	@JoinColumn(name = "employee_id", nullable = false)
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "criteria_id", nullable = false)
	private Criteria criteria;
}