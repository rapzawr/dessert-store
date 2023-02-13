package com.example.springbootdemo.vo;

public class ResponseVO <T>{

    private int code;
    private String message;
    private T data;

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }

    public void setData(T data) { this.data = data; }

    public ResponseVO(int code, String message, T data){
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }

}
