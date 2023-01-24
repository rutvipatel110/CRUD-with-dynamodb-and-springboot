package com.example.dynamodb.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.dynamodb.entity.Employee;
import com.example.dynamodb.service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {

	@InjectMocks
	EmployeeController employeeController;
	
	@MockBean
	private Employee employee;
	
	@Mock
	private EmployeeServiceImpl employeeService;
	
	private static final String USERID="userId";
	
	private static final String NAME="Employee";
	
	private static final String EMAIL="employee@mail.com";
	
	private static final String ROLE="engineer";
	
	private static final String DATE="1-1-1";
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		  MockitoAnnotations.initMocks(this);
		  this.mockMvc=MockMvcBuilders.standaloneSetup(employeeController).build();
	}
	@Test
	public void testSaveEmployee() throws Exception {

		when(employeeService.saveEmployee(any())).thenReturn(getEmployee());
        ResultActions result =
                mockMvc.perform(post("/saveEmp").content(new ObjectMapper().writeValueAsString(getEmployee())).contentType(MediaType.APPLICATION_JSON));
        
        result.andExpect(status().isOk());
	}
	
	
	@Test
	public void testGetAllEmployee() throws Exception {

		when(employeeService.getAllEmployee()).thenReturn(Arrays.asList(getEmployee()));
        ResultActions result =
                mockMvc.perform(get("/getEmp").contentType(MediaType.APPLICATION_JSON));
        
        result.andExpect(status().isOk());
	}
	
	@Test
	public void testGetEmployeeByDate() throws Exception {

		when(employeeService.getEmployeeByDate(anyString())).thenReturn(Arrays.asList(getEmployee()));
        ResultActions result =
                mockMvc.perform(get("/getDateEmp/%s").contentType(MediaType.APPLICATION_JSON));
        
        result.andExpect(status().isOk());
	}
	
	@Test
	public void testGetEmployee() throws Exception {

		when(employeeService.getEmployee(anyString(), anyString())).thenReturn(getEmployee());
        ResultActions result =
                mockMvc.perform(get("/getEmp/%s/%s").contentType(MediaType.APPLICATION_JSON));
        
        result.andExpect(status().isOk());
	}
	
	@Test
	public void testUpdateEmployee() throws Exception {

		when(employeeService.updateEmployee(anyString(),any())).thenReturn(getEmployee());
        ResultActions result =
                mockMvc.perform(put("/updateEmp/%s").content(new ObjectMapper().writeValueAsString(getEmployee())).contentType(MediaType.APPLICATION_JSON));
        
        result.andExpect(status().isOk());
	}
	
	@Test
	public void testDeleteEmployee() throws Exception {

		when(employeeService.deleteEmployee(anyString(),any())).thenReturn("");
        ResultActions result =
                mockMvc.perform(delete("/deleteEmp/%s/%s").contentType(MediaType.APPLICATION_JSON));
        
        result.andExpect(status().isOk());
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
