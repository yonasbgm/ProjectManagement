package com.jrp.pma.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long employeeId;	
	private String firstName;
	private String lastName;
	private String email;

	@ManyToMany(cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	@JoinTable(name="project_employee",
			joinColumns=@JoinColumn(name="employee_id"),
			inverseJoinColumns = @JoinColumn(name="project_id")
	)
	private List<Project> projects = new ArrayList <>();;
	
	public Employee() {
		
	}
	
	public Employee(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public List<Project> getProjects() {
		return Collections.unmodifiableList(projects);
		//return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
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


	public void addProject(Project project) {
		if(! projects.contains(project) ){
			projects.add(project);

			//add method to Product : sets 'other side' of association
			project.addEmployee(this);
		}
	}

	public void removeProject(Project project) {
		if(! projects.contains(project) ){
			projects.remove(project);
			project.removeEmployee(this);
		}
	}


}
