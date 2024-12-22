package automationproject.resources;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
	public static ExtentReports getReportObject() {
		  // Define the report path
        String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + "index.html";
        
        // Ensure the reports directory exists
        File reportDirectory = new File(System.getProperty("user.dir") + File.separator + "reports");
        if (!reportDirectory.exists()) {
            reportDirectory.mkdirs();
        }

        // Initialize ExtentSparkReporter
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        // Initialize ExtentReports and attach reporter
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Rashi");

        return extent;
	}

}
