package io.makamu.crudrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.makamu.crudrestapi.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer>{

	Client findByFirstName(String firstName);
	Client findByMobileNumber(String mobileNumber);
	Client findByIdNumber(String idNumber);
	Client findByClientId(int id);

}
