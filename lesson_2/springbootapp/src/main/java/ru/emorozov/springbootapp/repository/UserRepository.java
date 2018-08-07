package ru.emorozov.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.emorozov.springbootapp.entity.User;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, String> {

	@Override
	@Query("SELECT u FROM User u")
	List<User> findAll();
}
