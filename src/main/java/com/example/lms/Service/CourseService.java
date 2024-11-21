package com.example.lms.Service;

import com.example.lms.Model.Course;
import com.example.lms.Model.Instructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
@Service
public class CourseService {
    ArrayList<Course> courses = new ArrayList<Course>();


    public ArrayList<Course> getCourses() {
        return courses;
    }

    public boolean addCourse(Course course) {
        for (Course var : courses) {
            if (var.getCourseId().equals( course.getCourseId())) {
                return false;
            }
        }
        courses.add(course);
        return true;
    }


    public boolean updateCourse(String id , Course course){

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(id)){
                courses.set(i,course);
                return true;
            }
        }
        return false;

    }


    public boolean deleteCourse(String id){

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(id)){
                courses.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean updateCourseStatus(String id){

        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(id) && courses.get(i).getCourseStatus().equalsIgnoreCase("open")){
                courses.get(i).setCourseStatus("closed");
                return true;
            }
        }
        return false;
    }

    public boolean changeStartDate(String id,String startDate){
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(id)){
                courses.get(i).setCourseStartDate(LocalDate.parse(startDate));

                return true;
            }
        }
        return false;
    }

    public boolean addInstructor(String id,String instructorId){
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(id)){
               if (courses.get(i).getInstructorId().isEmpty()){
                   courses.get(i).setInstructorId(instructorId);
                   return true;
               }
            }
        }
        return false;
    }

    public String getInstructor(String id){
        for (int i = 0; i < courses.size(); i++) {
            if (courses.get(i).getCourseId().equals(id)){
                return courses.get(i).getInstructorId();
            }
        }
        return null;
    }
}
