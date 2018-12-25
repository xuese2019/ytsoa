package com.yts.ytsoa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.SQLSyntaxErrorException;
import java.util.Arrays;
import java.util.Date;

//import org.hibernate.JDBCException;

/**
 * 全局错误处理
 * LD
 */
@Slf4j
@ControllerAdvice
@Controller
public class GlobalExceptionHandler {

    private boolean isAjaxRequest(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(header);
    }

    private void res(HttpServletResponse response,
                     String str) throws Exception {
        ResponseResult<String> result = new ResponseResult<>();
        result.setSuccess(false);
        result.setMessage(str);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(result);
        //这句话的意思，是让浏览器用utf8来解析返回的数据
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        //这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859
        response.setCharacterEncoding("UTF-8");
        PrintWriter pw = response.getWriter();
        pw.write(json);
    }

    /**
     * 页面不存在或出现编译错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = TemplateInputException.class)
    public void templateInputException(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        boolean b = isAjaxRequest(request);
        if (b)
            res(response, "页面不存在或出现编译错误");
        else
            response.encodeRedirectURL("/error/500");
    }

    /**
     * 数据库错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = SQLSyntaxErrorException.class)
    public void sQLSyntaxErrorException(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        boolean b = isAjaxRequest(request);
        if (b)
            res(response, "数据库错误");
        else
            response.encodeRedirectURL("/error/500");
    }

    /**
     * 数据库错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = BadSqlGrammarException.class)
    public void badSqlGrammarException(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        boolean b = isAjaxRequest(request);
        if (b)
            res(response, "数据库错误");
        else
            response.encodeRedirectURL("/error/500");
    }


    /**
     * 全局异常
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    public void myException(HttpServletRequest request,
                            HttpServletResponse response,
                            Exception exception) throws Exception {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        boolean b = isAjaxRequest(request);
        if (b)
            res(response, "内部错误");
        else
            response.encodeRedirectURL("/error/500");
    }

}
