package com.lakhan.restprojects.hackerrankclone.daos;

import com.lakhan.restprojects.hackerrankclone.models.CodeSubmissionDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeSubmissionDetailsRepository extends JpaRepository<CodeSubmissionDetails, Long> {
}
