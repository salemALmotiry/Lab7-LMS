package com.example.lms.Service;

import com.example.lms.Model.Instructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InstructorService {

    ArrayList<Instructor> instructors = new ArrayList<Instructor>();

    ArrayList<String> course = new ArrayList<>();

    public ArrayList<Instructor> getInstructors() {
        return instructors;
    }

    public boolean addInstructor(Instructor instructor) {
        for (Instructor var : instructors) {
            if (var.getInstructorId().equals( instructor.getInstructorId())) {
                return false;
            }
        }
        instructor.setCoursesId(course);
        instructors.add(instructor);

        return true;
    }


    public boolean updateInstructor(String id , Instructor instructor){

        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getInstructorId().equals(id)){
                instructors.set(i,instructor);
                return true;
            }
        }
        return false;

    }


    public boolean deleteInstructor(String id){

        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getInstructorId().equals(id)){
                instructors.remove(i);
                return true;
            }
        }
        return false;
    }


    public boolean updateOnLeave(String id){
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getInstructorId().equals(id)){
                if (instructors.get(i).isOnLeave()){
                    instructors.get(i).setOnLeave(true);
                    return true;
                }else {
                    instructors.get(i).setOnLeave(false);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addCourse(String id ,String courseId){

        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getInstructorId().equals(id) && ! instructors.get(i).isOnLeave() ){

                if (instructors.get(i).getCoursesId().isEmpty()){
                    instructors.get(i).getCoursesId().add(courseId);
                    return true;
                }

               for (String cId : instructors.get(i).getCoursesId()){
                   if (cId.equals(courseId)){
                       return false;
                   }
               }
                instructors.get(i).getCoursesId().add(courseId);
                return true;
            }
        }
        return false;

    }

    public ArrayList<String> getCourse(String id) {
        for (int i = 0; i < instructors.size(); i++) {
            if (instructors.get(i).getInstructorId().equals(id)){
                return instructors.get(i).getCoursesId();
            }
        }
        return null;
    }


}
