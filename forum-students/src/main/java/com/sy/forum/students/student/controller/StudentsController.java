package com.sy.forum.students.student.controller;

import com.sy.forum.students.student.model.StudentEntity;
import com.sy.forum.system.users.model.UserInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author SY
 * @ClassName StudentsController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Date 2017-04-20 11:30
 */
@RestController
@RequestMapping("/student")
public class StudentsController {

    @RequestMapping("/getStudentInfo")
    public StudentEntity getStudentInfo(){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName("管理员");
        return new StudentEntity("张三","001",userInfo.getUserName());
    }

}
