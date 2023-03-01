package kurswork.remaster.KursWorkKpiApp.dto;


public class CalificatRegistrationDTO {
	private double left_bound;

	private double right_bound;

	private double calificat_coef;

	private String calificat_symbol;

	public double getLeft_bound() {
		return left_bound;
	}

	public void setLeft_bound(double left_bound) {
		this.left_bound = left_bound;
	}

	public double getRight_bound() {
		return right_bound;
	}

	public void setRight_bound(double right_bound) {
		this.right_bound = right_bound;
	}

	public double getCalificat_coef() {
		return calificat_coef;
	}

	public void setCalificat_coef(double calificat_coef) {
		this.calificat_coef = calificat_coef;
	}

	public String getCalificat_symbol() {
		return calificat_symbol;
	}

	public void setCalificat_symbol(String calificat_symbol) {
		this.calificat_symbol = calificat_symbol;
	}

	public CalificatRegistrationDTO(double left_bound, double right_bound, double calificat_coef,
			String calificat_symbol) {
		super();
		this.left_bound = left_bound;
		this.right_bound = right_bound;
		this.calificat_coef = calificat_coef;
		this.calificat_symbol = calificat_symbol;
	}

	public CalificatRegistrationDTO() {
		super();
	}
	
	
}
