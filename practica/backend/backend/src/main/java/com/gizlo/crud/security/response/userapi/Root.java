package com.gizlo.crud.security.response.userapi;

import java.util.List;

public class Root {
    private List<User> users;
    private Long total;
    private Long skip;
    private Long limit;

    public Root(List<User> users, Long total, Long skip, Long limit) {
        this.users = users;
        this.total = total;
        this.skip = skip;
        this.limit = limit;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public Long getTotal() {
        return this.total;
    }

    public Long getSkip() {
        return this.skip;
    }

    public Long getLimit() {
        return this.limit;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public void setSkip(Long skip) {
        this.skip = skip;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }
}
