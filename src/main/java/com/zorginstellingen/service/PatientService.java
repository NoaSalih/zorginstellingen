package com.zorginstellingen.service;


import com.zorginstellingen.dto.UserDto;
import com.zorginstellingen.enums.UserStatus;
import com.zorginstellingen.exception.*;
import com.zorginstellingen.model.Patient;
import com.zorginstellingen.model.User;
import com.zorginstellingen.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    /**
     * geeft terug alle patients van bijhorende doctor
     *
     * @param doctorId
     * @return
     * @throws NotFoundException
     */
    public List<Patient> getAllPatientsByDoctorId(Long doctorId) throws NotFoundException {
        List<Patient> patients =  patientRepository.findPatientsByDoctorId(doctorId);
        patients.forEach(patient -> {
            patient.setDoctorName(patient.getUser().getUsername());
        });
        return patients;
    }

    /**
     *
     * @param patient
     * @return
     * @throws NotFoundException
     */
    @Transactional
    public Patient addPatient(Patient patient) throws NotFoundException, PasswordException, ResourceAlreadyExists {
        if(patient.getUserStatus()==null)
            patient.setUserStatus(UserStatus.ACTIVE);

        User doctor = userService.findUserById(patient.getUserId());
        patient.setUser(doctor);
        UserDto userDto = new UserDto();
        userDto.setUsername(patient.getUsername());
        userDto.setPassword(patient.getPassword());
        userDto.setEmail(patient.getEmail());
        userDto.setUserRole("PATIENT");
        userDto.setContactNumber(patient.getContactNumber());
        userDto.setQualification("NULL");
        userDto.setGender(patient.getGender());
        userDto.setActive(true);
        userDto.setUserDescription("Patient has no description");

        User saveUser = userService.register(userDto);
        return patientRepository.save(patient);
    }

    /**
     *
     * @param patient
     * @return
     * @throws InvalidCredentialsException
     */
    public Patient loginAsPatient(Patient patient) throws InvalidCredentialsException {
        List<Patient> findPatient = patientRepository.findByUserPassword(patient.getUsername(), patient.getPassword());
        if (findPatient == null)
            throw new InvalidCredentialsException("Invalid Credentials");
        return findPatient.get(0);
    }

    /**
     *
     * @param patient
     * @return
     * @throws InvalidCredentialsException
     * @throws NotFoundException
     */
    public Patient removePatient(Patient patient) throws InvalidCredentialsException, NotFoundException {
        Patient findPatient = patientRepository.findById(patient.getId()).orElseThrow(() -> new NotFoundException("Patient not found"));
        patientRepository.delete(findPatient);
        return findPatient;
    }

    /**
     *
     * @param patient
     * @return
     * @throws InvalidCredentialsException
     * @throws NotFoundException
     */
    public Patient updatePatientInfo(Patient patient) throws InvalidCredentialsException, NotFoundException {
        Patient findPatient = patientRepository.findById(patient.getId()).orElseThrow(() -> new NotFoundException("Patient not found"));
        return patientRepository.save(patient);
    }


    /**
     * @param patientId
     * @return
     * @throws NotFoundException
     */
    public Patient findPatientByPatientId(Long patientId) throws NotFoundException {
        return patientRepository.findById(patientId).orElseThrow(() -> new NotFoundException("Patient not found for id = " + patientId));
    }

    public List<Patient> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        patients.forEach(patient ->{
            patient.setDoctorName(patient.getUser().getUsername());
        });
        return patients;
    }


    public Patient deleteById(Long id) throws EmptyException, DuplicateException, NotFoundException {
        if (id == null)
            throw new EmptyException("Patient ID niet gevonden");
        Patient patient = findPatientByPatientId(id);
        patientRepository.delete(patient);

        return patient;
    }


    public Patient activePatientAccount(Patient patient) throws NotFoundException {
        Patient findPatient = findPatientByPatientId(patient.getId());
        findPatient.setUserStatus(UserStatus.ACTIVE);
        return patientRepository.save(findPatient);
    }

    public Patient findPatientByUsername(String username) {
        return patientRepository.findByUsername(username);
    }
}