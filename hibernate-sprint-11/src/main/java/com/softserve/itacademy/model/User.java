package com.softserve.itacademy.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "ID", insertable = false, updatable = false)
	@GeneratedValue
	@GenericGenerator(name = "sequence-generator", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@Parameter(name = "sequence_name", value = "user_sequence"),
			@Parameter(name = "initial_value", value = "10"),
			@Parameter(name = "increment_size", value = "1") })
	private long id;

	@NotBlank(message = "The first_name cannot be empty")
	@Column(name = "First_Name", nullable = false)
	@Pattern(regexp = "[A-Z]\\w*(-[A-Z]\\w*)?", message = "Please provide a valid name like Ann or Ann-Mary")
	@Size(min = 1, max = 255, message = "the first name range must be from 1 to 255")
	private String first_name;

	@NotBlank(message = "The last_ame cannot be empty")
	@Column(name = "Last_Name", nullable = false)
	@Pattern(regexp = "[A-Z]\\w*(-[A-Z]\\w*)?", message = "Please provide a valid last name like Colt or Smith-Wesson")
	@Size(min = 1, max = 255, message = "the last name range must be from 1 to 255")
	private String last_name;

	@Column(name = "email", nullable = false, unique = true)
	@Email(regexp = ".+@.+\\..+", message = "Please provide a valid email address")
	@Size(max = 255, message = "the email range must be from 1 to 255")
	private String email;

	@NotBlank(message = "The password cannot be empty")
	@Column(name = "Password", nullable = false)
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[_!@#$%\\^&\\*]).{6,}$", message = "Please provide a valid password like 123456_!qQ")
	@Size(min = 1, max = 255, message = "the password range must be from 1 to 255")
	private String password;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<ToDo> myTodos;
	
	@ManyToMany
	@JoinTable(name = "todo_collaborator",
			joinColumns = @JoinColumn(name = "collaborator_id"),
			inverseJoinColumns = @JoinColumn(name = "todo_id"))
	Set<ToDo> collaboratedTodos;

	public User() {
	}

	public long getId() {
		return id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public List<ToDo> getMyTodos() {
		return myTodos;
	}
	
	public Set<ToDo> getCollaboratedTodos() {
		return collaboratedTodos;
	}

	@Override
	public boolean equals(Object obj) {
		return this.id == ((User) obj).id;
	}

	@Override
	public String toString() {
		return "User[id=" + id + ", firsName=" + first_name + ", lastName=" + last_name + ", " + role + "]";
	}

}
