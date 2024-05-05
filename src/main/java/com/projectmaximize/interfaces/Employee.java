package com.projectmaximize.interfaces;

import java.util.List;

public interface Employee {
    void logHours(String activityId, int hours);
    int getTotalHoursLogged();
    String getName();
    void printAllActivities();
    String getId();
    void assignActivity(Activity activity);
    List<Activity> getActivities();
}