package result;

import lombok.Data;

/**
 * @author BDsnake
 * @since 2023/3/28 20:52
 */
@Data
public class Result<T> {
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
