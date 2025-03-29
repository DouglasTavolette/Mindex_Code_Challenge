package com.mindex.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChallengeApplicationTests {

	private String employeeUrl;
    private String employeeIdUrl;

    @Autowired
    private EmployeeService employeeService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        employeeUrl = "http://localhost:" + port + "/employee";
        employeeIdUrl = "http://localhost:" + port + "/employee/{id}";
    }

	@Test
	public void contextLoads() {
		// Create mock employees (1-5)
		Employee testEmployee1 = new Employee();
        testEmployee1.setFirstName("John");
        testEmployee1.setLastName("Lennon");
        testEmployee1.setDepartment("Engineering");
        testEmployee1.setPosition("Developer");

		Employee testEmployee2 = new Employee();
        testEmployee2.setFirstName("Paul");
        testEmployee2.setLastName("McCartney");
        testEmployee2.setDepartment("Engineering");
        testEmployee2.setPosition("Jr Developer");

		Employee testEmployee3 = new Employee();
        testEmployee3.setFirstName("Ringo");
        testEmployee3.setLastName("Starr");
        testEmployee3.setDepartment("Marketing");
        testEmployee3.setPosition("Manager");

		Employee testEmployee4 = new Employee();
        testEmployee4.setFirstName("Pete");
        testEmployee4.setLastName("Best");
        testEmployee4.setDepartment("Marketing");
        testEmployee4.setPosition("Worker");

		Employee testEmployee5 = new Employee();
        testEmployee5.setFirstName("George");
        testEmployee5.setLastName("Harrison");
        testEmployee5.setDepartment("Marketing");
        testEmployee5.setPosition("Worker");



		// Persist employees first
		Employee createdEmployee1 = restTemplate.postForEntity(employeeUrl, testEmployee1, Employee.class).getBody();
		Employee createdEmployee2 = restTemplate.postForEntity(employeeUrl, testEmployee2, Employee.class).getBody();
		Employee createdEmployee3 = restTemplate.postForEntity(employeeUrl, testEmployee3, Employee.class).getBody();
		Employee createdEmployee4 = restTemplate.postForEntity(employeeUrl, testEmployee4, Employee.class).getBody();
		Employee createdEmployee5 = restTemplate.postForEntity(employeeUrl, testEmployee5, Employee.class).getBody();

		// Set direct reports using persisted employees (with IDs)
		List<Employee> tempList = new ArrayList<>();
		tempList.add(createdEmployee5);
		tempList.add(createdEmployee4);
		createdEmployee3.setDirectReports(tempList);

		tempList = new ArrayList<>();
		tempList.add(createdEmployee2);
		tempList.add(createdEmployee3);
		createdEmployee1.setDirectReports(tempList);

		//  Update employees in the database
		restTemplate.put(employeeUrl, createdEmployee3);
		restTemplate.put(employeeUrl, createdEmployee1);

		
		// Check that employees were created
		assertEquals(testEmployee1.getFirstName(), createdEmployee1.getFirstName());
		assertEquals(testEmployee2.getFirstName(), createdEmployee2.getFirstName());
		assertEquals(testEmployee3.getFirstName(), createdEmployee3.getFirstName());
		assertEquals(testEmployee4.getFirstName(), createdEmployee4.getFirstName());
		assertEquals(testEmployee5.getFirstName(), createdEmployee5.getFirstName());
		

		// Call the API endpoint for ReportingStructure
		String reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
		ReportingStructure reportingStructure = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();
		reportingStructure.setEmployee(createdEmployee1);
		reportingStructure.setNumReps();
   		// Verify the number of reports
    	assertEquals(4, reportingStructure.getNumReps());
	}
}
