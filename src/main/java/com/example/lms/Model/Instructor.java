package com.example.lms.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Instructor {

    @NotEmpty(message = "Enter instructor id")
    @Size(min = 10 , message = "Instructor id must be longer than 10 characters")
    private String instructorId;

    @NotEmpty(message = "Enter instructor name")
    @Size(min = 5 , message = "Instructor name length must be longer than 5 characters")
    @Pattern(regexp = "^[A-Za-z]+ [A-Za-z]+$", message = "Instructor name must valid name first and last name is required. No numbers allow")
    private String instructorName;

    @NotEmpty(message = "Enter instructor email")
    @Email(message = "Instructor email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @NotEmpty(message = "Instructor phone number cannot be empty")
    @Size(min = 10 , max = 14 , message = "phone number must be 10 digit ")
    @Pattern(regexp = "^(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$", message = "Phone number must be from Saudi Arabia")
    private String instructorPhoneNumber;

    @NotNull(message = "Enter instructor age")
    @Positive(message = "instructor age positive only")
    @Range(min = 25,max = 75 , message = "instructor age must be between 25 and 70 ")
    private int instructorAge;

    @NotNull(message = "hire date cannot be empty")
    @PastOrPresent(message = "hire date must be in the past or present date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    @AssertFalse(message = "onLeave must be initially set to false")
    private boolean onLeave;

    @JsonIgnore
    private ArrayList<String> coursesId ;

}
