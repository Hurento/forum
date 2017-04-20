package com.sy.forum.students.student.model;

/**
 * @Author SY
 * @ClassName StudentEntity
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Date 2017-04-20 11:27
 */
public class StudentEntity {
    private String stuName;
    private String stuNo;
    private String currentLoginPerson;

    public StudentEntity() {}

    public StudentEntity(String stuName, String stuNo, String currentLoginPerson) {
        this.stuName = stuName;
        this.stuNo = stuNo;
        this.currentLoginPerson = currentLoginPerson;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuNo() {
        return stuNo;
    }

    public void setStuNo(String stuNo) {
        this.stuNo = stuNo;
    }

    public String getCurrentLoginPerson() {
        return currentLoginPerson;
    }

    public void setCurrentLoginPerson(String currentLoginPerson) {
        this.currentLoginPerson = currentLoginPerson;
    }
}
