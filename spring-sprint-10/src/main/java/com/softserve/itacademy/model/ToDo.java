package com.softserve.itacademy.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ToDo {

	private String title;

	private LocalDateTime createdAt;

	private User owner;

	private List<Task> tasks;

	public ToDo() {
	}

	public ToDo(String title, User owner) {
		this.title = title;
		this.owner = owner;
		this.tasks = new LinkedList<Task>();
		this.createdAt = LocalDateTime.now();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	@Override
	public int hashCode() {
		return owner.hashCode() + title.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this.owner.equals(((ToDo) obj).getOwner()) && this.tasks.containsAll(((ToDo) obj).getTasks()) 
				&& ((ToDo) obj).getTasks().containsAll(this.tasks);
	}
	
	@Override
	public String toString() {
		return "ToDo " + title + " created at " + createdAt + " owner " + owner.getFirstName() + " " + owner.getLastName(); 
	}

}
