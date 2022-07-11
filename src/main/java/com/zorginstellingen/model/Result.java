package com.zorginstellingen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "results")
@Getter
@Setter
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String resultDescription;

    @CreationTimestamp
    @CreatedDate
    private Date createdDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    @JoinColumn(name = "fk_patient")
    private Patient patient;

    @Transient
    private  Long patientId;


}
