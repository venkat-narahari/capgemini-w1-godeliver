package com.stackroute.logisticsservice.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.logisticsservice.domain.DateLogistics;

@Repository
public interface LogisticsRepository extends CrudRepository<DateLogistics, String>{ 

}
