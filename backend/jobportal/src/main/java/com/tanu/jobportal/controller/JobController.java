package com.tanu.jobportal.controller;

import com.tanu.jobportal.entity.Job;
import com.tanu.jobportal.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    @Autowired
    private JobService jobService;

    // POST JOB (Protected API)
    @PostMapping("/post")
    public Job postJob(@RequestBody Job job, Authentication authentication) {

        // get logged-in user email from JWT
        String email = authentication.getName();

        job.setPostedBy(email);

        return jobService.postJob(job);
    }

    // VIEW ALL JOBS (Public API)
    @GetMapping("/all")
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }
}