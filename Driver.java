package amazonApp;
import java.lang.reflect.Method;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.WebDriver;

public class Driver {
	
		public static void main(String[] args) throws Exception {
			
			String[] browsers = {/*"firefox",*/ "chrome"/*, "ie"*/};
			
			List<Row> testCases = AmazonAutomationScript.loadTestCases("C:\\Users\\Bhuvana\\Desktop\\amazon\\Test Cases.xls");			
			for (String browser : browsers) {
				AmazonAutomationScript.loadObjectRepo("C:\\Users\\Bhuvana\\Desktop\\Amazonapp.xls");
				WebDriver driver = AmazonAutomationScript.getDriver(browser);
				for (Row row :testCases) {
					String testCaseName=row.getCell(1).getStringCellValue();
					Method tc=AmazonAutomationScript.class.getMethod(testCaseName, WebDriver.class);
					String result = (String)tc.invoke(null, driver);
					AmazonAutomationScript.updateResultsExcel(row, result, driver);
				}			
			}
			AmazonAutomationScript.writeResultsExcel("C:\\Users\\Bhuvana\\Desktop\\amazon\\Test Cases.xls");
		}
		


}
