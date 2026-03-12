package com.tanu.jobportal.controller;

import com.tanu.jobportal.entity.JobApplication;
import com.tanu.jobportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    // Apply job (Protected)
    @PostMapping("/apply/{jobId}")
    public JobApplication apply(@PathVariable Long jobId,
                                @RequestBody JobApplication application,
                                Authentication authentication) {

        String email = authentication.getName();

        application.setJobId(jobId);
        application.setApplicantEmail(email);

        return applicationService.apply(application);
    }

    // See who applied to a job
    @GetMapping("/job/{jobId}")
    public List<JobApplication> getApplications(@PathVariable Long jobId) {
        return applicationService.getApplicationsForJob(jobId);
    }

    // My applications
    @GetMapping("/my")
    public List<JobApplication> myApplications(Authentication authentication) {
        return applicationService.myApplications(authentication.getName());
    }
}
