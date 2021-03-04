package com.fashion.rest.model;

public class ReportType {
    String report,reportS;
    int reportImage;

    public ReportType(String report, String reportS, int reportImage) {
        this.report = report;
        this.reportS = reportS;
        this.reportImage = reportImage;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getReportS() {
        return reportS;
    }

    public void setReportS(String reportS) {
        this.reportS = reportS;
    }

    public int getReportImage() {
        return reportImage;
    }

    public void setReportImage(int reportImage) {
        this.reportImage = reportImage;
    }
}
