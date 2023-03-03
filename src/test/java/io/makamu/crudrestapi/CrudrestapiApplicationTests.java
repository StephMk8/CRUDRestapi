package io.makamu.crudrestapi;

import io.makamu.crudrestapi.dto.ClientDto;
import io.makamu.crudrestapi.entity.Client;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CrudrestapiApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl="http://localhost";

	@Autowired
	private static RestTemplate restTemplate;

	@Autowired
	TestCrudH2Repository testCrudH2Repository;
	@BeforeAll
	public static void init(){
		restTemplate=new RestTemplate();
	}

	@BeforeEach
	public void setup(){
		baseUrl=baseUrl.concat(":").concat(port+"").concat("/clients");
	}

	@Test
	public void addClient(){
		ClientDto clientDto= new ClientDto("Akani","Stephen","0123456789","0002155500082","sunnyside");
		Client client=restTemplate.postForObject(baseUrl+"/register",clientDto,Client.class);
		assertEquals(clientDto.getFirstName(),client.getFirstName());
		assertEquals(1,testCrudH2Repository.findAll().size());


	}

	@Test
	@Sql(statements = "INSERT INTO CLIENTS(client_id,first_name,last_name,mobile_number,id_number,physical_address) VALUES(1,'Ak','MK8','0123456789','0102155500082','')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENTS WHERE first_name='Ak'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void getClientByName(){

		Client client=restTemplate.getForObject(baseUrl+"/findbyname/{firstName}",Client.class,"Ak");
		assertAll(
				()-> assertNotNull(client),
				()->assertEquals("Ak",client.getFirstName()),
				()->assertEquals(1,client.getClientId())
		);


	}


	@Test
	@Sql(statements = "INSERT INTO CLIENTS(client_id,first_name,last_name,mobile_number,id_number,physical_address) VALUES(1,'Steph','MK8','0123456789','0102155500082','')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENTS WHERE first_name='Ak'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void getClientByIdNumber(){

		Client client=restTemplate.getForObject(baseUrl+"/findbyid/{idNumber}",Client.class,"0102155500082");
		assertAll(
				()-> assertNotNull(client),
				()->assertEquals("0102155500082",client.getIdNumber()),
				()->assertEquals(1,client.getClientId()),
				()->assertEquals("Steph",client.getFirstName())
		);


	}

	@Test
	@Sql(statements = "INSERT INTO CLIENTS(client_id,first_name,last_name,mobile_number,id_number,physical_address) VALUES(1,'Steph','CCNC','0123456789','0102155500082','')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENTS WHERE first_name='Ak'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void getClientByMobile(){

		Client client=restTemplate.getForObject(baseUrl+"/findbynumber/{mobileNumber}",Client.class,"0123456789");
		assertAll(
				()-> assertNotNull(client),
				()->assertEquals("0123456789",client.getMobileNumber()),
				()->assertEquals(1,client.getClientId()),
				()->assertEquals("CCNC",client.getLastName())
		);


	}

	@Test
	@Sql(statements = "INSERT INTO CLIENTS(client_id,first_name,last_name,mobile_number,id_number,physical_address) VALUES(1,'Steph','CCNC','0123456789','0102155500082','')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM CLIENTS WHERE first_name='Ak'",executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void updateClient(){

		ClientDto clientDto= new ClientDto("Akani","Stephen","0123456789","0002155500082","sunnyside");
		Client client=restTemplate.postForObject(baseUrl+"/update/{id}",clientDto,Client.class,1);

		assertAll(
				()-> assertNotNull(client),
				()->assertEquals(client.getFirstName(),client.getFirstName()),
				()->assertEquals(1,client.getClientId()),
				()->assertEquals(clientDto.getLastName(),client.getLastName()),
				()->assertEquals(clientDto.getPhysicalAddress(),client.getPhysicalAddress())
		);


	}




	@Test
	void contextLoads() {
	}

}
