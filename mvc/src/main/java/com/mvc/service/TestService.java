package com.mvc.service;

import com.mvc.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: Administrator
 * @date: 2018-03-19
 * @Time: 下午 10:10
 * Description:
 **/
@Service
public class TestService {
    private static Logger log = LoggerFactory.getLogger(TestService.class);

    public void testService(){
        log.info("Service");
    }

}