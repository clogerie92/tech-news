package com.technews.testModel;

import java.util.Objects;

public class Demo {
    // instance variables
    private String name;
    private int age;
    // constructor
    public Demo(String name, int age) {
        this.name = name;
        this.age = age;
    }
    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    // override equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Demo demo = (Demo) o;
        return age == demo.age && Objects.equals(name, demo.name);
    }
    // override hashcode()
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
    // override toString()
    @Override
    public String toString() {
        return "Demo{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
