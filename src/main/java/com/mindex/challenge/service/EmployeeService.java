package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

//I think that only create is neeeded for ReportingStructure, as no requirements
    //were stated where it would need to be changed, and the lack of a delete for Employee
    //indicated that I wouldn't need one for ReportingStructure
public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
    ReportingStructure createRepStr(String empID);
}
