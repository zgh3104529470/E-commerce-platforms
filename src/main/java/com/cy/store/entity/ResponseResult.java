package com.cy.store.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> implements Serializable {
    public Integer state;
    private String msg;
    private T data;

    public ResponseResult(Integer state, String msg, T data){
        this.state = state;
        this.msg = msg;
        this.data = data;
    }
    public ResponseResult(Integer state, String msg) {
        this.state = state;
        this.msg = msg;
    }
    public ResponseResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }


    public ResponseResult(Throwable e) {
        super();
        // 获取异常对象中的异常信息
        this.msg = e.getMessage();
    }
}
