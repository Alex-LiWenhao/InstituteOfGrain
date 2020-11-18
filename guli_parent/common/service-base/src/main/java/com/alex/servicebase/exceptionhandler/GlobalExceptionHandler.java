package com.alex.servicebase.exceptionhandler;



import com.alex.commonutils.ExceptionUtil;
import com.alex.commonutils.R;
import com.alex.servicebase.AlexException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName GlobalExceptionHandler
 * @Description TODO :
 * @Author Alex
 * @Date 2020/11/10 21:45
 * @Version 1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        e.printStackTrace();
        return R.error().message("指定ArithmeticException异常处理");
    }

    @ExceptionHandler(AlexException.class)
    @ResponseBody
    public R error(AlexException e){
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message("指定AlexException异常处理");
    }
}
