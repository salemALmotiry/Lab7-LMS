package com.example.lms.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date.*;

@Data
@AllArgsConstructor
public class Course {

    @NotEmpty(message = "Enter course id")
    @Size(min = 5 , message = "Course id must be longer than 5 characters")
    private String courseId;

    @NotEmpty(message = "Enter course name")
    @Size(min = 5 , message = "course name length must be longer than 5 characters")
    private String courseName;

    @NotEmpty(message = "Enter course description")
    @Size(min = 20 , message = "Course description length must be longer than 20 characters")
    private String courseDescription;

    @NotEmpty(message = "Enter course location")
    @Size(min = 10 , message = "Course location length must be longer than 10 characters")
    private String courseLocation;

    @NotEmpty(message = "course type cannot be empty")
    @Pattern(regexp = "^(online|In-person)$", message = "Course type must be either online or In-person only")
    private String courseType;

    @NotEmpty(message = "course status cannot be empty")
    @Pattern(regexp = "^(Open)$", message = "Course status must be open only")
    private String courseStatus;

    @PastOrPresent(message = "Course start date must be past or present date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    private LocalDate courseStartDate;

    @Future(message = "Course end date must be in future")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")

    private LocalDate courseEndDate;

    @JsonIgnore
    private String instructorId;

}
