package zorginstellingen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zorginstellingen.enums.UserStatus;
import zorginstellingen.exception.DuplicateException;
import zorginstellingen.exception.EmptyException;
import zorginstellingen.exception.InvalidCredentialsException;
import zorginstellingen.exception.NotFoundException;
import zorginstellingen.model.Patient;
import zorginstellingen.model.User;
import zorginstellingen.repositories.PatientRepository;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private UserService userService;

    /**
     * return all the patients of particular doctor
     *
     * @param doctorId
     * @return
     * @throws NotFoundException
     */
    public List<Patient> getAllPatientsByDoctorId(Long doctorId) throws NotFoundException {
        User findUser = userService.findUserById(doctorId);
        return patientRepository.findPatientsByDoctorId(doctorId);
    }

    /**
     * Email should trigger to patient for registration and he will be register along with doctor
     *
     * @param patient
     * @return
     * @throws NotFoundException
     */
    public Patient addPatient(Patient patient) throws NotFoundException {
        User user = userService.findUserById(patient.getUserId());
        patient.setUser(user);
        if(patient.getUserStatus()==null)
            patient.setUserStatus(UserStatus.INACTIVE);
        // TODO: 12/06/2022 Trigger email to patient for registration
        return patientRepository.save(patient);
    }

    /**
     * will take username and password for particular patient and will return if patient found else exception
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
     * will take object containing only id will find patient and remove it if found
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
     * update patient information
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


    /**
     * only deleted by doctor
     * @param id
     * @return
     * @throws EmptyException
     * @throws DuplicateException
     * @throws NotFoundException
     */
    public Patient deleteById(Long id) throws EmptyException, DuplicateException, NotFoundException {
        if (id == null)
            throw new EmptyException("Please mention id of patient for update");
        Patient patient = findPatientByPatientId(id);
        patientRepository.delete(patient);

        return patient;
    }

    /**
     * Activate Patient Account
     * @param patient
     * @return
     * @throws NotFoundException
     */
    public Patient activePatientAccount(Patient patient) throws NotFoundException {
        Patient findPatient = findPatientByPatientId(patient.getId());
        findPatient.setUserStatus(UserStatus.ACTIVE);
        return patientRepository.save(findPatient);
    }

}