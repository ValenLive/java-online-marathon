package com.softserve.itacademy.model;

import java.io.ObjectInputStream.GetField;
import java.util.LinkedList;
import java.util.List;

public class User {

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private List<ToDo> myTodos;
    
    public User() {
	}
    
    public User(String firstName, String lastName, String email, String password) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.email = email;
    	this.password = password;
    	this.myTodos = new LinkedList<>();
    }
    
    public String getFirstName() {
		return firstName;
	}
    
    public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
    
    public String getLastName() {
		return lastName;
	}
    
    public void setLastName(String lastName) {
		this.lastName = lastName;
	}
    
    public String getEmail() {
		return email;
	}
    
    public void setEmail(String email) {
		this.email = email;
	}
    
    public String getPassword() {
		return password;
	}
    
    public void setPassword(String password) {
		this.password = password;
	}

	public void setMyTodos(List<ToDo> myTodos) {
		this.myTodos = myTodos;
	}

	public List<ToDo> getMyTodos() {
		return myTodos;
	}
    
    @Override
    public int hashCode() {
    	return firstName.hashCode() + lastName.hashCode() + email.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
    	return this.email.equals(((User)obj).email);
    }
    
    @Override
    public String toString() {
    	return firstName + " " + lastName +  " " + email + " " + password;
    }
  
}
