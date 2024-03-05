package com.sanju.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sanju.model.Address;

public interface AddressRepo extends JpaRepository<Address, Integer> {

}
