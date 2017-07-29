package com.tasktwo.service;

import com.tasktwo.dao.StudentMapper;
import com.tasktwo.model.Student;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 *
 * Created by Administrator on 25/7/2017.
 */

@Service("userService")
public class StudentServiceImpl implements StudentService{
    @Resource
    private StudentMapper studyDao;
    public Student getUserId(String userId){
        return this.studyDao.studySelect(userId);
    }
    public int studyInsert(Student student){
        return this.studyDao.studyInsert(student);
    }
    public int studyUpdate(Student student){
        return this.studyDao.studyUpdate(student);
    }
    public int studyDelete(String userId){
        return this.studyDao.studyDelete(userId);
    }
    public List<Student> studentAll(){
        return this.studyDao.studentAll();
    }
}
