package com.example.demo;

/**
 * Public class for employee
 */
public class Employee {
    private Integer employee_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private Integer job_id;
    private double salary;
    private Integer manager_id;
    private Integer department_id;

    /**
     *  ToString method to print out the information from the Employee class instance
     * @return Employee information in String format
     */
    @Override
    public String toString() {
        return  "Employee:\n" +
               "Name: " + first_name + " " + last_name +"\n\nContact Information\nPhone: " + phone_number +
                ", Email: " + email +
                "\n\nJob Description\nEmployee Job Id: " + job_id +
                "\nEmployee Salary: " + salary + "\nDepartment ID: " + department_id;
    }

    /**
     * Constructor with all attributes of Employee Class
     * @param employee_id Employee ID
     * @param first_name Employee First Name
     * @param last_name Employee Last Name
     * @param email Email Address
     * @param phone_number Phone Number
     * @param job_id Employee Job ID
     * @param salary Employee Salary
     * @param manager_id Manager ID
     * @param department_id Department ID
     */
    public Employee(int employee_id, String first_name, String last_name,
                    String email, String phone_number,
                    int job_id, double salary, int manager_id, int department_id) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.job_id = job_id;
        this.salary = salary;
        this.manager_id = manager_id;
        this.department_id = department_id;
    }

    /**
     * Getter method for employee id
     * @return  employee id
     */
    public Integer getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    /**
     * Getter method for employee first name
     * @return  Employee's first name
     */
    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Getter method for employee last name
     * @return Employee's last name
     */
    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Getter method for email attribute
     * @return  email
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter method for employee phone number
     * @return Employee's phone number
     */
    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * Getter method for employee job id
     * @return  Employee's job id
     */
    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Integer getManager_id() {
        return manager_id;
    }

    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }

    /**
     * Getter method for department id
     * @return department id
     */
    public Integer getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(Integer department_id) {
        this.department_id = department_id;
    }

    /**
     * Default constructor for employee class
     */
    public Employee() {

    }

}
