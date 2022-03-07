package com.example.demo;

public class Job {
    private int job_id;
    private String job_title;
    private double min_salary;
    private double max_salary;

    /**
     * Constructor with all attributes from this class
     * @param job_id Job ID number
     * @param job_title Job title
     * @param min_salary Minimum Salary for this job
     * @param max_salary Maximum Salary for this job
     */
    public Job(int job_id, String job_title, double min_salary, double max_salary) {
        this.job_id = job_id;
        this.job_title = job_title;
        this.min_salary = min_salary;
        this.max_salary = max_salary;
    }


    @Override
    /*
        toString method returns Job information in String format
     */
    public String toString() {
        return "Job Description" +
                "\nID: " + job_id +
                "\nTitle: " + job_title +
                "\nMinimum Salary: " + min_salary +
                "\nMaximum Salary: " + max_salary;
    }

    //Getters and Setters
    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public double getMin_salary() {
        return min_salary;
    }

    public void setMin_salary(double min_salary) {
        this.min_salary = min_salary;
    }

    public double getMax_salary() {
        return max_salary;
    }

    public void setMax_salary(double max_salary) {
        this.max_salary = max_salary;
    }

    public int getJob_id() {
        return job_id;
    }

    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }
}