package zorginstellingen.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zorginstellingen.exception.EmptyException;
import zorginstellingen.exception.NotFoundException;
import zorginstellingen.model.Result;
import zorginstellingen.repositories.ResultRepository;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private PatientService patientService;

    /**
     * only accessed by ADMIN
     * @return
     */
    public List<Result> getAllResults(){
        return resultRepository.findAll();
    }

    /**
     * when adding result the email should be sent to patient
     * @param result
     * @return
     * @throws EmptyException
     * @throws NotFoundException
     */
    public Result addResult(Result result) throws EmptyException, NotFoundException {
        // TODO: 12/06/2022 Email trigger to doctor
        if (result.getId()==null)
            throw new EmptyException("Please mention result id");
        if (result.getPatient()==null)
            throw new EmptyException("Please mention patient id");
        if (result.getResultDescription()==null)
            throw new EmptyException("Please mention result description");
        result.setPatient(patientService.findPatientByPatientId(result.getPatientId()));

        return resultRepository.save(result);
    }

    /**
     * only updated by doctor
     * @param result
     * @return
     * @throws EmptyException
     * @throws NotFoundException
     */
    public Result updateResult(Result result) throws EmptyException, NotFoundException {
        // TODO: 12/06/2022 Email trigger to patient
        Result result1 = resultRepository.findById(result.getId()).orElseThrow(()-> new NotFoundException("result not found"));
        return resultRepository.save(result);
    }

    /**
     * Only updated by doctor
     * @param id
     * @return
     * @throws EmptyException
     * @throws NotFoundException
     */
    public Result deleteResult(Long id) throws EmptyException, NotFoundException {
        Result result = resultRepository.findById(id).orElseThrow(()-> new NotFoundException("result not found"));
        resultRepository.delete(result);
        return result;
    }

    /**
     * accessible by all ROLES
     * @param id
     * @return
     * @throws NotFoundException
     */
    public Result findResultById(Long id) throws NotFoundException {
        return resultRepository.findById(id).orElseThrow(()->  new NotFoundException("Record not found"));
    }

    public List<Result> getAllResultsByDoctorId(Long doctorId) {
        return resultRepository.findByDoctorId(doctorId);
    }
}
