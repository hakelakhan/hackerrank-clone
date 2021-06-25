package com.lakhan.restprojects.hackerrankclone.daos;

import com.lakhan.restprojects.hackerrankclone.models.CodeSubmissionDetails;
import com.lakhan.restprojects.hackerrankclone.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface CodeSubmissionDetailsRepository extends JpaRepository<CodeSubmissionDetails, Long> {

//    @Query("SELECT DISTINCT CODESUBMITTEDLANGUAGE FROM CodeSubmissionDetails WHERE solvedBy.email = ?")
    default List<String> findDistinctBySolvedBy(User user) {
        return Arrays.asList("C", "C++", "Java", "Python");
    }

    Integer countBySolvedBy(User user);
}
