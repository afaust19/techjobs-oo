package org.launchcode.models;

/**
 * Created by LaunchCode
 */
public class Job {                                  //allows creation of Job objects

    // Instance Variables (Fields)

    private int id;                                 //unique for each object; needs getter (listed below); does not include setter because user should not be able to do this (id for each object is set in default constructor listed below)
    private static int nextId = 1;                  //static = this field will belong to all instances (objects) of the Cheese class; will be shared amongst all Cheese objects; NOT unique for each object

    private String name;                            //unique for each object; needs getter and setter (listed below); name is the only field that is a String - all other job properties are represented by their own classes
    private Employer employer;                      //unique for each object; needs getter and setter (listed below)
    private Location location;                      //unique for each object; needs getter and setter (listed below)
    private PositionType positionType;              //unique for each object; needs getter and setter (listed below)
    private CoreCompetency coreCompetency;          //unique for each object; needs getter and setter (listed below)

    // Default Constructor                          //we need a default constructor so that we can do model binding

    public Job() {
        id = nextId;                                //initializes id field (set value to nextId - the first one created will have an id of 1)
        nextId++;                                   //increment nextId field
    }

    // Constructor

    public Job(String aName, Employer aEmployer, Location aLocation,        //takes in all necessary parameters needed to create a new Job object
               PositionType aPositionType, CoreCompetency aSkill) {

        this();                                     //calls on default constructor (to initialize id field and increment nextId)

        name = aName;                               //initializes all fields variables for this object (set field variables as values in the parameters)
        employer = aEmployer;                       //we do not need to use this.variable to refer to instance variables at the top because input parameters use "a + variable" naming convention
        location = aLocation;
        positionType = aPositionType;
        coreCompetency = aSkill;

    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(Employer employer) {
        this.employer = employer;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }

    public CoreCompetency getCoreCompetency() {
        return coreCompetency;
    }

    public void setCoreCompetency(CoreCompetency coreCompetency) {
        this.coreCompetency = coreCompetency;
    }

    public int getId() {
        return id;
    }                                   //returns the id of the given Job object

    @Override                                                           //what is this overriding since it doesn't extend a class?
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Job job = (Job) o;

        return id == job.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
