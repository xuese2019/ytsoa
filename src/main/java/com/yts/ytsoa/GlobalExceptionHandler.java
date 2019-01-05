package com.yts.ytsoa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.sql.SQLException;
import java.sql.SQLRecoverableException;
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
                     String str) throws IOException {
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

    @ExceptionHandler(value = Throwable.class)
    public void throwable(HttpServletRequest request,
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

    @ExceptionHandler(value = ConnectException.class)
    public void connectException(HttpServletRequest request,
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
            res(response, "数据库连接错误");
        else
            response.encodeRedirectURL("/error/500");
    }

    @ExceptionHandler(value = DataAccessException.class)
    public void dataAccessException(HttpServletRequest request,
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

    @ExceptionHandler(value = SQLRecoverableException.class)
    public void sQLRecoverableException(HttpServletRequest request,
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

    @ExceptionHandler(value = CannotGetJdbcConnectionException.class)
    public void cannotGetJdbcConnectionException(HttpServletRequest request,
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
     * 密码错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = SQLException.class)
    public void sQLException(HttpServletRequest request,
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
     * 密码错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = org.apache.ibatis.exceptions.PersistenceException.class)
    public void persistenceException(HttpServletRequest request,
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
     * 密码错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = IncorrectCredentialsException.class)
    public void incorrectCredentialsException(HttpServletRequest request,
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
            res(response, "账号密码错误");
        else
            response.encodeRedirectURL("/error/403");
    }

    /**
     * 权限不足
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = AuthorizationException.class)
    public void authorizationException(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Exception exception) throws IOException {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        boolean b = isAjaxRequest(request);
        if (b)
            res(response, "权限不足");
        else
            response.encodeRedirectURL("/error/403");
    }

    /**
     * 权限不足
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public void unauthorizedException(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Exception exception) throws IOException {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        boolean b = isAjaxRequest(request);
        if (b)
            res(response, "权限不足");
        else
            response.encodeRedirectURL("/error/403");
    }

    /**
     * 页面不存在或出现编译错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = ModelAndViewDefiningException.class)
    public void modelAndViewDefiningException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Exception exception) throws IOException {
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
     * 页面不存在或出现编译错误
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = TemplateInputException.class)
    public void templateInputException(HttpServletRequest request,
                                       HttpServletResponse response,
                                       Exception exception) throws IOException {
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
                                        Exception exception) throws IOException {
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
                                       Exception exception) throws IOException {
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
     * IO异常
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = IOException.class)
    public void iOException(HttpServletRequest request,
                            HttpServletResponse response,
                            Exception exception) throws IOException {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        boolean b = isAjaxRequest(request);
        if (b)
            res(response, "输入输出错误");
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
                            Exception exception) throws IOException {
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

    /**
     * 全局异常
     *
     * @param request
     * @param exception
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public void runtimeException(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Exception exception) throws IOException {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        boolean b = isAjaxRequest(request);
        if (b)
            res(response, exception.getMessage());
        else
            response.encodeRedirectURL("/error/500");
    }

    @ExceptionHandler(value = Exception.class)
    public void exception(HttpServletRequest request,
                          HttpServletResponse response,
                          Exception exception) throws IOException {
        exception.printStackTrace();
        log.debug("ERROR::::：" + exception.getLocalizedMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getCause() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getSuppressed()) + "::::::" + new Date());
        log.debug("ERROR::::：" + exception.getMessage() + "::::::" + new Date());
        log.debug("ERROR::::：" + Arrays.toString(exception.getStackTrace()) + "::::::" + new Date());
        boolean b = isAjaxRequest(request);
        if (b)
            res(response, exception.getMessage());
        else
            response.encodeRedirectURL("/error/500");
    }

}
