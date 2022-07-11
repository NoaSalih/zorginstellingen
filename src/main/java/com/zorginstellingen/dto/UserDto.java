package com.zorginstellingen.dto;

import com.zorginstellingen.enums.Gender;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String email;
    private Boolean active;
    private String qualification;
    private String userDescription;
    private String accountNumber;
    private Gender gender;
    private String contactNumber;
    private String userRole;
}
