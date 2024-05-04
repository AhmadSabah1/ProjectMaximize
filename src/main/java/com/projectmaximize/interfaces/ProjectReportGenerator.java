package com.projectmaximize.interfaces;

import java.util.UUID;

public interface ProjectReportGenerator {
    String generateTimeReport(UUID projectId);
    String generateResourceAllocationReport(UUID projectId);
}
