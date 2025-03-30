package com.mindex.challenge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;

//Here is my controller class for the compensation endpoints
@RestController
public class CompensationController {
    @Autowired
    private CompensationService compensationService;


    @PostMapping("/compensation")
    public Compensation createCompensation(@RequestBody Compensation compensation) {
        if (compensation.getEmployee() == null) {
            System.out.println("Employee is null in the received compensation object");
        }

        // Create or save the Compensation object (assuming it's correctly saved)
        return compensationService.create(compensation);
    }





    @GetMapping("/compensation/{emp}")
    public Compensation getCompensation(@PathVariable Employee emp) {
        return compensationService.read(emp.getEmployeeId());
    } 
}
