package kurswork.remaster.KursWorkKpiApp.dto;

import kurswork.remaster.KursWorkKpiApp.model.Position;

public class EmployeeRegistrationDTO {
	private String name;
	private String surname;
	private String login;
	private String password;
	private Position position;
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
	public EmployeeRegistrationDTO(String name, String surname, String login, String password, Position position) {
		super();
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.position = position;
	}
	public EmployeeRegistrationDTO() {
		super();
	}
	
	
	
}
