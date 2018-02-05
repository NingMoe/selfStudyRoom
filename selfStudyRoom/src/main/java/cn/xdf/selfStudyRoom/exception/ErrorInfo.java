package cn.xdf.selfStudyRoom.exception;

public class ErrorInfo<T> {

    public static final Integer OK = 200;
    
    public static final Integer ERROR = 400;
    //状态码
    private Integer code;
    //消息内容
    private String message;
    //请求的url
    private String url;
    //请求返回的数据
    private T data;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Integer getOK() {
        return OK;
    }

    public static Integer getERROR() {
        return ERROR;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}