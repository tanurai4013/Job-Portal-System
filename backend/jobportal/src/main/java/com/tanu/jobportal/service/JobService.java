package com.tanu.jobportal.service;

import com.tanu.jobportal.entity.Job;
import com.tanu.jobportal.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    // post job
    public Job postJob(Job job) {
        return jobRepository.save(job);
    }

    // view all jobs
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}
