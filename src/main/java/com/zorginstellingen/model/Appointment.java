package com.zorginstellingen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zorginstellingen.enums.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    private Patient patient; //bij welke patient hoort deze afspraak

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "fk_doctor")
    private User user; //met doctor id

    @Transient
    private Long doctorId;
    @Transient
    private String doctorName;
    @Transient
    private String patientName;

    private AppointmentStatus appointmentStatus;

    @Transient
    private Long patientId;


}
