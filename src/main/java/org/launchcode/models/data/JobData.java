package org.launchcode.models.data;

import javafx.geometry.Pos;
import org.launchcode.models.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
public class JobData {                                                                               //works with Job objects, and the objects that a Job has reference to (Employer, etc.)

    // Fields

    private ArrayList<Job> jobs = new ArrayList<>();
    private static JobData instance;                                                                 //field shared by all objects in JobData class, not yet initialized

    private JobFieldData<Employer> employers = new JobFieldData<>();                                 //variables (enums representing each job property) are initialized
    private JobFieldData<Location> locations = new JobFieldData<>();
    private JobFieldData<CoreCompetency> coreCompetencies = new JobFieldData<>();
    private JobFieldData<PositionType> positionTypes = new JobFieldData<>();

    // Constructor

    private JobData() {
        JobDataImporter.loadData(this);                                                     //invoked when a new JobData object is created; loads data from JobDataImporter
    }

    // Methods

    public static JobData getInstance() {                                                           //static = shared by all objects in the JobData class; initializes 'instance' field
        if (instance == null) {                                                                     //if instance is null (empty),
            instance = new JobData();                                                               //then instance = a new JobData object (constructor is invoked, which loads all data from the JobDataImporter)
        }
        return instance;
    }

    public Job findById(int id) {                                                                   //returns object of type Job, takes in id as parameter
        for (Job job : jobs) {                                                                      //iterates through ArrayList of Job objects (jobs) - initialized at the top of the class
            if (job.getId() == id)                                                                  //calls on Job method getId, if the id from the job object matches the id put in as parameter,
                return job;                                                                         //return that job object
        }

        return null;                                                                                //otherwise return null
    }

    public ArrayList<Job> findAll() {                                                               //gets the full list of jobs
        return jobs;
    }


    public ArrayList<Job> findByColumnAndValue(JobFieldType column, String value) {                 //returns ArrayList of all jobs matching the given String within the given column (property)

        ArrayList<Job> matchingJobs = new ArrayList<>();                                            //creates new ArrayList of Jobs

        for (Job job : jobs) {                                                                      //iterates through ArrayList of Job objects (jobs) - initialized at the top of the class
            if (getFieldByType(job, column).contains(value))                                        //calls on getFieldByType (method below) if the particular column contains the value,
                matchingJobs.add(job);                                                              //add the job object to matchingJobs
        }

        return matchingJobs;
    }


    public ArrayList<Job> findByValue(String value) {                                               //returns ArrayList of all jobs matching the given string in any column

        ArrayList<Job> matchingJobs = new ArrayList<>();

        for (Job job : jobs) {                                                                      //iterates through ArrayList of Job objects (jobs) - initialized at the top of the class

            if (job.getName().toLowerCase().contains(value)) {                                      //get the name of the job, convert to lowercase, and see if it contains the given value
                matchingJobs.add(job);                                                              //if so, add the job to matchingJobs and go to the end of the loop (skips the for loop)
                continue;
            }

            for (JobFieldType column : JobFieldType.values()) {                                     //if the program does not find a matching value in the ArrayList of jobs, it iterates through the enum values in the JobFieldType class (Employer, etc.)
                if (column != JobFieldType.ALL && getFieldByType(job, column).contains(value)) {    //if the ...? and the column contains the value,
                    matchingJobs.add(job);                                                          //then exit the loop
                    break;
                }
            }
        }

        return matchingJobs;
    }


    public void add(Job job) {                                                                      //adds the job to the ArayList of jobs
        jobs.add(job);
    }


    private static JobField getFieldByType(Job job, JobFieldType type) {                            // method shared by all instances of the JobData class; finds the column given the job object and the column type (JobFieldType)
        switch(type) {                                                                              //similar to else if statement; looks for matching value, e.g. EMPLOYER
            case EMPLOYER:
                return job.getEmployer();
            case LOCATION:
                return job.getLocation();
            case CORE_COMPETENCY:
                return job.getCoreCompetency();
            case POSITION_TYPE:
                return job.getPositionType();
        }

        throw new IllegalArgumentException("Cannot get field of type " + type);                     //if inputted value does not match an enum value in JobFieldType class, display error message
    }

    public JobFieldData<Employer> getEmployers() {                                                  //getter methods for fetching enum values?
        return employers;
    }

    public JobFieldData<Location> getLocations() {
        return locations;
    }

    public JobFieldData<CoreCompetency> getCoreCompetencies() {
        return coreCompetencies;
    }

    public JobFieldData<PositionType> getPositionTypes() {
        return positionTypes;
    }
}
