package com.partick.authservice.dto;

import lombok.Data;

/**
 * @author partick_peng
 */
@Data
public class ResponseObject<T> {
    private String code;
    private String message;
    private T data;

    public ResponseObject() {
    }

    public ResponseObject(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseObject(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    /**
     * ...是指可变参数，表示可以传入或者不传入，默认以数组形式存在
     * @param data
     */
    public ResponseObject(T... data) {
        this.code = "0";
        this.message = "success";
        if (data.length > 0) {
            this.data = data[0];
        }
    }
}
