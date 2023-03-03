package io.makamu.crudrestapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.makamu.crudrestapi.exceptionhandler.ClientExceptionHandler;
import io.makamu.crudrestapi.dto.ClientDto;
import io.makamu.crudrestapi.entity.Client;
import io.makamu.crudrestapi.service.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientController {

	
	
	@Autowired
	private ClientService clientService;
	
	
	
	
	@PostMapping("/register")
	public ResponseEntity<Client> saveClient(@RequestBody @Valid ClientDto clientDto) throws ClientExceptionHandler {
		
		return new ResponseEntity<>(clientService.saveClient(clientDto),HttpStatus.CREATED);
		
	}
	
	@GetMapping("/findbyname/{git}")
	public ResponseEntity<Client> getClientByFirstName(@PathVariable("firstName") String firstName) throws ClientExceptionHandler{
		
		return ResponseEntity.ok(clientService.searchByFirstName(firstName));
		
		
	}
	
	@GetMapping("/findbyid/{idNumber}")
	public ResponseEntity<Client> getClientByIdNumber(@PathVariable("idNumber") String idNumber) throws ClientExceptionHandler{
		
		return ResponseEntity.ok(clientService.searchByIdNumber(idNumber));
		
		
	}
	
	@GetMapping("/findbynumber/{mobileNumber}")
	public ResponseEntity<Client> getClientByMobileNumber(@PathVariable("mobileNumber") String mobileNumber) throws ClientExceptionHandler{
		
		return ResponseEntity.ok(clientService.searchByMobileNumber(mobileNumber));
		
	}

	@PostMapping("/update/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable("id") int id,@RequestBody ClientDto clientDto) throws ClientExceptionHandler {

		return new ResponseEntity<>(clientService.updateClient(id,clientDto),HttpStatus.CREATED);

	}
	
	
}
