package org.launchcode.models;

/**
 * Created by LaunchCode
 */
public enum JobFieldType {                                  // Enum values in all caps, display name in ()
    EMPLOYER ("Employer"),                                  // Using enum values in place of string eliminates the possibility of runtime errors (compiler will force you to use a valid enum type so that there are no chances of misspelling
    LOCATION ("Location"),
    CORE_COMPETENCY ("Skill"),
    POSITION_TYPE ("Position Type"),
    ALL ("All");

    private final String name;

JobFieldType(String name) {                                 //default constructor is automatically invoked; you cannnot invoke an enum constructor yourself
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
