package zorginstellingen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zorginstellingen.model.Appointment;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select app from Appointment app where app.user.id=?1")
    List<Appointment> findByDoctorId(Long userId);

    @Query("select app from Appointment app where app.patient.id=?1")
    List<Appointment> findByPatientId(Long patientId);
}
