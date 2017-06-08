package org.launchcode.models.forms;

import org.launchcode.models.JobFieldType;

/**
 * Created by LaunchCode
 */
public class SearchForm {

    // Fields - represent data associated with the search form; each is necessary to display and process the form

    private JobFieldType[] fields = JobFieldType.values();              // The search options

    private JobFieldType searchField = JobFieldType.ALL;                // The selected search options

    private String keyword;                                             // The search string

    public JobFieldType getSearchField() {
        return searchField;
    }

    public void setSearchField(JobFieldType searchField) {
        this.searchField = searchField;
    }

    public JobFieldType[] getFields() {
        return fields;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
