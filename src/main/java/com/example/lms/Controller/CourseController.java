package com.example.lms.Controller;


import com.example.lms.ApiResponse.ApiResponse;
import com.example.lms.Model.Course;
import com.example.lms.Model.Instructor;
import com.example.lms.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lms-system-course")

public class CourseController {

    private final CourseService courseService;

    @GetMapping("/get")
    public ResponseEntity getCourses() {
        return ResponseEntity.status(200).body(courseService.getCourses());
    }

    @PostMapping("/add")
    public ResponseEntity addInstructor(@RequestBody @Valid Course course, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        course.setInstructorId("");
        boolean isAdded = courseService.addCourse(course);

        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Course added"));
        } else
            return ResponseEntity.status(400).body(new ApiResponse("Courses not added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity UpdateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated = courseService.updateCourse(id, course);

        if (isUpdated){
            return ResponseEntity.status(200).body("Course updated");
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Course id not exits"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCourse(@PathVariable String id){

        boolean isDeleted = courseService.deleteCourse(id);

        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted"));
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Course is not exits"));

    }


    @PutMapping("/update-status/{id}")
    public ResponseEntity updateCourseStatus(@PathVariable String id){

        boolean isUpdated = courseService.updateCourseStatus(id);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Course updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course is not exits"));
    }

    @PutMapping("update-start-date/{id}/{startDate}")
    public ResponseEntity updateCourseStartDate(@PathVariable String id, @PathVariable String startDate){
        boolean isUpdated = courseService.changeStartDate(id, startDate);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Course start date updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course is not exits"));

    }

    @PutMapping("/add-instructor/{id}/{instructorId}")
    public ResponseEntity addInstructor(@PathVariable String id, @PathVariable String instructorId){
        boolean isAdded = courseService.addInstructor(id, instructorId);
        if (isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("instructor added"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("Course is not exits"));
    }

    @GetMapping("get-instructor/{id}")
    public ResponseEntity getInstructor(@PathVariable String id){
        String instructor = courseService.getInstructor(id);
        if (instructor != null){
            return ResponseEntity.status(200).body(instructor);
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course is not exits"));
    }

}
