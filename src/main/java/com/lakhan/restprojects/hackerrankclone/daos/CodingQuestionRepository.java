package com.lakhan.restprojects.hackerrankclone.daos;

import com.lakhan.restprojects.hackerrankclone.models.CodingQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodingQuestionRepository extends JpaRepository<CodingQuestion, Long> {
}
