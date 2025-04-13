package com.framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {
    private static ExtentReports extent;
    private static final String REPORT_PATH = Paths.get(System.getProperty("user.dir"), "reports").toString();

    public static ExtentReports getInstance() {
        if (extent == null) {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
            String reportName = "AutomationReport_" + timestamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(Paths.get(REPORT_PATH, reportName).toString());
            spark.config().setDocumentTitle("Unified Test Report");
            spark.config().setReportName("API + UI Automation Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}
