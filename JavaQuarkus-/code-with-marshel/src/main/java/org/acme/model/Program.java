package org.acme.model;


public class Program {
    public int program_id;

    public Program(String programId, String program_name) {
    }

    @Override
    public String toString() {
        return "Program{" +
                "program_id=" + program_id +
                ", program_name='" + program_name + '\'' +
                '}';
    }

    public String program_name;

    // person 1: Inputting ID and name
    public Program(int program_id, String program_name) {
        this.program_id = program_id; // building material
        this.program_name = program_name;
    }

    public Program(String program_name) {
        this.program_name = program_name;
    }

    // Person2: Go and get the id
    public int getProgram_id() {
        return program_id;
    }

    // Person3: Go and get the name
    public String getProgram_name() {
        return program_name;
    }


    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }
    public Program(){

    }
    public void displayInfo() {
        System.out.println("Program Name: " + program_id);
        System.out.println("program  name :" + program_name);
    }

    public void wait(Program program) {
    }
}

