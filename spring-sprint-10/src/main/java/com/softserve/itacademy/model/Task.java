package com.softserve.itacademy.model;

public class Task {

    private String name;
    private Priority priority;
    
    public Task() {
    	this.priority = Priority.LOW;
    }

    public Task(String name, Priority priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task " + name + ", Priority " + priority;
    }
    
    @Override
    public int hashCode() {
    	return name.hashCode() + priority.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
    	return this.name.equalsIgnoreCase(((Task) obj).name);
    }
}
