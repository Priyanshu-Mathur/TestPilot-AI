package com.testpilot.ai.dto;

public class DeleteRequirementResponse {

    private Long id;
    private String message;

    public DeleteRequirementResponse() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
