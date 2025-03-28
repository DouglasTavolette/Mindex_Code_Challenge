package com.mindex.challenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeApplicationTests {

	@Test
	public void contextLoads() {
		// Create mock employees
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

		
	}

}
