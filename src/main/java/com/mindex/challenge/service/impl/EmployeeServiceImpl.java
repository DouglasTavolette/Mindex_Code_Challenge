package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    //This is my method for checking a ReportingStructure for the API
    //The reason why I decided to write the new code in this file as opposed creating my own
        //is because I wanted access to the employeeRepository that is already being populated by
        //the prewritten code. I figured it would be easier to do it this way as opposed to creating a 
        //duplicate employeeRepository that contained the exact same contents. I also considered making
        //the original repository public instead of private, but I am under the impression that I should not 
        //change any prewritten code.
    //
    @Override
    public ReportingStructure createRepStr(String empID) {
        Employee rootEmployee = employeeRepository.findByEmployeeId(empID);
        if (rootEmployee == null) {
            throw new RuntimeException("Invalid employeeId: " + empID);
        }

        // Ensure direct reports are fully populated
        populateFullEmployee(rootEmployee);

        return new ReportingStructure(rootEmployee);
    }

    // Recursively replace Employee references with full Employee objects
    private void populateFullEmployee(Employee emp) {
        if (emp.getDirectReports() != null) {
            for (int i = 0; i < emp.getDirectReports().size(); i++) {
                Employee partialEmployee = emp.getDirectReports().get(i);
                Employee fullEmployee = employeeRepository.findByEmployeeId(partialEmployee.getEmployeeId());
                emp.getDirectReports().set(i, fullEmployee);
                populateFullEmployee(fullEmployee); // Recurse
            }
        }
    }

}
