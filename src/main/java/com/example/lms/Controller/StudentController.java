package com.example.lms.Controller;


import com.example.lms.ApiResponse.ApiResponse;
import com.example.lms.Model.Instructor;
import com.example.lms.Model.Student;
import com.example.lms.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lms-system-student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;


    @GetMapping("/get")
    public ResponseEntity getStudent() {
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors) {

        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isAdded = studentService.addStudent(student);

        if (isAdded) {
            return ResponseEntity.status(200).body(new ApiResponse("Student added"));
        } else
            return ResponseEntity.status(400).body(new ApiResponse("Student not added"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity UpdateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        boolean isUpdated = studentService.updateStudent(id,student);

        if (isUpdated){
            return ResponseEntity.status(200).body("Student updated");
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Student id not exits"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){

        boolean isDeleted = studentService.deleteStudent(id);

        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted"));
        }else
            return ResponseEntity.status(400).body(new ApiResponse("Student is not exits"));

    }

    @GetMapping("/get-honor/{id}")
    public ResponseEntity getHonorStudent(@PathVariable String id){

        String honor = studentService.checkStudentHonor(id);

        if (honor != null){
            return ResponseEntity.status(200).body(new ApiResponse (honor));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not exit"));
    }

    @GetMapping("/get-FW/{id}")
    public ResponseEntity getFWStudent(@PathVariable String id){

        String fw = studentService.checkFW(id);

        if (fw != null){
            return ResponseEntity.status(200).body(new ApiResponse(fw));

        }

        return ResponseEntity.status(400).body(new ApiResponse("Student not exit"));
    }


    @GetMapping("/student-with-no-absent")
    public ResponseEntity getStudentWithNoAbsent(){
        if (studentService.studentsWithNoAbsent()==null){
            return ResponseEntity.status(200).body(new ApiResponse("there no student without absent"));
        }
        return ResponseEntity.status(200).body(studentService.studentsWithNoAbsent());
    }

    @PutMapping("/add-absent/{id}")
    public ResponseEntity addAbcentStudent(@PathVariable String id){
        boolean isAbsent = studentService.setAbsent(id);
        if (isAbsent){
            return ResponseEntity.status(200).body(new ApiResponse("Absent added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Student not found"));
    }

}
