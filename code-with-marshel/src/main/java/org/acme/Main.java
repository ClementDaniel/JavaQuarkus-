//package org.acme;
//
//class Main {
//    public static void main(String[] args) {
//        // Create a Program object with the list of courses
//        Program program = new Program("Akan");
//        int programId = Database.insertStudent(program);
//        program.setProgram_id(programId);
//
//        // Create a Department object with the list of courses
//        Department dept = new Department("Arts");
//        int deptId = Database.insertDepartment(dept);
//        dept.setDept_id(deptId);
//
//        // Create a Student object for "Mary"
//        Student student = new Student();
//        student.setfirstName("Kan");
//        student.setlastName("Alem");
//        student.setIndexNumber("13711");
//        student.setEmail("ka@gmail.com");
//        student.setDepartment(dept);
//        student.setProgram(program);
//
//        // Insert the student
//        int insertResult = Database.insertStudent(program);
//        System.out.println("Insert Student: " + insertResult);
//        if (insertResult == 0) {
//            System.out.println("Student not inserted");
//        }
//    }

        // Update the student
        // int updateResult = Database.updateStudentEmail("lo@gmail.com", student.getIndexNumber());
        // System.out.println("Update Student: " + updateResult);
        // if (updateResult == 0) {
        //     System.out.println("Student not updated");
        // }

        // Delete the student
        // int deleteResult = Database.deleteStudent(student.getIndexNumber());
        // System.out.println("Delete Student: " + deleteResult);
        // if (deleteResult == 0) {
        //     System.out.println("Student not deleted");
        // }
//    }
//}