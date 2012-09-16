/*
 * Copyright (C) 2012 Jakub Narloch (jmnarloch@gmail.com), All Rights Reserved.
 */

package org.soa.security.model.dto;

import java.util.List;

/**
 * <p>Represents a search result.</p>
 *
 * <p><strong>Thread safety:</strong> This class is mutable and not thread safe.</p>
 *
 * @author Jakub Narloch (jmnarloch@gmail.com)
 * @version 1.0
 */
public class SearchResult<T> {

    /**
     * <p>Represents the search result list.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private List<T> result;

    /**
     * <p>Represents the total number of records that meats the concrete search result.</p>
     *
     * <p>0 by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private int totalRecords;

    /**
     * <p>Represents the total number of pages.</p>
     *
     * <p>Null by default.</p>
     *
     * <p>Can by any value.</p>
     *
     * <p>Has getter and setter.</p>
     */
    private int totalPages;

    /**
     * <p>Creates new instance of {@link SearchResult} class.</p>
     */
    public SearchResult() {
        // empty constructor
    }

    /**
     * <p>Retrieves the search result.</p>
     *
     * @return the search result
     */
    public List<T> getResult() {
        return result;
    }

    /**
     * <p>Sets the search result.</p>
     *
     * @param result the search result
     */
    public void setResult(List<T> result) {
        this.result = result;
    }

    /**
     * <p>Retrieves the total result count.</p>
     *
     * @return the total result count
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     * <p>Sets the total result count.</p>
     *
     * @param totalRecords the total result count
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    /**
     * <p>Retrieves the number of pages.</p>
     *
     * @return the number of pages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * <p>Sets the number of pages</p>
     *
     * @param totalPages the number of pages
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
