package org.launchcode.controllers;

import org.launchcode.models.Job;
import org.launchcode.models.JobField;
import org.launchcode.models.JobFieldType;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "list")                                                         //handles all requests to and from route '/list'
public class ListController {                                                           //refactored from TechJobs MVC - now uses model classes

    private JobData jobData = JobData.getInstance();                                    //creates a new instance of the JobData class (loads all job data from CSV into variable jobData)

    @RequestMapping(value = "")                                                         //route is '/list'
    public String list(Model model) {
        JobFieldType[] fields = JobFieldType.values();                                  //puts all JobFieldType values (enums e.g "EMPLOYER") into an Array named 'fields'
        model.addAttribute("fields", fields);                              //passes fields Array into view
        return "list";                                                                  //renders 'list.html'
    }

    @RequestMapping(value = "values")                                                   //route is '/list/values'
    public String listColumnValues(Model model, @RequestParam JobFieldType column) {    //column gets passed into controller from URL??

        if (column.equals(JobFieldType.ALL)) {                                          //if the column type is "ALL"
            return "redirect:/list/all";                                                //redirect to '/list/all' (method to render this page is listed below)
        }

        ArrayList<? extends JobField> items;                                            //ArrayList (unknown type that extends JobField), e.g. Employer, Location, etc.

        switch(column) {
            case EMPLOYER:
                items = jobData.getEmployers().findAll();                               //if the selected column is EMPLOYER, then find all employers and put in the ArrayList items
                break;
            case LOCATION:
                items = jobData.getLocations().findAll();
                break;
            case CORE_COMPETENCY:
                items = jobData.getCoreCompetencies().findAll();
                break;
            case POSITION_TYPE:
            default:
                items = jobData.getPositionTypes().findAll();
        }

        model.addAttribute("title", "All " + column.getName() + " Values");                   //pass in title
        model.addAttribute("column", column);                                                              //pass in column
        model.addAttribute("items", items);                                                                //pass in ArrayList items

        return "list-column";                                                                                           //renders 'list-column.html'
    }

    @RequestMapping(value = "jobs")                                                                                     //route is '/list/jobs'
    public String listJobsByColumnAndValue(Model model,
            @RequestParam JobFieldType column, @RequestParam String name) {                                             //column and name get passed into controller from URL?

        ArrayList<Job> jobs = jobData.findByColumnAndValue(column, name);                                               //creates a new ArrayList (jobs) of type Job; populates ArrayList by calling on JobData method

        model.addAttribute("title", "Jobs with " + column.getName() + ": " + name);           //pass in title
        model.addAttribute("jobs", jobs);                                                                  //pass in ArrayList jobs

        return "list-jobs";                                                                                             //renders 'list-jobs.html'
    }

    @RequestMapping(value = "all")                                                                                      //route is 'list/all'
    public String listAllJobs(Model model) {

        ArrayList<Job> jobs = jobData.findAll();                                                                        //creates a new ArrayList (jobs) of type Job; populates ArrayList by calling on JobData method

        model.addAttribute("title", "All Jobs");                                              //pass in title
        model.addAttribute("jobs", jobs);                                                                  //pass in ArrayList

        return "list-jobs";                                                                                             //renders 'list-jobs.html'
    }
}
