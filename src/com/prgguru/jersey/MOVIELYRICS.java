package com.prgguru.jersey;

public class MOVIELYRICS {

	public String MOVIESTARTCHAR;
	public String MOVIENAME;
	public int MOVIENUMBER;
	public String URLS;
	public int ISLATEST;
	
	public MOVIELYRICS() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MOVIELYRICS(int mOVIENUMBER, String mOVIESTARTCHAR, String mOVIENAME,String uRLS,int ISLATEST) {
		super();
		this.MOVIESTARTCHAR = mOVIESTARTCHAR;
		this.MOVIENAME = mOVIENAME;
		this.MOVIENUMBER = mOVIENUMBER;
		this.URLS = uRLS;
		this.ISLATEST = ISLATEST;
		
	}
	public String getMOVIESTARTCHAR() {
		return this.MOVIESTARTCHAR;
	}
	public void setMOVIESTARTCHAR(String mOVIESTARTCHAR) {
		this.MOVIESTARTCHAR = mOVIESTARTCHAR;
	}
	public String getMOVIENAME() {
		return this.MOVIENAME;
	}
	public void setMOVIENAME(String mOVIENAME) {
		this.MOVIENAME = mOVIENAME;
	}
	public int getMOVIENUMBER() {
		return this.MOVIENUMBER;
	}
	public void setMOVIENUMBER(int mOVIENUMBER) {
		this.MOVIENUMBER = mOVIENUMBER;
	}
	public String getURLS() {
		return this.URLS;
	}
	public void setURLS(String uRLS) {
		this.URLS = uRLS;
	}
	public int getISLATEST() {
		return this.ISLATEST;
	}
	public void setISLATEST(int iSLATEST) {
		this.ISLATEST = iSLATEST;
	}
	
}
