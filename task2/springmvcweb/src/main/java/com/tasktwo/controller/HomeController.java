package com.tasktwo.controller;

import com.tasktwo.model.Student;
import com.tasktwo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * Created by Administrator on 24/7/2017.
 */

@Controller
@RequestMapping("/task2")
public class HomeController {
    private static Logger logger = Logger.getLogger(HomeController.class);
    @Autowired
    private StudentService studyService;

    @RequestMapping(value="/a/student/select/{userId}",method = RequestMethod.GET)
    public String detail(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable String userId) {
        try {
            logger.info("查找当前的用户信息");
            logger.info(userId);
            Student study = this.studyService.getUserId(userId);
            logger.info(study);
            model.addAttribute("study",study);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error("查询失败" + e.getMessage());
            return "errorJson";
        }
        return "studentDataliJson";
    }


    @RequestMapping("/a/student/toAddStudent")
	public String toAddStudent(){
		return "studentInsertJson";
	}


    @RequestMapping(value = "/a/student/insert",method = RequestMethod.POST)
    public String insert(HttpServletRequest request, HttpServletResponse response, Model model,@RequestBody Student user ) {
        try {
            logger.info("添加用户信息");

            user.setentryData(System.currentTimeMillis());
            user.setCreateAt(System.currentTimeMillis());
            user.setUpdateAt(System.currentTimeMillis());
            logger.info("user" + user);
            Integer i = studyService.studyInsert(user);
            logger.info("添加信息的行数" + i);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("添加出错" + e.getMessage());
            return "errorJson";
        }
        return "successJson";
    }

    @RequestMapping(value = "/a/student/update",method = RequestMethod.PUT)
    public String update(HttpServletRequest request, HttpServletResponse response,Model model,
                         String name, String userId){
        try {
            logger.info("修改用户信息");
            Student study = new Student();
            study.setName(name);
            study.setUserId(userId);
            logger.info("name  :  "+ name);
            logger.info("userId  :  "+  userId);
            study.setUpdateAt(System.currentTimeMillis());
            int i = studyService.studyUpdate(study);
            logger.info("返回修改行数" + i);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("修改出错" + e.getMessage());
            return "errorJson";
        }
        return "successJson";
    }


    @RequestMapping(value = "/a/student/delete/{userId}",method = RequestMethod.DELETE)
    public String delete(HttpServletRequest request, HttpServletResponse response,Model model,@PathVariable("userId") String userId){
        try {
            logger.info("删除用户信息");
            logger.info("userId  :  "+  userId);
            int i = studyService.studyDelete(userId);
            logger.info("返回影响行数" + i);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("" + e.getMessage());
            return "errorJson";
        }
        return "successJson";
    }

    @RequestMapping(value = "/a/student/all",method = RequestMethod.GET)
    public String all(HttpServletRequest request, HttpServletResponse response,Model model){
        try {
            logger.info("查询全部学生信息");
            List<Student> study = studyService.studentAll();
            logger.info("studylist ： " + study);
            int i = study.size();
            logger.info("返回用户数量" + i);
            model.addAttribute("study",study);
        }catch(Exception e){
            e.printStackTrace();
            logger.error("查询错误" + e.getMessage());
            return "errorJson";
        }
        return "studentListJson";
    }

}