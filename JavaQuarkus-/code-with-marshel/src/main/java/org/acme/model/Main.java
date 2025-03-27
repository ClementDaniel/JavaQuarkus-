//package org.acme.model;
//
//import org.acme.service.StudentService;
//
//import static org.acme.service.StudentService.insertStudent;
//
//class Main {
//    public void main(String[] args) {
//        // Create a Program object with the list of courses
//        Program program = new Program("65", "swahili");
////        int programId = Database.insertStudent(program);
//
//
//        // Create a Department object with the list of courses
//        Department dept = new Department("64", "language");
////        int deptId = Database.insertDepartment(dept);
//
//
//        // Create a Student object for "Mary"
//        Student student = new Student();
//        student.setFirstName("Ann");
//        student.setLastName("Ntmi");
//        student.setIndexNumber("S19711");
//        student.setEmail("an@gmail.com");
//        student.setDepartment(dept);
//        student.setProgram(program);
////        dept.setDept_id(String.valueOf(deptId));
//        int programId = StudentService.insertStudent(student);
//
//        // Insert the student
//        int insertResult = StudentService.insertStudent(student);
//        System.out.println("Insert Student: " + insertResult);
//        if (insertResult == 0) {
//            System.out.println("Student not inserted");
//        }
//
//        // Update the student
////        int updateResult = Database.updateStudentEmail("lo@gmail.com", student.getIndexNumber());
////        System.out.println("Update Student: " + updateResult);
////        if (updateResult == 0) {
////            System.out.println("Student not updated");
////        }
//
//        // Delete the student
////        int deleteResult = Database.deleteStudent(student.getIndexNumber());
////        System.out.println("Delete Student: " + deleteResult);
////        if (deleteResult == 0) {
////            System.out.println("Student not deleted");
//        }
//    }
//
//
//
