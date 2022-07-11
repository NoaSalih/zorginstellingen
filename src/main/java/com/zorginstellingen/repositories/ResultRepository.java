package com.zorginstellingen.repositories;

import com.zorginstellingen.model.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {
    @Query("select result from Result  result where result.patient.user.id=?1")
    List<Result> findByDoctorId(Long doctorId);
}
