package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.daos.CodeSubmissionDetailsRepository;
import com.lakhan.restprojects.hackerrankclone.models.CodeSubmissionDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeSubmissionDetailsService {
    @Autowired
    private CodeSubmissionDetailsRepository submissionDao;

    public void add(CodeSubmissionDetails details) {
        submissionDao.save(details);
    }
}
