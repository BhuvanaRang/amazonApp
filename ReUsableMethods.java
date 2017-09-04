package amazonApp;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFHyperlink;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import practiceprograms.ExcelFile;

public class ReUsableMethods {

	static BufferedWriter bw = null;
	static BufferedWriter bw1 = null;
	static String htmlname;
	static String objType;
	static String objName;
	static String TestData;
	static String rootPath;
	static int report;


	static Date cur_dt = null;
	static String filenamer;
	static String TestReport;
	int rowcnt;
	static String exeStatus = "True";
	static int iflag = 0;
	static int j = 1;

	static String fireFoxBrowser;
	static String chromeBrowser;
	static String result;
	static int intRowCount = 0;
	static String dataTablePath;
	static int i;
	static String browserName;
	static Map<String, String[]> objectRepo = new HashMap<>();
	static HSSFWorkbook resultWorkbook; 

	public static void loadObjectRepo(String fileName) throws IOException {
		Iterator<Row> itr=ExcelFile.readExcel(fileName);

		itr.next();					
		while(itr.hasNext()){

			Row row=itr.next();
			String objName=row.getCell(1).getStringCellValue();//gets the password

			String objType = row.getCell(2).getStringCellValue();//xpath
			String objProperty= row.getCell(3).getStringCellValue();////input[@id='password']
			String[] values = {objType, objProperty};
			objectRepo.put(objName.trim(), values);// to remove leading and trailing spaces remove--->trim
		}
	}

	
	public static List<Row> loadTestCases(String fileName) throws IOException {
		File xlFile=new File(fileName);
		FileInputStream xlDoc=new FileInputStream(xlFile);
		resultWorkbook =new HSSFWorkbook (xlDoc);
		HSSFSheet sheet=resultWorkbook.getSheet("Sheet1");
		Iterator<Row> itr = sheet.iterator();

		List<Row> testCases = new ArrayList<Row>();

		itr.next();
		while(itr.hasNext()){

			Row row=itr.next();
			String toRun = row.getCell(2).getStringCellValue();
			if (toRun.equalsIgnoreCase("Y")){
				testCases.add(row);
			}
		}
		System.out.println("Closing FileInputStream");
		xlDoc.close();
		
		return testCases;
	}

	/* Name Of the method: enterText
	 * Brief Description: Enter the text value to the text box
	 * Arguments: obj --> Text box object, textVal --> value to be entered, objName --> name of the object
	 * Created by: Automation team
	 * Creation Date: Aug 23 2017
	 * Last Modified: Aug 23 2017
	 * */
	public static void enterText(WebElement obj, String textVal, String objName) throws IOException{
		if(obj.isDisplayed()){
			obj.sendKeys(textVal);
			Update_Report("Pass: " , " is entered in " ," field");
		}else{
			Update_Report("Fail: " , objName , objName +" please check your application ");

		}

	}
	/* Name Of the method: clickButton
	 * Brief Description: Click on button
	 * Arguments: obj --> web object,  objName --> name of the object
	 * Created by: Automation team
	 * Creation Date: Aug 23 2017
	 * Last Modified: Aug 23 2017
	 * */

	public static void clickButton(WebElement obj,  String objName) throws IOException{

		if(obj.isDisplayed()){
			obj.click();
			Update_Report("Pass: " ,objName, "clicked");
		}else{
			Update_Report("Fail: " , objName , objName + " field is not displayed,please check your application");
		}
	}

	public static void startReport(String scriptName, String ReportsPath) throws IOException{

		String strResultPath = null;
		String testScriptName =scriptName;

		cur_dt = new Date(); 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String strTimeStamp = dateFormat.format(cur_dt);

		if (ReportsPath == "") { 

			ReportsPath = "C:\\";
		}

		if (ReportsPath.endsWith("\\")) { 
			ReportsPath = ReportsPath + "\\";
		}

		strResultPath = ReportsPath + "/"
				+ "Log" + "/" +testScriptName +"/"; 
		File f = new File(strResultPath);
		f.mkdirs();
		htmlname = strResultPath  + testScriptName + "_" + strTimeStamp 
				+ ".html";

		bw = new BufferedWriter(new FileWriter(htmlname));

		bw.write("<HTML><BODY><TABLE BORDER=0 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TABLE BORDER=0 BGCOLOR=BLACK CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR><TD BGCOLOR=#66699 WIDTH=27%><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>Browser Name</B></FONT></TD><TD COLSPAN=6 BGCOLOR=#66699><FONT FACE=VERDANA COLOR=WHITE SIZE=2><B>"
				+ "FireFox " + "</B></FONT></TD></TR>");
		bw.write("<HTML><BODY><TABLE BORDER=1 CELLPADDING=3 CELLSPACING=1 WIDTH=100%>");
		bw.write("<TR COLS=7><TD BGCOLOR=#BDBDBD WIDTH=3%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>SL No</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Step Name</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Execution Time</B></FONT></TD> "
				+ "<TD BGCOLOR=#BDBDBD WIDTH=10%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Status</B></FONT></TD>"
				+ "<TD BGCOLOR=#BDBDBD WIDTH=47%><FONT FACE=VERDANA COLOR=BLACK SIZE=2><B>Detail Report</B></FONT></TD></TR>");

	}

	public static void Update_Report(String Res_type,String Action, String result) throws IOException {
		String str_time;
		Date exec_time = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		str_time = dateFormat.format(exec_time);
		if (Res_type.startsWith("Pass")) {
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ "Passed"
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = GREEN>"
					+ result + "</FONT></TD></TR>");

		} else if (Res_type.startsWith("Fail")) {
			exeStatus = "Failed";
			report = 1;
			bw.write("<TR COLS=7><TD BGCOLOR=#EEEEEE WIDTH=3%><FONT FACE=VERDANA SIZE=2>"
					+ (j++)
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+Action
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2>"
					+ str_time
					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=10%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ "<a href= "
					+ htmlname
					+ "  style=\"color: #FF0000\"> Failed </a>"

					+ "</FONT></TD><TD BGCOLOR=#EEEEEE WIDTH=30%><FONT FACE=VERDANA SIZE=2 COLOR = RED>"
					+ result + "</FONT></TD></TR>");
		} 
	}

	public static By byLocator(String objName) {

		String[] values = objectRepo.get(objName);
		return byLocator(values[0], values[1]);
	}


	public static By byLocator(String objType, String value) {

		switch (objType) {
		case "id":
			return By.id(value);
		case "xpath":
			return By.xpath(value);
		case "className":
			return By.className(value);
		case "name":
			return By.name(value);
		case "linkText":
			return By.linkText(value);
		case "partialLinkText":
			return By.partialLinkText(value);
		case "cssSelector":
			return By.cssSelector(value);
		case "tagName":
			return By.tagName(value);
		default:
			System.out.println("Unknown type");
			return null;
		}
	}


	public static String verify(String expected, String actual)
	{
		String result =null;
		if(expected.equals(actual))
		{
			result = "Pass";
			return result;
		}
		else
		{
			System.out.println("Expected " + expected + "Actual " + actual);
			result = "Fail";
			return result;
		}
	}

	public static String verify(boolean expected, boolean actual)
	{
		String result =null;
		if(expected==actual)
		{
			result = "Pass";
			return result;
		}
		else
		{
			result = "Fail";
			return result;
		}
	}

	public static String verify(List<String> expected, List<String> actual)
	{
		String result ="Pass";
		for(String s:expected){
			if(!actual.contains(s))
			{
				System.out.println(s);
				result = "Fail";
				break;
			}
		}
		return result ;
	}
	
	public static WebDriver getDriver(String browser) {
		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\Bhuvana\\Downloads\\geckodriver-v0.18.0-win32\\geckodriver.exe");
			return new FirefoxDriver();
		} else if(browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\Bhuvana\\Downloads\\chromedriver.exe");
			return new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\Users\\Bhuvana\\Downloads\\IEDriverServer.exe");
			return new InternetExplorerDriver();
		}
		return null;
	}
	
	public static int getIndex(WebDriver driver) {
		if (driver instanceof FirefoxDriver) {
			return 3;
		} else if(driver instanceof ChromeDriver) {
			return 4;
		} else if (driver instanceof InternetExplorerDriver) {
			return 5;
		}
		return -1;
	}
	
	
	public static void updateResultsExcel(Row row, String result, WebDriver driver) {

		HSSFCellStyle style = resultWorkbook.createCellStyle();
		if (result.equalsIgnoreCase("Pass")) {
			style.setFillForegroundColor(HSSFColor.GREEN.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		} else {
			style.setFillForegroundColor(HSSFColor.RED.index);
			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		}
		
		Cell cell = row.createCell(getIndex(driver));
		cell.setCellValue(result);
		cell.setCellStyle(style);
		String reportPath = getLatestReport(row.getCell(1).getStringCellValue());
		HSSFHyperlink hyperLink = new HSSFHyperlink(HSSFHyperlink.LINK_FILE);
		hyperLink.setAddress(reportPath);
		cell.setHyperlink(hyperLink);
	}
	
	public static String getLatestReport(String testCaseName) {
		
		String dir = "C:\\Users\\Bhuvana\\Desktop\\amazon\\Report\\Log\\" + testCaseName;
		File fDir = new File(dir);
		File[] files = fDir.listFiles();
		
		if (files == null || files.length == 0) {
	        return null;
	    }

	    File lastModifiedFile = files[0];
	    for (int i = 1; i < files.length; i++) {
	       if (lastModifiedFile.lastModified() < files[i].lastModified()) {
	           lastModifiedFile = files[i];
	       }
	    }
	    return lastModifiedFile.getAbsolutePath();
	}
	
	public static void writeResultsExcel(String fileName) throws IOException {
		
		FileOutputStream fileOut = new FileOutputStream(fileName);
		resultWorkbook.write(fileOut);
		fileOut.close();
	}
	
	public static List<String> getTextList(List<WebElement> elements) {
		List<String> textList = new ArrayList<>();
		for (WebElement elem : elements) {
			textList.add(elem.getText().trim());
		}
		return textList;
	}
}



