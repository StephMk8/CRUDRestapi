package io.makamu.crudrestapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.makamu.crudrestapi.exceptionhandler.ClientExceptionHandler;
import io.makamu.crudrestapi.dto.ClientDto;
import io.makamu.crudrestapi.entity.Client;
import io.makamu.crudrestapi.repository.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	public Client saveClient(ClientDto clientDto) throws ClientExceptionHandler {
		Client client= new Client(clientDto.getFirstName(),clientDto.getLastName(),clientDto.getMobileNumber(),clientDto.getIdNumber()
				      ,clientDto.getPhysicalAddress());
		if(clientRepository.findByIdNumber(clientDto.getIdNumber()) == null ){
			if (clientRepository.findByMobileNumber(clientDto.getMobileNumber()) == null){
				return clientRepository.save(client);
			}else{
				throw new ClientExceptionHandler("Client with mobile number "+clientDto.getMobileNumber()+" exist. Duplicates are not allowed");
			}

		}else{
			throw new ClientExceptionHandler("Client with id number "+clientDto.getIdNumber()+" exist. Duplicates are not allowed");
		}
			
		
	}
	
	
	public Client searchByFirstName(String firstName) throws ClientExceptionHandler {
		
		Client client = clientRepository.findByFirstName(firstName);
		if (client != null) {
			return client;
		}else {
			throw new ClientExceptionHandler("Client not found with id number:"+firstName);
		}
	}
	
	public Client searchByIdNumber(String idNumber) throws ClientExceptionHandler {
		
		Client client = clientRepository.findByIdNumber(idNumber);	
		if (client != null) {
			return client;
		}else {
			throw new ClientExceptionHandler("Client not found with id number:"+idNumber);
		}
		
	}
	
	public Client searchByMobileNumber(String mobileNumber) throws ClientExceptionHandler {
		
		Client client = clientRepository.findByMobileNumber(mobileNumber);
		if (client != null) {
			return client;
		}else {
			throw new ClientExceptionHandler("Client not found with mobile number:"+mobileNumber);
		}
		
	}

	public Client updateClient(int id,ClientDto clientDto) throws ClientExceptionHandler {

		Client client=clientRepository.findByClientId(id);

		if(client!= null){
			client.setFirstName(clientDto.getFirstName());
			client.setLastName(clientDto.getLastName());
			client.setIdNumber(clientDto.getIdNumber());
			client.setMobileNumber(clientDto.getMobileNumber());
			client.setPhysicalAddress(clientDto.getPhysicalAddress());

			return clientRepository.save(client);
		}else{
			throw new ClientExceptionHandler("Client you are trying to update with id "+id+" does not exist");
		}


	}


	

}
