package com.projectmaximize.interfaces;

public interface ProjectReportGenerator {
    String generateTimeReport(String projectId);
    String generateResourceAllocationReport(String projectId);
}
