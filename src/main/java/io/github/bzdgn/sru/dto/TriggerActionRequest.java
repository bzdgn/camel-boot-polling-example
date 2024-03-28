package io.github.bzdgn.sru.dto;

public class TriggerActionRequest {

    private String dtDate;
    private String dtModified;

    public TriggerActionRequest() {}

    public String getDtDate() {
        return dtDate;
    }

    public void setDtDate(String dtDate) {
        this.dtDate = dtDate;
    }

    public String getDtModified() {
        return dtModified;
    }

    public void setDtModified(String dtModified) {
        this.dtModified = dtModified;
    }

    @Override
    public String toString() {
        return "TriggerActionRequest{" +
                "dtDate='" + dtDate + '\'' +
                ", dtModified='" + dtModified + '\'' +
                '}';
    }
}
