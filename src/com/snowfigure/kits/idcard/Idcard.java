package com.snowfigure.kits.idcard;

public class Idcard {
	private boolean validate = false;
	private String idcardno_15;
	private String gender;
	private String idcardno;
	private String address;
	private String birthdate;
	public Idcard(String idcardno) {
		super();
		this.idcardno = idcardno;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender=gender;
		if("M".equals(gender)) this.gender="男";
		if("F".equals(gender)) this.gender="女";
	}
	public String getIdcardno_15() {
		return idcardno_15;
	}
	public void setIdcardno_15(String idcardno_15) {
		this.idcardno_15 = idcardno_15;
	}
	public boolean isValidate() {
		return validate;
	}
	public void setValidate(boolean validate) {
		this.validate = validate;
	}
	public String getIdcardno() {
		return idcardno;
	}
	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
}
