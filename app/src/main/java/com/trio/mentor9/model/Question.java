package com.trio.mentor9.model;

public class Question {
	private String QUES;
	private String ANS_true;
	private String ANS_fake1;
	private String ANS_fake2;
	private String LOCATION;
	private String ROW_ID;
	private String TYPE;
	private String UNIT_NO;
	private int ATTEMPT=0;
	private int SUCCESS=0;
	private String QUE_ID;
	
	public String getQUE_ID() {
		return QUE_ID;
	}
	public void setQUE_ID(String qUE_ID) {
		QUE_ID = qUE_ID;
	}
	public int getATTEMPT() {
		return ATTEMPT;
	}
	public void setATTEMPT(int aTTEMPT) {
		ATTEMPT = aTTEMPT;
	}
	public int getSUCCESS() {
		return SUCCESS;
	}
	public void setSUCCESS(int sUCCESS) {
		SUCCESS = sUCCESS;
	}
	public String getQUES() {
		return QUES;
	}
	public void setQUES(String qUES) {
		QUES = qUES;
	}
	public String getANS_true() {
		return ANS_true;
	}
	public void setANS_true(String aNS_true) {
		ANS_true = aNS_true;
	}
	public String getANS_fake1() {
		return ANS_fake1;
	}
	public void setANS_fake1(String aNS_fake1) {
		ANS_fake1 = aNS_fake1;
	}
	public String getANS_fake2() {
		return ANS_fake2;
	}
	public void setANS_fake2(String aNS_fake2) {
		ANS_fake2 = aNS_fake2;
	}
	public String getLOCATION() {
		return LOCATION;
	}
	public void setLOCATION(String lOCATION) {
		LOCATION = lOCATION;
	}
	public String getROW_ID() {
		return ROW_ID;
	}
	public void setROW_ID(String rOW_ID) {
		ROW_ID = rOW_ID;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getUNIT_NO() {
		return UNIT_NO;
	}
	public void setUNIT_NO(String uNIT_NO) {
		UNIT_NO = uNIT_NO;
	}
	
}
