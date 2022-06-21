package zorginstellingen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import zorginstellingen.enums.AppointmentStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointment")
@Getter
@Setter
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String appointmentDescription;

    @CreationTimestamp
    private Date appointmentDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "fk_patient")
    private Patient patient; //which patients appointment is this

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "fk_doctor")
    private User user; //with doctor id

    @Transient
    private Long doctorId;

    private AppointmentStatus appointmentStatus;

    @Transient
    private Long patientId;


}
