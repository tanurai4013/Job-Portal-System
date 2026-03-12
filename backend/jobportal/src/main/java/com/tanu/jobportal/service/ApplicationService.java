package com.tanu.jobportal.service;

import com.tanu.jobportal.entity.JobApplication;
import com.tanu.jobportal.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    public JobApplication apply(JobApplication application) {
        return applicationRepository.save(application);
    }

    public List<JobApplication> getApplicationsForJob(Long jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    public List<JobApplication> myApplications(String email) {
        return applicationRepository.findByApplicantEmail(email);
    }
}
