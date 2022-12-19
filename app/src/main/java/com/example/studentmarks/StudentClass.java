package com.example.studentmarks;

public class StudentClass {

    Integer id;
    String name;
    String roll;
    String sem;
    String marks;

    public StudentClass(String name, String roll, String sem, String marks) {
        this.name = name;
        this.roll = roll;
        this.sem = sem;
        this.marks = marks;
    }

    public StudentClass(Integer id, String name, String roll, String sem, String marks) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.sem = sem;
        this.marks = marks;
    }

    public StudentClass(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }


}