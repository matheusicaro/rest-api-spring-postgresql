package com.matheusicaro.course.fullstack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matheusicaro.course.fullstack.domain.AddressHouse;

@Repository
public interface AddressHouseRepository extends JpaRepository<AddressHouse, Integer> {

}
