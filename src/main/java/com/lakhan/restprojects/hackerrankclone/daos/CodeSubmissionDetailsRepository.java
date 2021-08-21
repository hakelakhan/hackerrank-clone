package com.lakhan.restprojects.hackerrankclone.daos;

import com.lakhan.restprojects.hackerrankclone.models.CodeSubmissionDetails;
import com.lakhan.restprojects.hackerrankclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Repository
public interface CodeSubmissionDetailsRepository extends JpaRepository<CodeSubmissionDetails, Long> {

//    @Query("SELECT DISTINCT CODESUBMITTEDLANGUAGE FROM CodeSubmissionDetails WHERE solvedBy.email = ?")
    default List<String> findDistinctBySolvedBy(User user) {
        Random random = new Random();
        List<String> languages = Arrays.asList("C", "C++", "Java", "Python");
        return Arrays.asList(languages.get(random.nextInt(languages.size())));
    }

    Integer countBySolvedBy(User user);
}
