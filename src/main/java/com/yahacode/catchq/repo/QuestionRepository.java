package com.yahacode.catchq.repo;

import com.yahacode.catchq.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Question findById(int id);

    List<Question> findByContentLike(String content);
}
