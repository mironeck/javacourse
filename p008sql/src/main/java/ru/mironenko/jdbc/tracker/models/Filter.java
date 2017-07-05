package ru.mironenko.jdbc.tracker.models;

/**
 * Class Filter
 * @author mironenko
 * @since 04.07.2017.
 * @version 1
 */
public class Filter {
    /**
     * @param filter
     */
    private String filter;

    /**
     * Constructor of Filter
     * @param filter - String value of filter
     */
    public Filter(String filter){
        this.filter = filter;
    }

    /**
     * Getter of filter
     * @return string value of filter
     */
    public String getFilter() {
        return filter;
    }

}
