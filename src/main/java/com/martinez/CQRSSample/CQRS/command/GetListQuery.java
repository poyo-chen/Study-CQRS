package com.martinez.CQRSSample.CQRS.command;

public record GetListQuery(String enrolledIn, int numberOfCourses) implements Query {

}
