package com.lakhan.restprojects.hackerrankclone.services;

import com.lakhan.restprojects.hackerrankclone.daos.CodeSubmissionDetailsRepository;
import com.lakhan.restprojects.hackerrankclone.models.CodeSubmissionDetails;
import com.lakhan.restprojects.hackerrankclone.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeSubmissionDetailsService {
    @Autowired
    private CodeSubmissionDetailsRepository submissionDao;

    public void add(CodeSubmissionDetails details) {
        submissionDao.saveAndFlush(details);
    }
    public List<String> getProgrammingSkills(User user) {
        return submissionDao.findDistinctBySolvedBy(user);
    }
    public Integer getSolvedProblemsCount(User user) {
        return submissionDao.countBySolvedBy(user);
    }
}
