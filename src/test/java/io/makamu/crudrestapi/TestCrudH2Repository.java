package io.makamu.crudrestapi;

import io.makamu.crudrestapi.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestCrudH2Repository extends JpaRepository<Client,Integer> {

}
