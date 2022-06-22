package zorginstellingen.dtos;

import lombok.*;
import zorginstellingen.enums.Gender;

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
    private Long roleId;
}
