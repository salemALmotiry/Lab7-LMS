package com.example.lms.Service;


import com.example.lms.Model.Course;
import com.example.lms.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {


    ArrayList<Student> students = new ArrayList<Student>();


    public ArrayList<Student> getStudents() {
        return students;
    }

    public boolean addStudent(Student student) {
        for (Student var : students) {
            if (var.getStudentId().equals( student.getStudentId())) {
                return false;
            }
        }
        students.add(student);
        return true;
    }


    public boolean updateStudent(String id , Student student){

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(id)){
                students.set(i,student);
                return true;
            }
        }
        return false;

    }


    public boolean deleteStudent(String id){

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(id)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public String checkStudentHonor(String id){
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(id)){
                if (students.get(i).getGPA()>4.75)
                    return "First honor degree";
                else if(students.get(i).getGPA()>4.25)
                    return "Second honor degree";
                else
                    return "No honor degree";

            }
        }

        return null;


    }


    public boolean setAbsent(String id){

        for (Student student : students) {
            if (student.getStudentId().equals(id)) {
                if (student.getStudentAbsent() > 0) {
                    student.setStudentAbsent(student.getStudentAbsent() - 1);
                    return true;
                }
            }
        }

        return false;
    }

    public ArrayList<Student> studentsWithNoAbsent(){
        ArrayList<Student> tem = new ArrayList<>();

        for (Student student : students) {
            if (student.getStudentAbsent() == 9) {
                tem.add(student);
            }
        }
        return tem;
    }

    public String checkFW(String id){
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(id)){
                if(students.get(i).getStudentAbsent() == 0 ){
                    return "STUDENT WITHDRAWAL FAILING";
                }else {
                    return "Student still have absents";
                }
            }
        }
        return null;
    }
}
