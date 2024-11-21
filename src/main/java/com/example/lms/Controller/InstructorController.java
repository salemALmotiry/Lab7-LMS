package com.example.lms.Controller;


import com.example.lms.ApiResponse.ApiResponse;
import com.example.lms.Model.Instructor;
import com.example.lms.Service.InstructorService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/lms-system-instructor")
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping("/get")
    public ResponseEntity getInstructor() {
        return ResponseEntity.status(200).body(instructorService.getInstructors());
    }

    @PostMapping("/add")
    public ResponseEntity addInstructor(@RequestBody @Valid Instructor instructor, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isAdded = instructorService.addInstructor(instructor);

        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Instructor added"));
        } else
            return ResponseEntity.status(400).body(new ApiResponse("Instructor not added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity UpdateInstructor(@PathVariable String id, @RequestBody @Valid Instructor instructor, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated = instructorService.updateInstructor(id,instructor);

        if (isUpdated){
            return ResponseEntity.status(200).body("Instructor updated");
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Instructor id not exits"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteInstructor(@PathVariable String id){

        boolean isDeleted = instructorService.deleteInstructor(id);

        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Instructor deleted"));
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Instructor is not exits"));

    }


    @PutMapping("/update-onleave/{id}")
    public ResponseEntity updateInstructorOnLeave(@PathVariable String id){
        boolean isUpdated = instructorService.updateOnLeave(id);
        if (isUpdated){
            return ResponseEntity.status(200).body(new ApiResponse("Instructor OnLeave updated"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("Instructor is not exits"));
    }

    @PutMapping("/add-course/{id}/{cId}")
    public ResponseEntity addCourseInstructor(@PathVariable String id, @PathVariable String cId){

        boolean isAddCourse = instructorService.addCourse(id,cId);
        if (isAddCourse){
            return ResponseEntity.status(200).body(new ApiResponse("Course added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Course not added"));
    }

    @GetMapping("/get-courses/{id}")
    public ResponseEntity getCourseInstructor(@PathVariable String id){
        if (instructorService.getCourse(id)==null){
            return ResponseEntity.status(400).body(new ApiResponse("Courses not found"));
        }
        return ResponseEntity.status(200).body(instructorService.getCourse(id));
    }

}



