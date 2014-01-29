package org.imanmobile.sms.core.domain;

public class Recipient {
	private long gsm;
    private String firstvalue;
    private String secondvalue;
    private String thirdvalue;
    private String fourthvalue;
    private String fifthvalue;

    public Recipient(){}
    public Recipient(long gsm){
        this.gsm = gsm;
    }

	public long getGsm() {
		return gsm;
	}

	public void setGsm(long gsm) {
		this.gsm = gsm;
	}

    public String getFirstvalue() {
        return firstvalue;
    }

    public void setFirstvalue(String firstvalue) {
        this.firstvalue = firstvalue;
    }

    public String getSecondvalue() {
        return secondvalue;
    }

    public void setSecondvalue(String secondvalue) {
        this.secondvalue = secondvalue;
    }

    public String getThirdvalue() {
        return thirdvalue;
    }

    public void setThirdvalue(String thirdvalue) {
        this.thirdvalue = thirdvalue;
    }

    public String getFourthvalue() {
        return fourthvalue;
    }

    public void setFourthvalue(String fourthvalue) {
        this.fourthvalue = fourthvalue;
    }

    public String getFifthvalue() {
        return fifthvalue;
    }

    public void setFifthvalue(String fifthvalue) {
        this.fifthvalue = fifthvalue;
    }
}
