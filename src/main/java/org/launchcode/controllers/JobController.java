package org.launchcode.controllers;

import org.launchcode.models.Employer;
import org.launchcode.models.Job;
import org.launchcode.models.JobField;
import org.launchcode.models.JobFieldType;
import org.launchcode.models.data.JobFieldData;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

import static org.launchcode.models.JobField.*;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")                                                  //handles all requests to and from route '/job'
public class JobController {                                                    //refactored from TechJobs MVC - now uses model classes

    private JobData jobData = JobData.getInstance();                            //creates a new instance of the JobData class (loads all job data from CSV into variable jobData)

    @RequestMapping(value = "", method = RequestMethod.GET)                     //the detail display for a given Job at URLs like /job?id=17; handles all GET requests at '/job'
    public String index(Model model, int id) {                                  //creates a Model object, which serves as a 'holder' for attributes to be passed into view; takes in id as argument

        // TODO #1 - get the Job with the given ID and pass it into the view

        Job singleJob = jobData.findById(id);                                   //finds the job object with the given id
        model.addAttribute("singleJob", singleJob);                                          //passes job object into view

        return "job-detail";                                                    //renders job-detail.html
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)                  //handles all GET requests at route '/job/add'
    public String add(Model model) {                                            //creates a Model object, which serves as a 'holder' for attributes to be passed into view;
        model.addAttribute(new JobForm());                                      //pass a JobForm object into the view to display the form; same as model.addAttribute("jobform", new JobForm())
        return "new-job";                                                       //renders new-job.html
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)                 //handles all POST requests at route '/job/add'
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {     //creates a Model object, which serves as a 'holder' for attributes to be passed into view; @Valid = object's fields need to be validated according to annotations provided in JobForm class; puts any errors messages that are found in this Error object;

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.

        if (errors.hasErrors()) {
            model.addAttribute("jobForm", jobForm);
            return "new-job";
        }

        Job newJob = new Job(jobForm.getName(),
                             jobData.getEmployers().findById(jobForm.getEmployerId()),                                  //put in parameters for new job object to invoke constructor
                             jobData.getLocations().findById(jobForm.getLocationId()),                                  //jobData.getLocations() returns a JobFieldData object (of type Employer, Location, etc.) then you call on method findById (within class JobFieldData)
                             jobData.getPositionTypes().findById(jobForm.getPositionTypeId()),                          //need to get id for all parameters except name (fetch from jobForm object using getters (jobForm.getEmployerId()), etc.
                             jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId()));

        jobData.add(newJob);                                                                                            //add newJob to jobData

        model.addAttribute("singleJob", newJob);                                                           //pass newJob to view to display its details as a single job

        return "redirect:/job?id=" + newJob.getId();                                                                    //redirect to url with newJob object's id


    }
}
