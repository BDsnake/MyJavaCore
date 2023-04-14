package com.example.result;

import lombok.Data;

/**
 * @author BDsnake
 * @since 2023/4/12 20:35
 */
@Data
public  class Result<T> {
    private boolean success = false;
    private T data;
    private int code;
    private String message;
    public Result() {

    }
    public static <T> Result<T> newInstance() {
        return new Result<T>();
    }
}
