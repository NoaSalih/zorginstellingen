package zorginstellingen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import zorginstellingen.model.Patient;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("select patient from Patient patient where patient.user.id=?1")
    List<Patient> findPatientsByDoctorId(Long doctorId);

    @Query("select patient from Patient patient where patient.username=?1 and patient.password=?2 and patient.userStatus=0")
    List<Patient> findByUserPassword(String username, String password);
}
