package ru.emorozov.springbootapp.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.emorozov.springbootapp.entity.User;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class UserRepositoryTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void findByIdTest() {
		User user = new User();
		final String id = user.getId();
		this.testEntityManager.persist(user);
		User userFound = this.userRepository.findById(id).get();
		assertNotNull(userFound);
	}

	@Test
	public void findByNameTest() {
		User user = new User();
		user.setFirstName("John");
		this.testEntityManager.persist(user);
		assertTrue(userRepository.findByFirstName("John").getFirstName().equalsIgnoreCase(user.getFirstName()));
	}
}
