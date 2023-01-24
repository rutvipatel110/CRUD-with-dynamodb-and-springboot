package com.example.dynamodb.config;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
//import org.powermock.api.mockito.PowerMockito;
//import org.powermock.modules.junit4.PowerMockRunner;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

//@RunWith(PowerMockRunner.class)
public class DynamoDBConfigTest {

	@Mock
	AmazonDynamoDBClientBuilder amazonDynamoDbClientBuilder;
	
	@Mock
	AmazonDynamoDB amazonDynamoDB;
	
	@InjectMocks
	private DynamodbConfig dynamoDbConfig;
	
//	@Test
//	public void testDynamoDBMapper() {
//		PowerMockito.mockStatic(AmazonDynamoDBClientBuilder.class);
//        PowerMockito.when(AmazonDynamoDBClientBuilder.standard()).thenReturn(amazonDynamoDbClientBuilder);
//        PowerMockito.when(amazonDynamoDbClientBuilder.withEndpointConfiguration(any())).thenReturn(amazonDynamoDbClientBuilder);
//        PowerMockito.when(amazonDynamoDbClientBuilder.withCredentials(any())).thenReturn(amazonDynamoDbClientBuilder);
//        PowerMockito.when(amazonDynamoDbClientBuilder.build()).thenReturn(amazonDynamoDB);
//        dynamoDbConfig.dynamoDBMapper();
//	}
}
