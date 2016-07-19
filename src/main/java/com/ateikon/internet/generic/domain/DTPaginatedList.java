package com.ateikon.internet.generic.domain;

import java.util.List;
import org.displaytag.pagination.PaginatedList;
import org.displaytag.properties.SortOrderEnum;

public class DTPaginatedList implements PaginatedList {

    private List list;
    private int pageNumber;
    private int objectsPerPage;
    private int fullListSize;
    private String sortCriterion;
    private SortOrderEnum sortDirection;
    private String searchID;
    private int range = 8;
    private int startRange;
    private int endRange;
    private int pages;
    private int prev;
    private int next;

    public DTPaginatedList(int size, List list, int pageNumber, int pageSize, String searchid, String criterion, SortOrderEnum direction) {
        fullListSize = size;
        this.list = list;
        objectsPerPage = pageSize;
        this.pageNumber = pageNumber;
        searchID = searchid;
        sortCriterion = criterion;
        sortDirection = direction;
        pages = (int) Math.ceil(fullListSize / (double) objectsPerPage);

        int offset = 0;
        if (pageNumber > range) {
            offset = pageNumber - range;
        }
        startRange = offset + 1;
        endRange = ((offset + range + 1) <= pages) ? offset + range + 1 : pages;

        prev = (pageNumber > 1) ? pageNumber - 1 : 1;
        next = (pageNumber < pages) ? pageNumber + 1 : pages;
    }

    public List getList() {

        return list;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getObjectsPerPage() {
        return objectsPerPage;
    }

    public int getFullListSize() {
        return fullListSize;
    }

    public String getSortCriterion() {
        return sortCriterion;
    }

    public SortOrderEnum getSortDirection() {

        return sortDirection;
    }

    public String getSearchId() {
        return searchID;
    }

    public int getEndRange() {
        return endRange;
    }

    public void setEndRange(int endRange) {
        this.endRange = endRange;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getStartRange() {
        return startRange;
    }

    public void setStartRange(int startRange) {
        this.startRange = startRange;
    }

    public int getNext() {
        return next;
    }

    public void setNext(int next) {
        this.next = next;
    }

    public int getPrev() {
        return prev;
    }

    public void setPrev(int prev) {
        this.prev = prev;
    }
}
