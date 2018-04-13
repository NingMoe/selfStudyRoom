package cn.xdf.pay.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.xdf.pay.util.ResponseInfo;
import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理类
 * @author liuwei63
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 返回RESTful式的异常JSON数据
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = MyException.class)
    @ResponseBody
    public ResponseInfo<String> jsonErrorHandler(HttpServletRequest req, MyException e) throws Exception {
    	ResponseInfo<String> responseInfo = new ResponseInfo<String>();
    	responseInfo.setRtnMsg(e.getMessage());
    	responseInfo.setRtnCode(ResponseInfo.ERROR);
    	responseInfo.setData("");
        return responseInfo;
    }

}

