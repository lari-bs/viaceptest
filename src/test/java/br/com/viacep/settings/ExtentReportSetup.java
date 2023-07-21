package br.com.viacep.settings;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterEach;

public class ExtentReportSetup {
    protected static ExtentReports extent;
    protected static ExtentTest extentTest;

    public static void setupExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("report.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    @AfterEach
    public void tearDown() {
        extent.flush();
    }
}
