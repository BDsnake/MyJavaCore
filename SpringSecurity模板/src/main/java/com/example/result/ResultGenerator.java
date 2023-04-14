package com.example.result;

/**
 * @author BDsnake
 * @since 2023/4/12 20:46
 */
public class ResultGenerator {
    /**
     * 自定义参数类型
     */
    public static <T> Result<T> custom(boolean success,T data,int code, String msg) {
        Result<T> result = Result.newInstance();
        result.setSuccess(success);
        result.setCode(code);
        result.setData(data);
        result.setMessage(msg);
        return result;
    }
    /**成功且带数据**/
    public static <T> Result<T> success(ResultEnum resultEnum,T data){
        return custom(true,data,resultEnum.getCode(), resultEnum.getMessage());
    }

    /**成功但不带数据**/
    public static <T> Result<T> success(ResultEnum resultEnum){
        return custom(true,null,resultEnum.getCode(), resultEnum.getMessage());
    }

    /**失败**/
    public static <T> Result<T> error(ResultEnum resultEnum){
        return custom(false,null, resultEnum.getCode(), resultEnum.getMessage());
    }
}