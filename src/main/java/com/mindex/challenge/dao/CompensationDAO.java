package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

@Repository
public interface CompensationDAO extends MongoRepository<Compensation, String> {
    @Query("{ 'employee.employeeId' : ?0 }")
    Compensation findByEmployee_EmployeeId(String employeeId);
}
