package com.mindex.challenge.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//this class is my take on the requirement for task 1
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructure.class);

    public ReportingStructure(){
        System.out.println("print rep str empty const");
    }
    //Contructor for the Reporting Structure class, takes an employee as a parameter
    public ReportingStructure(Employee emp){
        System.out.println("print rep str not empty const "+ emp.toString());
        this.employee = emp;        
        this.numberOfReports = navigateTree(employee);

    }

    //this is a recursive function to navigate the "tree" of employees and find the number of underlings for a given employee
    private int navigateTree(Employee emp){
        System.out.println("navigate tree "+ emp);
        int count = 0;
        if(emp.getDirectReports() != null){
            for (int i = 0; i < emp.getDirectReports().size(); i++){
                LOG.debug("Navigating direct report: {}", emp.getEmployeeId());
                System.out.println("print navigate tree loop");
                count += navigateTree(emp.getDirectReports().get(i)) +1 ;
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

    public void setNumReps(){
        System.out.println("set num reps");
        this.numberOfReports = navigateTree(this.employee);
    }

    public void setEmployee(Employee emp){
        System.out.println("set employee");
        this.employee = emp;
    }
}
