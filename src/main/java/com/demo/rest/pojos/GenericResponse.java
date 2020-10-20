package com.demo.rest.pojos;

import java.util.Map;

public class GenericResponse {
    private boolean status;
    private String message;
    private Map<String, Object> data;

    public GenericResponse(boolean status, String message){
        this.status = status;
        this.message = message;
    }

    public GenericResponse(boolean status, String message, Map<String, Object> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
