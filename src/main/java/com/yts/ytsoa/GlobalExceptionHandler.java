package com.yts.ytsoa;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yts.ytsoa.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        response.getWriter().write(json);
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

}
