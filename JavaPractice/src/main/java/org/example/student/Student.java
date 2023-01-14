package org.example.student;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String result;
}
