package com.example.dynamodb.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper.FailedBatch;
import com.amazonaws.services.dynamodbv2.document.BatchGetItemOutcome;
import com.amazonaws.services.dynamodbv2.document.BatchWriteItemOutcome;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.TableKeysAndAttributes;
import com.amazonaws.services.dynamodbv2.document.TableWriteItems;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.example.dynamodb.entity.Employee;

/**
 * The Class EmployeeServiceImpl.
 */
@Repository
public class EmployeeServiceImpl {

	@Autowired
	private DynamoDBMapper dynamoDBMapper;
	

	public Employee saveEmployee(Employee employee) {
		dynamoDBMapper.save(employee);

		return employee;
	}

	public List<Employee> getAllEmployee() {
		return dynamoDBMapper.scan(Employee.class, new DynamoDBScanExpression());

	}

	public Employee getEmployee(String employeeId, String role) {
		return dynamoDBMapper.load(Employee.class, employeeId, role);
	}

	public String deleteEmployee(String employeeId, String role) {
		Employee emp = dynamoDBMapper.load(Employee.class, employeeId, role);

		dynamoDBMapper.delete(emp);

		return "Employee with " + employeeId + " deleted";
	}

	public Employee updateEmployee(String employeeId, Employee employee) {

		dynamoDBMapper.save(employee, new DynamoDBSaveExpression().withExpectedEntry("employeeId",
				new ExpectedAttributeValue(new AttributeValue().withS(employeeId))));

		return employee;
	}

	public List<Employee> getEmployeeByDate(String date) {

		Employee employee = new Employee();
		employee.setDate(date);

		DynamoDBQueryExpression<Employee> queryExp = new DynamoDBQueryExpression<Employee>();
		queryExp.withHashKeyValues(employee).withIndexName("date-index").withConsistentRead(false);
		
		System.out.println(queryExp);

		return dynamoDBMapper.query(Employee.class, queryExp);
	}

}
