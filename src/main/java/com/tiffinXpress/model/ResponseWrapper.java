package com.tiffinXpress.model;

//import javax.xml.ws.Response;
import java.util.List;

public class ResponseWrapper<T> {
    private boolean status;
    private String message;
    private List<String> errors;
    private T data;

    public ResponseWrapper(){

    }

    public ResponseWrapper(boolean status, String message, List<String> errors, T data){
        this.status = status;
        this.message = message;
        this.errors = errors;
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

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
