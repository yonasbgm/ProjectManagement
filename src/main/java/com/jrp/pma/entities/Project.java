package com.jrp.pma.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="PROJECT")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long projectId;
	private String name;
	private String stage;	//NOTSTARTED, COMPLETED, INPROGRESS
	private String description;
	
	/*public void addEmployee(Employee emp) {
		if(employees==null) {
			employees = new ArrayList<>();
		}
	}*/

	@ManyToMany(cascade = { CascadeType.ALL},
			fetch = FetchType.LAZY)
	@JoinTable(name="project_employee",
			joinColumns=@JoinColumn(name="project_id"),
			inverseJoinColumns = @JoinColumn(name="employee_id")
	)
	private List <Employee> employees = employees = new ArrayList<>();;
	
	public Project() {
		
	}
	
	public Project(String name, String stage, String description) {
		super();
		this.name = name;
		this.stage = stage;
		this.description = description;
		
		
		
	}

	public List<Employee> getEmployees() {
		return Collections.unmodifiableList(employees);
		//return employees;
	}

	public void addEmployee(Employee employee){

		//avoid circular calls : assumes equals and hashcode implemented
		if(! employees.contains(employee)){
			employees.add(employee);

			//add method to Product : sets 'other side' of association
			employee.addProject(this);
		}
	}

	public void removeEmployee(Employee employee){

		//avoid circular calls: assumes equals and hashcode implemented:
		if(! employees.contains(employee)){
			employees.remove(employee);

			//add method to Product: set 'other side' of association:
			employee.removeProject(this);
		}
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
