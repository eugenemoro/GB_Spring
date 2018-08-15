package ru.emorozov.springbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.emorozov.springbootapp.entity.Comment;

@Repository
@Transactional
public interface CommentRepository extends JpaRepository<Comment, String> {
}
