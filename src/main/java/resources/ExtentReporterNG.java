package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG{

    public static ExtentReports getReporterObject(){

        String reportPath = System.getProperty("user.dir")+"\\reports\\ExtentReport.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(reportPath);
        extentSparkReporter.config().setReportName("Web Automation report");
        extentSparkReporter.config().setDocumentTitle("Practise Website Test Report");

        ExtentReports reports = new ExtentReports();
        reports.attachReporter(extentSparkReporter);
        reports.setSystemInfo("Tester", "Mohammed JafferAli");
        reports.setSystemInfo("Operating System", "Windows and Mac");
        reports.setSystemInfo("Version", "5.53.2");
        return reports;
    }
}
