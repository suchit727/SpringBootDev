package com.example.springvalidation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeModel {
    @NotNull(message = "Employee Name Shouldn't be null")
    private String employeeName;
    @Email(message = "Invalid Email Address")
    private String email;
    @Pattern(regexp = "(0/91)?[7-9][0-9]{9}")
    private String mobileNumber;
    @NotBlank
    private String gender;
    @Min(18)
    @Max(60)
    private Byte age;
    @NotBlank(message = "Nationality shouldn't be null")
    private String nationality;
}
