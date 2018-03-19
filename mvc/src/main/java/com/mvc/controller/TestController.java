package com.mvc.controller;

import com.mvc.annotation.Autowired;
import com.mvc.annotation.Controller;
import com.mvc.annotation.RequestMapping;
import com.mvc.annotation.RequestParam;
import com.mvc.service.TestService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-03-19
 * @Time: 下午 8:29
 * Description:
 **/
@Controller
public class TestController {

    @Autowired
    TestService testService;

    @RequestMapping("/test")
    public void test1(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam("str_param") String strParam,
                      @RequestParam("int_param") Integer intParam,
                      @RequestParam("float_param") Float floatParam,
                      @RequestParam("double_param") Double doubleParam) {

        testService.testService();

        try {
            response.getWriter().write(
                    "String parameter: " + strParam +
                            "\nInteger parameter: " + intParam +
                            "\nFloat parameter: " + floatParam +
                            "\nDouble parameter: " + doubleParam);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}