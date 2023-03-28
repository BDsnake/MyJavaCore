package result;

public enum ResultEnum {

    // 这里是自己定义的

    UNKNOWN_ERROR(-1,"未知错误"),
    OK(200,"执行成功"),
    CREATED(201,"新建或修改数据成功"),
    ACCEPTED(202,"请求已经进入后台排队（异步任务）"),
    NO_CONTENT(204,"用户删除数据成功"),
    NO_DATA(205,"请求的资源不存在"),
    INVALID_REQUEST(400,"用户发出的请求有错误，服务器没有进行新建或修改数据的操作"),
    Unauthorized(401,"用户没有权限（令牌、用户名、密码错误）"),
    Forbidden(403,"用户得到授权,但是访问是被禁止的"),
    NOT_FOUND(404,"用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的"),
    Not_Acceptable(406,"用户请求的格式不可得（比如用户请求 JSON 格式，但是只有 XML 格式）"),
    Gone(410,"用户请求的资源被永久删除，且不会再得到"),
    Unprocessable(422,"当创建一个对象时，发生一个验证错误"),
    INTERNAL_SERVER_ERROR(500,"服务器发生错误，用户将无法判断发出的请求是否成功");

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}