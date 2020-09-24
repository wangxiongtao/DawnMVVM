package com.example.dawnmvvm.bean;



/**
 * Created by fw-soc on 2018/9/19.
 */

public class BaseResponse<T> {
    public String result;
    public String message;
    private int code;//
    public T body;

}
