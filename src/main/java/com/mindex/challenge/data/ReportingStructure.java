package com.mindex.challenge.data;

import com.mindex.challenge.service.EmployeeService;

//this class is my take on the requirement for task 1
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(){

    }
    //Contructor for the Reporting Structure class, takes an employee as a parameter
    public ReportingStructure(Employee emp, EmployeeService empSer){
        this.employee = emp;        
        this.numberOfReports = navigateTree(employee, empSer);

    }

    //this is a recursive function to navigate the "tree" of employees and find the number of underlings for a given employee
    public int navigateTree(Employee emp, EmployeeService employeeService) {
    int count = 0; // Start at 0 (only count subordinates, not the given employee)

    if (emp.getDirectReports() != null) {
        for (Employee e : emp.getDirectReports()) {
            Employee fullEmployee = employeeService.read(e.getEmployeeId()); // Fetch full employee object
            count += 1 + navigateTree(fullEmployee, employeeService); // Recursively count reports
        }
    }

    return count;
}
    

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public void setNumReps(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }

    public int getNumReps(){
        return this.numberOfReports;
    }

    public Employee getEmployee(){
        return this.employee;
    }
}
