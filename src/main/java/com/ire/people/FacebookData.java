package com.ire.people;

import facebook4j.Page;
import facebook4j.User;


public class FacebookData {
    Page page;


    @Override
    public String toString() {
        return "FacebookData [page=" + page + "]";
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
