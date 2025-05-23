package org.acme.model;


public class Department {
    public int dept_id;
    public String dept_name;

    public int getDept_id() {
        return dept_id;
    }

    // Default constructor required by JSON-B
    public Department() {
    }

    public Department(int dept_id, String dept_name) {
        this.dept_id = dept_id;
        this.dept_name = dept_name;
    }

    public Department(String dept_name) {
        this.dept_name = dept_name;
    }



    @Override
    public String toString() {
        return "Department{" +
                "dept_id=" + dept_id +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public static void main(String[] args) {
        String[] dept_name = {"Bsc Physics", "Bsc Computer Science", "Bsc Mathematics", "Bsc Music"};

    }
}


