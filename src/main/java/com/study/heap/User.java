package com.study.heap;

public class User {
    private Integer i;
    private String name;

    public User() {
    }

    public Integer getI() {
        return i;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(Integer i, String name) {
        this.i = i;
        this.name = name;
    }
}
