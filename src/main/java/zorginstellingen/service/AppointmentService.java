package zorginstellingen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zorginstellingen.enums.AppointmentStatus;
import zorginstellingen.exception.EmptyException;
import zorginstellingen.exception.NotFoundException;
import zorginstellingen.model.Appointment;
import zorginstellingen.repositories.AppointmentRepository;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PatientService patientService;
    /**

     * for ADMIN to check all the appointments of all the doctors
     * @return
     */
    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }
    public List<Appointment> getAllAppointmentsByDoctorId(Long userId){
        return appointmentRepository.findByDoctorId(userId);
    }

    /**
     * @param patientId
     * @return
     */
    public List<Appointment> getAllAppointmentsByPatientId(Long patientId){
        return appointmentRepository.findByPatientId(patientId);
    }


    /**
     *
     * @param appointment
     * @return
     * @throws EmptyException
     * @throws NotFoundException
     */
    public Appointment applyForAppointment(Appointment appointment) throws EmptyException, NotFoundException, NotFoundException {
        // TODO: 12/06/2022 Email trigger to doctor
        if (appointment.getPatientId()==null)
            throw new EmptyException("Please mention patient id");
        if (appointment.getDoctorId()==null)
            throw new EmptyException("Please mention doctor id");
        if (appointment.getAppointmentDescription()==null)
            throw new EmptyException("Please mention appointment description");
        appointment.setUser(userService.findUserById(appointment.getDoctorId()));
        appointment.setPatient(patientService.findPatientByPatientId(appointment.getPatientId()));
        appointment.setAppointmentStatus(AppointmentStatus.INPROGRESS);
        return appointmentRepository.save(appointment);
    }

    /**
     * by only doctor and then email will be trigger to doctor
     * @param appointment
     * @return
     * @throws EmptyException
     * @throws NotFoundException
     */
    public Appointment updateAppointment(Appointment appointment) throws EmptyException, NotFoundException {
        // TODO: 12/06/2022 Email trigger to patient
       Appointment findApp = appointmentRepository.findById(appointment.getId()).orElseThrow(()-> new NotFoundException("Appointment not found"));
       if (appointment.getDoctorId()!=null)
           findApp.setUser(userService.findUserById(appointment.getDoctorId()));
        if (appointment.getPatientId()!=null)
            findApp.setPatient(patientService.findPatientByPatientId(appointment.getPatientId()));
       if (appointment.getAppointmentDescription()!=null)
           findApp.setAppointmentDescription(appointment.getAppointmentDescription());
        return appointmentRepository.save(findApp);
    }

    /**
     *
     * @param id
     * @return
     * @throws EmptyException
     * @throws NotFoundException
     */
    public Appointment deleteAppointment(Long id) throws EmptyException, NotFoundException {
        Appointment findApp = appointmentRepository.findById(id).orElseThrow(()-> new NotFoundException("Appointment not found"));
        appointmentRepository.delete(findApp);
        return findApp;
    }

    /**
     * accessible by all ROLES
     * @param id
     * @return
     * @throws NotFoundException
     */
    public Appointment findAppointmentById(Long id) throws NotFoundException {
        Appointment appointment = appointmentRepository.findById(id).orElseThrow(()->  new NotFoundException("Record not found"));
        return appointment;
    }


}
