package com.mindex.challenge.data;

import java.util.List;

//this class is my take on the requirement for task 1
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    //Contructor for the Reporting Structure class, takes an employee as a parameter
    public ReportingStructure(Employee emp){
        this.employee = emp;        
        this.numberOfReports = navigateTree(employee);

    }

    //this is a recursive function to navigate the "tree" of employees and find the number of underlings for a given employee
    private int navigateTree(Employee emp){
        int count = 1;
        if(emp.getDirectReports() != null){
            for (int i = 0; i < emp.getDirectReports().size(); i++){
                count = navigateTree(emp.getDirectReports().get(i)) +1 ;
            }
        }
        return count;
    }

    public int getNumReps(){
        return this.numberOfReports;
    }

    public Employee getEmployee(){
        return this.employee;
    }
}
