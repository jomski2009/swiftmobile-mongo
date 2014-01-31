package org.imanmobile.sms.core.domain;

import org.mongodb.morphia.annotations.Entity;

@Entity
public class Recipient {

    private long gsm;
    private String firstvalue;
    private String secondvalue;
    private String thirdvalue;
    private String fourthvalue;
    private String fifthvalue;

    public Recipient() {
    }

    public Recipient(long gsm) {
        this.gsm = gsm;
    }

    public Recipient(long gsm, String firstvalue) {
        this.gsm = gsm;
        this.firstvalue = firstvalue;
    }

    public Recipient(long gsm, String firstvalue, String secondvalue) {
        this.gsm = gsm;
        this.firstvalue = firstvalue;
        this.secondvalue = secondvalue;
    }

    public Recipient(long gsm, String firstvalue, String secondvalue, String thirdvalue) {
        this.gsm = gsm;
        this.firstvalue = firstvalue;
        this.secondvalue = secondvalue;
        this.thirdvalue = thirdvalue;
    }

    public Recipient(long gsm, String firstvalue, String secondvalue, String thirdvalue, String fourthvalue) {
        this.gsm = gsm;
        this.firstvalue = firstvalue;
        this.secondvalue = secondvalue;
        this.thirdvalue = thirdvalue;
        this.fourthvalue = fourthvalue;
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

    @Override
    public String toString() {
        return "Recipient{" +
                "gsm=" + gsm +
                ", firstvalue='" + firstvalue + '\'' +
                ", secondvalue='" + secondvalue + '\'' +
                ", thirdvalue='" + thirdvalue + '\'' +
                ", fourthvalue='" + fourthvalue + '\'' +
                ", fifthvalue='" + fifthvalue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipient)) return false;

        Recipient recipient = (Recipient) o;

        if (gsm != recipient.gsm) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (gsm ^ (gsm >>> 32));
    }
}
