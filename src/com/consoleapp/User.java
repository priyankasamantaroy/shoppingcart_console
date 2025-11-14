package com.consoleapp;

public abstract class User {

    protected String name;

    public User() {
        this.name = "";
    }
    
     //using varargs and conditioning logic to ensure null doesnt break code logic
    public User(String name) {
        this.name = name == null ? "" : name;
    }

    public String getName() {
        return name;
    }

    //using varargs
    public void setName(String name) {
        this.name = name == null ? "" : name;
    }
      // LVTI example (Local Variable Type Inference - Java 10+)
    public String getFormattedName() {
        var name = this.name;  // LVTI - type inferred as String
        var length = name.length();  
        return name.toUpperCase();
    }

    @Override
    public String toString() {
        return "User [name=" + name + "]";
    }
}