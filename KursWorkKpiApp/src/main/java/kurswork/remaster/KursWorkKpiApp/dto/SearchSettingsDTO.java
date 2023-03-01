package kurswork.remaster.KursWorkKpiApp.dto;

public class SearchSettingsDTO {
	private String string;
	private int type;
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public SearchSettingsDTO(String string, int type) {
		super();
		this.string = string;
		this.type = type;
	}
	public SearchSettingsDTO() {
		super();
	}
	
	
}
