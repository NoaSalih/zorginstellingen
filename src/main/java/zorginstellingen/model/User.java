package zorginstellingen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import zorginstellingen.enums.Gender;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "active")
    private Boolean active;

    @CreatedDate
    @Column(name = "created_date")
    @CreationTimestamp
    private Date createdDate;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "description")
    private String userDescription;

    @Column(name = "contact_number")
    private String contactNumber;

    private String email;

    private Gender gender;

    @Transient
    private Long roleId;

    @Transient
    private String token;

     @ManyToOne(cascade = CascadeType.ALL)
     @JoinColumn(name = "fk_role")
     private Role role;

}
