package zorginstellingen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import zorginstellingen.enums.Gender;
import zorginstellingen.enums.UserStatus;

import javax.persistence.*;

@Entity
@Table(name = "patient")
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "patient_name")
    private String patientName;

    @Column(name = "address")
    private String patientAddress;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "account_number")
    private String  accountNumber;

    @Column(name = "blood_group")
    private String bloodGroup;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Transient
    private String doctorName;

    @Column(name = "gender")
    private Gender gender;

    private UserStatus userStatus;
    @Transient
    private Long userId; //doctor id

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "fk_user")
    private User user; //whose doctor is this patient





}
