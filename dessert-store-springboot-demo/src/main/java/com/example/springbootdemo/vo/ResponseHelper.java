package com.example.springbootdemo.vo;

import lombok.Data;

@Data
public class ResponseHelper {

    public static <T> ResponseVO<T> success(T data) {
        return new ResponseVO(200, "Successful Operation",data);
    }

    public static <T> ResponseVO error() {
        return new ResponseVO(406,"The Input Data is Not Acceptable!", "");
    }


}
