package com.example.lms.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty(message = "Enter student id")
    @Size(min = 10 , message = "Student id must be longer than 10 characters")
    private String studentId;

    @NotEmpty(message = "Enter student name")
    @Size(min = 5 , message = "Student name length must be longer than 5 characters")
    @Pattern(regexp = "^[A-Za-z]+ [A-Za-z]+$", message = "student name must valid name first and last name is required. No numbers allow")
    private String studentName;

    @NotEmpty(message = "Enter student email")
    @Email(message = "Student email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotEmpty(message = "Student phone number cannot be empty")
    @Size(min = 10 , max = 14 , message = "student phone number must be 10 digit ")
    @Pattern(regexp = "^(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$", message = "Phone number must be from Saudi Arabia")

    private String studentPhoneNumber;

    @NotNull(message = "Enter student age")
    @Positive(message = "student age positive only")
    @Range(min = 18,max = 25 , message = "student age must be between 25 and 70 ")
    private int studentAge;



    @NotNull(message = "enter student")
    private double GPA ;

    @NotNull(message = "Student absent cannot be empty")
    @Positive(message = "Student absent positive only")
    @Max(value = 9, message = "Max absent is 9")
    private int studentAbsent;



}
