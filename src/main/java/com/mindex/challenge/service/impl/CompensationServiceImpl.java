package com.mindex.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationDAO;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;

//this is my implementation of my CompensationService interface based on how I learned to do this and using 
//what was provided in EmployeeServiceImpl.java
@Service
public class CompensationServiceImpl implements CompensationService{

    @Autowired
    private CompensationDAO compDAO;

    public CompensationServiceImpl() {

    }

    public Compensation create(Compensation compensation) {
        // Ensure that the Employee is not null before saving
        if (compensation.getEmployee() == null) {
            throw new IllegalArgumentException("Employee cannot be null");
        }
    
        // Save compensation (e.g., in the repository)
        return compDAO.save(compensation);
    }
    
    @Override
    public Compensation read(String empID){
        //tried an if statement to check if empID was null, but it still didn't like it
        //found .orElse() on Stack Overflow and it doesn't show an error anymore, so that's nice
        return compDAO.findById(empID).orElse(null);
        
    }
}
