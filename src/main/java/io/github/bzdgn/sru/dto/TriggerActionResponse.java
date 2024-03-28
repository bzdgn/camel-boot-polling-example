package io.github.bzdgn.sru.dto;

public class TriggerActionResponse {

    private String status;

    public TriggerActionResponse() {}

    public TriggerActionResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
