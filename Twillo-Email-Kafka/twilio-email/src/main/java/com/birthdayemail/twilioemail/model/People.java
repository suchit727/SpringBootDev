package com.birthdayemail.twilioemail.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class People {
    private String firstName;
    private String lastName;
    private String email_id;
}
