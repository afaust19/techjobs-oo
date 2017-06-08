package org.launchcode.controllers;

import org.launchcode.models.Job;
import org.launchcode.models.JobFieldType;
import org.launchcode.models.forms.SearchForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")                                                                               //handles all requests to and from route '/search'
public class SearchController {                                                                         //refactored from TechJobs MVC - now uses model classes

    private JobData jobData = JobData.getInstance();                                                    //creates a new instance of the JobData class (loads all job data from CSV into variable jobData)

    @RequestMapping(value = "")                                                                         //route is '/search'
    public String search(Model model) {
        model.addAttribute(new SearchForm());                                                           //passes a SearchForm object into the view to display the form; same as model.addAttribute("searchform", new SearchForm())
        return "search";                                                                                //renders 'search.html;
    }

    @RequestMapping(value = "results")                                                                  //route is '/search/results'
    public String search(Model model,
                         @ModelAttribute SearchForm searchForm) {                                       //use model binding with a SearchForm object to process the form

        ArrayList<Job> jobs;                                                                            //declare new ArrayList jobs (not initialized yet)

        if (searchForm.getSearchField().equals(JobFieldType.ALL)) {                                     //if the column selected to search is "ALL",
            jobs = jobData.findByValue(searchForm.getKeyword());                                        //then search all the columns for the value
        } else {
            jobs = jobData.findByColumnAndValue(searchForm.getSearchField(), searchForm.getKeyword());  //otherwise, search for the value within a specific column
        }

        model.addAttribute("jobs", jobs);                                                  //passes ArrayList jobs into view

        return "search";                                                                                //render 'search.html'
    }

}
