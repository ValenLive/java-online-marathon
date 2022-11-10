package com.softserve.itacademy.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "todos")
public class ToDo {

	@Id
	@Column(name = "ID", insertable = false, updatable = false)
	@GeneratedValue
	@GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "user_sequence"),
			@Parameter(name = "initial_value", value = "1"), 
			@Parameter(name = "increment_size", value = "1") })
	private long id;
	
	@NotBlank(message = "The title cannot be empty")
	@Column(name = "Title", nullable = false)
	@Size(min = 1, max = 255, message = "the title must be from 1 to 255")
	private String title;
	
	@NotNull
	@Column(name = "Created_at", updatable=false)
	@Past
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "owner_id")
	private User user;
	
    @OneToMany(mappedBy = "toDo")
    private List<Task> tasks;
    
    @ManyToMany(mappedBy = "collaboratedTodos")
    Set<User> collaborators;
    
    public ToDo() {
		this.createdAt = LocalDateTime.now();
	}
    
    public long getId() {
		return id;
	}
    
    public LocalDateTime getCreatedAt() {
		return createdAt;
	}
    
    public String getTitle() {
		return title;
	}
    
    public void setTitle(String title) {
		this.title = title;
	}
    
    public User getOwner() {
		return user;
	}
    
    public void setOwner(User user) {
		this.user = user;
	}
    
    public List<Task> getTasks() {
		return tasks;
	}
    
    public Set<User> getCollaborators() {
		return collaborators;
	}
    
	@Override
	public boolean equals(Object obj) {
		return this.user.equals(((ToDo) obj).getOwner()) && this.id == ((ToDo) obj).id;
	}
	
	@Override
	public String toString() {
		return "ToDo " + title + " created at " + createdAt + " owner " + user.getFirstName() + " " + user.getLastName(); 
	}
}
