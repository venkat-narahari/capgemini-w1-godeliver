package com.stackroute.recommendation.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.recommendation.domain.Person;
import com.stackroute.recommendation.repository.PersonRepository;

@Service("personimpl")
public class PersonServiceImpl implements PersonService{
PersonRepository personRepository;
	
	@Autowired
	public  PersonServiceImpl(PersonRepository personRepository) {
			
		this.personRepository=personRepository;
		}

	@Override
	public List<Person> getAllPersonnodes() {
		List<Person> getAllpersons=(List<Person>) personRepository.findAll();
		return getAllpersons;
	}

}
