import static config.StartServerRunner.startServer;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.intuit.karate.cucumber.CucumberRunner;
import com.intuit.karate.cucumber.KarateStats;
import config.ServerStart;
import cucumber.api.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

@CucumberOptions(tags = {"~@ignore"}, format = {"pretty"})
public class GenerateReportParallel extends ServerStart {
    
    @BeforeClass
    public static void beforeClass() throws Exception {
        startServer();
    } 
    
    @Test
    public void generateReportParallel() {
        String karateOutputPath = "target/surefire-reports";
        KarateStats stats = CucumberRunner.parallel(getClass(), 5, karateOutputPath);
        generateReport(karateOutputPath);
        assertTrue("there are scenario failures", stats.getFailCount() == 0);        
    }
    
    public static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);
        List<String> jsonPaths = new ArrayList(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(new File("target"), "User Classification");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();        
    }
    
}