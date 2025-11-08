package com.consoleapp;

public abstract class User {

    protected String name;

    public User() {
        this.name = "";
    }

    public User(String name) {
        this.name = name == null ? "" : name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name;
    }

    @Override
    public String toString() {
        return "User [name=" + name + "]";
    }
}