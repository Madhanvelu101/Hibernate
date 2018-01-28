package com.hibernate.pack;

import java.util.Set;

public class Employee {
private int id;
private String firstName;
private String lastName;
private int salary;
private Set certificates;
public Employee() {}

public Employee(String fname, String lname, int salary) {
   this.firstName = fname;
   this.lastName = lname;
   this.salary = salary;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public int getSalary() {
	return salary;
}
public void setSalary(int salary) {
	this.salary = salary;
}
public Set getCertificates() {
	return certificates;
}
public void setCertificates(Set certificates) {
	this.certificates = certificates;
}

}
