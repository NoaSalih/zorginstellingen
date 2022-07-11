package com.zorginstellingen.service;

import com.zorginstellingen.exception.EmptyException;
import com.zorginstellingen.exception.NotFoundException;
import com.zorginstellingen.model.Result;
import com.zorginstellingen.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private PatientService patientService;


    public List<Result> getAllResults(){
        return resultRepository.findAll();
    }


    public Result addResult(Result result) throws EmptyException, NotFoundException {
        if (result.getId()==null)
            throw new EmptyException("Result niet gevonden");
        if (result.getPatient()==null)
            throw new EmptyException("Patient niet gevonden");
        if (result.getResultDescription()==null)
            throw new EmptyException("Testuitslag niet gevonden");
        result.setPatient(patientService.findPatientByPatientId(result.getPatientId()));

        return resultRepository.save(result);
    }


    public Result updateResult(Result result) throws EmptyException, NotFoundException {
        Result result1 = resultRepository.findById(result.getId()).orElseThrow(()-> new NotFoundException("uitslag niet gevonden"));
        return resultRepository.save(result);
    }


    public Result deleteResult(Long id) throws EmptyException, NotFoundException {
        Result result = resultRepository.findById(id).orElseThrow(()-> new NotFoundException("uitslag niet gevonden"));
        resultRepository.delete(result);
        return result;
    }


    public Result findResultById(Long id) throws NotFoundException {
        return resultRepository.findById(id).orElseThrow(()->  new NotFoundException("uitslag niet gevonden"));
    }

    public List<Result> getAllResultsByDoctorId(Long doctorId) {
        return resultRepository.findByDoctorId(doctorId);
    }
}
