package com.stackroute.userlogin.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userlogin.domain.User;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryTest {

	@Autowired
	private transient UserRepository repo;
	public void setRepo(final UserRepository repo) {
		this.repo = repo;
	}

	@Test
	public void userEmail() throws Exception {
		repo.save(new User((long) 1,"tejakathari007","teja007",null));
		final User user = repo.findByUserEmail("tejakathari007");
		assertThat(user.getUserPassword()).isEqualTo("teja007");
	}
	



}
