package com.projectmaximize.interfaces;

public interface Employee {
    void logHours(String activityId, int hours);
    int getTotalHoursLogged();
    String getName();
    void printAllActivities();
    String getId();
    void assignActivity(Activity activity);
}