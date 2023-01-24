package com.example.dynamodb.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.example.dynamodb.entity.Employee;



@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeServiceTest {

	@MockBean
	private com.example.dynamodb.entity.Employee employee;

	@Mock
	private DynamoDBMapper dynamoDBMapper;
	
	@Mock
	private PaginatedScanList<Object> paginatedScanList;
	
	@Mock
    private PaginatedQueryList<Object> list;
	
	@InjectMocks
	EmployeeServiceImpl employeeService;
	
	private static final String USERID="userId";
	
	private static final String NAME="Employee";
	
	private static final String EMAIL="employee@mail.com";
	
	private static final String ROLE="engineer";
	
	private static final String DATE="1-1-1";
	
	@Test
	public void testgetEmployee() {
		when(dynamoDBMapper.load(any(), any(),any())).thenReturn(getEmployee());
		employeeService.getEmployee(USERID, ROLE);
		verify(dynamoDBMapper,never()).load(any(), any(),any());
	}
	
	@Test
	public void testSaveEmployee() {
		employeeService.saveEmployee(getEmployee());
		verify(dynamoDBMapper,times(1)).save(any());
	}
	
	@Test
	public void testgetAllEmployee() {
		when(dynamoDBMapper.scan(any(), any())).thenReturn(paginatedScanList);
		employeeService.getAllEmployee();
		verify(dynamoDBMapper,times(1)).scan(any(), any());
	}
	
	@Test
	public void testDeleteEmployee() {
		when(dynamoDBMapper.load(any(), any(),any())).thenReturn(getEmployee());
		employeeService.deleteEmployee(USERID, ROLE);
		verify(dynamoDBMapper,never()).load(any(),any(),any());
	}
	
	@Test
	public void testUpdateEmployee() {
		assertNotNull(employeeService.updateEmployee(USERID, getEmployee()));
	}
	
	@Test
	public void testGetEmployeeByDate() {
		when(dynamoDBMapper.query(any(), any())).thenReturn(list);
		employeeService.getEmployeeByDate(DATE);
		verify(dynamoDBMapper,times(1)).query(any(), any());
	}
	
	private Employee getEmployee() {
		Employee employee = new Employee();
		employee.setDate(DATE);
		employee.setEmail(EMAIL);
		employee.setName(NAME);
		employee.setRole(ROLE);
		employee.setUserId(USERID);
		return employee;
	}
}
