/*Amazon App Automation Scripts*/


//TC1  Search iphone6 and add to cart

package amazonApp;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class AmazonAutomationScript extends ReUsableMethods{

	public static String searchAndAdd(WebDriver driver) throws Exception {

		startReport("searchAndAdd", "C:\\Users\\Bhuvana\\Desktop\\amazon\\Report");
		try{

			driver.get("https://www.amazon.com/");

			Thread.sleep(2000);
			
			String enteredText="iphone 6";
			WebElement enterText = driver.findElement(byLocator("enterText"));
			enterText(enterText,enteredText, "Enter the text");

			Thread.sleep(2000);
			WebElement searchButton = driver.findElement(byLocator("searchButton"));
			clickButton(searchButton, "Click the search button");

			WebElement clickLink = driver.findElement(byLocator("clickLink"));
			clickButton(clickLink, "Click the link that displays iphone 6 ");

			Thread.sleep(3000);

			WebElement addToCartButton = driver.findElement(byLocator("addToCartButton"));
			clickButton(addToCartButton, "iphone 6 is added to the cart ");

			Thread.sleep(2000);

			// add insurance
			try {
				WebElement addInsurance = driver.findElement(byLocator("addInsurance"));
				if(addInsurance != null) {
					addInsurance.click();
					Thread.sleep(2000);
				}	
			} catch(Exception e){
				
			}
			

			String cartText= driver.findElement(byLocator("cartIcon")).getText();
			String actual = cartText;
			String expected = "1";
			String result = verify(expected,actual);
			System.out.println(result);
			Update_Report( result, "1 should be displayed in the cart button",  "Execution Completed");
			return result;

		} catch(Exception e) {
			e.printStackTrace();
			return "Fail";
		} finally {
			bw.close();
		}
	}
	/*********************************************************************************************************************/

	//TC2  Check all the list in <Main Tab> available in the page

	public static String checkAllDropDown(WebDriver driver) throws Exception {

		startReport("checkAllDropDown", "C:\\Users\\Bhuvana\\Desktop\\amazon\\Report");
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Bhuvana\\Downloads\\geckodriver-v0.18.0-win32\\geckodriver.exe");
		try{

			result = "Fail";
			
			driver.get("https://www.amazon.com/");

			Actions action=new Actions(driver);
			WebElement deptDropDown = driver.findElement(byLocator("deptDropDown"));
			action.moveToElement(deptDropDown).build().perform();

			WebElement yourAmazonLink = driver.findElement(byLocator("yourAmazonLink"));

			boolean actual=yourAmazonLink.isEnabled()&&yourAmazonLink.isDisplayed();

			boolean expected = true;
			result = verify(expected,actual);
			System.out.println(result);
			Update_Report( result, "Your Amazon link should be clickable",  "Execution Completed");

			WebElement todaysDeals = driver.findElement(byLocator("todaysDeals"));
			actual=todaysDeals.isEnabled()&&todaysDeals.isDisplayed();
			expected = true;
			result = verify(expected,actual);
			System.out.println(result);
			Update_Report( result, "Your Today's deals should be clickable",  "Execution Completed");
			return result;	

		} catch(Exception e) {
			
			e.printStackTrace();
			return result;
		} finally {
			bw.close();
		}
	}

	/*********************************************************************************************************************/

	//TC3  Validate Department tab dropdown list

	public static String deptDropDownList(WebDriver driver) throws Exception {

		startReport("deptDropDownList", "C:\\Users\\Bhuvana\\Desktop\\amazon\\Report");
		try{

			driver.get("https://www.amazon.com/");

			Actions action=new Actions(driver);
			WebElement deptDropDown = driver.findElement(byLocator("deptDropDown"));
			action.moveToElement(deptDropDown).build().perform();

			Thread.sleep(1000);

			List<WebElement> deptDropDownList = driver.findElements(byLocator("deptDropDownList"));

			List<String> actual=new ArrayList<>();
			for(WebElement elements:deptDropDownList){
				String depttext=elements.getText();
				actual.add(depttext);
			}

			List<String> expected=new ArrayList<String>();
			expected.add("Amazon Video");
			expected.add("Amazon Music");
			expected.add("Appstore for Android");
			expected.add("Kindle E-readers & Books");
			expected.add("Fire Tablets");
			expected.add("Fire TV");
			expected.add("Echo & Alexa");
			expected.add("Books & Audible");
			expected.add("Movies, Music & Games");
			expected.add("Electronics, Computers & Office");
			expected.add("Home, Garden & Tools");
			expected.add("Food & Grocery");
			expected.add("Toys, Kids & Baby");
			expected.add("Clothing, Shoes & Jewelry");
			expected.add("Handmade");
			expected.add("Sports & Outdoors");
			expected.add("Automotive & Industrial");
			expected.add("Home Services");
			expected.add("Credit & Payment Products");

			result=verify(expected, actual);
			if (result.equalsIgnoreCase("Fail")) {
				return result;
			}
			System.out.println(result);
			Update_Report( result, "It shows the Dept dropdown List",  "Execution Completed");

			WebElement storeDirectroy = driver.findElement(byLocator("storeDirectory"));
			String actualDir=storeDirectroy.getText();
			String expectedDir="Full Store Directory";
			result=verify(expectedDir, actualDir);
			System.out.println(result);
			Update_Report( result, "Shows the Full Store Directory ",  "Execution Completed");
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return "Fail";
		} finally {
			bw.close();
		}
	}

	/*********************************************************************************************************************/

	//TC4  Validate 'Your Account' drop down list

	public static String yourAcctDropDownList(WebDriver driver) throws Exception {

		startReport("yourAcctDropDownList", "C:\\Users\\Bhuvana\\Desktop\\amazon\\Report"); 
		try{

			driver.get("https://www.amazon.com/");

			Actions action=new Actions(driver);
			WebElement acctAndListDropdwn = driver.findElement(byLocator("acctAndListDropdwn"));
			action.moveToElement(acctAndListDropdwn).build().perform();

			Thread.sleep(1000);

			List<WebElement> acctAndList = driver.findElements(byLocator("acctAndListDropdwnList"));

			List<String> actual=new ArrayList<>();
			for(WebElement elements:acctAndList){
				String acctAndListtext=elements.getText();
				actual.add(acctAndListtext);
			}

			List<String> expected=new ArrayList<String>();
			expected.add("Your Account");
			expected.add("Your Orders");
			expected.add("Your Lists");
			expected.add("Your Recommendations");
			expected.add("Your Subscribe & Save Items");
			expected.add("Your Memberships & Subscriptions");
			expected.add("Your Service Requests");
			expected.add("Your Prime Membership");
			expected.add("Your Garage");
			expected.add("Register for a Business Account");
			expected.add("Your Amazon Credit Cards");
			expected.add("Your Content and Devices");
			expected.add("Your Music Library");
			expected.add("Your Prime Photos");
			expected.add("Your Amazon Drive");
			expected.add("Your Prime Video");

			result=verify(expected, actual);
			System.out.println(result);
			Update_Report( result, "It shows the Account and List dropdown List",  "Execution Completed");
			return result;
		} catch(Exception e) {
			e.printStackTrace();
			return "Fail";
		} finally {
			bw.close();
		}
	}

	/*********************************************************************************************************************/

	//TC5 Click on all menu dropdown from Search bar

	public static String allDropDownList(WebDriver driver) throws Exception {

		startReport("allDropDownList", "C:\\Users\\Bhuvana\\Desktop\\amazon\\Report");

		try{

			driver.get("https://www.amazon.com/");

			WebElement allSearchDropDown = driver.findElement(byLocator("allSearchDropDown"));
			clickButton(allSearchDropDown, "Click the All search drop down menu");

			Thread.sleep(1000);

			List<WebElement> allMenuSearchList = driver.findElements(byLocator("allMenuSearchList"));

			List<String> actual=new ArrayList<>();
			for(WebElement elements:allMenuSearchList){
				String allMenuSearch=elements.getText();
				actual.add(allMenuSearch);
			}

			List<String> expected=new ArrayList<String>();
			expected.add("All Departments");
			expected.add("Alexa Skills");
			expected.add("Amazon Devices");
			expected.add("Amazon Video");
			expected.add("Amazon Warehouse Deals");
			expected.add("Appliances");
			expected.add("Apps & Games");
			expected.add("Arts, Crafts & Sewing");
			expected.add("Automotive Parts & Accessories");
			expected.add("Baby");
			expected.add("Beauty & Personal Care");
			expected.add("Books");
			expected.add("CDs & Vinyl");
			expected.add("Cell Phones & Accessories");
			expected.add("Clothing, Shoes & Jewelry");
			expected.add("   Women");
			expected.add("   Men");
			expected.add("   Girls");
			expected.add("   Boys");
			expected.add("   Baby");
			expected.add("Collectibles & Fine Art");
			expected.add("Computers");
			expected.add("Courses");
			expected.add("Credit and Payment Cards");
			expected.add("Digital Music");
			expected.add("Electronics");
			expected.add("Gift Cards");
			expected.add("Grocery & Gourmet Food");
			expected.add("Handmade");
			expected.add("Health, Household & Baby Care");
			expected.add("Home & Business Services");
			expected.add("Home & Kitchen");
			expected.add("Industrial & Scientific");
			expected.add("Kindle Store");
			expected.add("Luggage & Travel Gear");
			expected.add("Luxury Beauty");
			expected.add("Magazine Subscriptions");
			expected.add("Movies & TV");
			expected.add("Musical Instruments");
			expected.add("Office Products");
			expected.add("Patio, Lawn & Garden");
			expected.add("Pet Supplies");
			expected.add("Prime Exclusive");
			expected.add("Prime Pantry");
			expected.add("Software");
			expected.add("Sports & Outdoors");
			expected.add("Tools & Home Improvement");
			expected.add("Toys & Games");
			expected.add("Vehicles");
			expected.add("Video Games");
			expected.add("Wine");

			result=verify(expected, actual);
			System.out.println(result);
			Update_Report( result, "It shows the All menu search dropdown List",  "Execution Completed");
			return result;

		} catch(Exception e) {
			e.printStackTrace();
			return "Fail";
		} finally {
			bw.close();
			driver.close();
		}
	}

	/*********************************************************************************************************************/

	//TC6  Empty Cart Validation1 - Check whether cart is empty

	public static String checkCartEmpty(WebDriver driver) throws Exception {

		startReport("checkCartEmpty", "C:\\Users\\Bhuvana\\Desktop\\amazon\\Report");
		try{

			driver.get("https://www.amazon.com/");

			String enteredText="iphone 6s";
			WebElement enterText = driver.findElement(byLocator("enterText"));
			enterText(enterText,enteredText, "Enter the text iphone 6s");

			WebElement clickButton = driver.findElement(byLocator("searchButton"));
			clickButton(clickButton, "Click Search Button");

			WebElement iphoneModelSelect = driver.findElement(byLocator("iphoneModelSelect"));
			clickButton(iphoneModelSelect, "click the iphone 6s 64GB");

			Thread.sleep(2000);
						
			WebElement addToCartButton = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.elementToBeClickable(byLocator("addToCartButton")));
			
			clickButton(addToCartButton, "iphone 6s 64GB is added to the cart ");
			Thread.sleep(2000);

			try {

				WebElement addInsurance = (new WebDriverWait(driver, 10))
						.until(ExpectedConditions.elementToBeClickable(byLocator("addInsurance")));

				if(addInsurance != null) {
					addInsurance.click();
					Thread.sleep(2000);
				}	
			} catch(Exception e) {

			}


			WebElement cartIcon = driver.findElement(byLocator("cartIcon"));
			clickButton(cartIcon, "Click the cart icon");

			WebElement deleteCartItem = driver.findElement(byLocator("deleteCartItem"));
			clickButton(deleteCartItem, "Click the cart icon");

			driver.navigate().refresh();

			cartIcon = driver.findElement(byLocator("cartIcon"));
			clickButton(cartIcon, "Click the cart icon");

			Thread.sleep(2000);
			String scCartEmpty = driver.findElement(byLocator("shoppingCartEmpty")).getText();
			String actual = scCartEmpty;
			String expected = "Your Shopping Cart is empty.";
			String result = verify(expected,actual);
			System.out.println(result);
			Update_Report( result, "Your shopping cart is empty should be displayed","Execution Completed");
			return result;

		} catch(Exception e) {
			e.printStackTrace();
			return "Fail";
		} finally {
			bw.close();
		}
	}

	/*********************************************************************************************************************/

	//TC7  To Verify Contents on help page  


	public static String verifyHelpPage(WebDriver driver) throws Exception {

		startReport("verifyHelpPage", "C:\\Users\\Bhuvana\\Desktop\\amazon\\Report");
		try{

			driver.get("https://www.amazon.com/");

			WebElement helpLink = driver.findElement(byLocator("helpLink"));
			clickButton(helpLink, "click the help link");

			Thread.sleep(5000);

			String helpMessage = driver.findElement(byLocator("helpText")).getText().trim();

			String actual = helpMessage;
			String expected = "Hello. What can we help you with?";
			String result = verify(expected,actual);
			System.out.println(result);
			if (result.equalsIgnoreCase("Fail")) {
				Update_Report( result, "What can we help you with? should be displayed","Execution Completed");
				return result;
			}

			List<String> actualList = getTextList(driver.findElements(byLocator("allHelpLinks")));
			List<String> expectedList = new ArrayList<>();
			expectedList.add("Your Orders");
			expectedList.add("Returns & Refunds");
			expectedList.add("Device Support");
			expectedList.add("Manage Prime");
			expectedList.add("Payments & Gift Cards");
			expectedList.add("Account Settings");

			result = verify(expectedList, actualList);

			Update_Report( result, "Displayed Help Menu","Execution Completed");
			return result;

		} catch(Exception e) {
			e.printStackTrace();
			return "Fail";
		} finally {
			bw.close();
		}
	}

	/*********************************************************************************************************************/

	//TC8  Search for item in search box and add the quantity and add to cart

	public static String searchItemBook(WebDriver driver) throws Exception {

		startReport("searchItemBook", "C:\\Users\\Bhuvana\\Desktop\\amazon\\Report");
		result = "Fail";
		try{

			driver.get("https://www.amazon.com/");

			String enteredText="Head First Java in Books";
			WebElement enterText = driver.findElement(byLocator("enterText"));
			enterText(enterText,enteredText, "Enter the text Head First Java, 2nd Edition");

			WebElement searchIcon = driver.findElement(byLocator("searchButton"));
			clickButton(searchIcon, "Click Search Button");

			WebElement clickItemBook = driver.findElement(byLocator("clickItemBook"));
			clickButton(clickItemBook, "click the text Head First Java, 2nd Edition link with option to add quantity ");

			WebElement quantityDropDown = driver.findElement(byLocator("quantityDropDown"));
			clickButton(quantityDropDown, "click the quantity drop down");

			Select select=new Select(quantityDropDown);
			select.selectByVisibleText("5");

			WebElement addToCartButton = driver.findElement(byLocator("addToCartButton"));
			clickButton(addToCartButton, "The item has been added to the cart and count incremented to 5");

			String itemCountDisp = driver.findElement(byLocator("itemCountDisp")).getText();
			String actual = itemCountDisp.trim();
			String expected = "Cart subtotal (5 items):";
			result = verify(expected,actual);
			System.out.println(result);
			Update_Report( result, "The total books quantity 5 isdisplayed","Execution Completed");
		} catch(Exception e) {
			e.printStackTrace();
			Update_Report( result, "The total books quantity 5 is displayed","Execution Completed");
		} finally {
			bw.close();
		}
		return result;
	}

	/*********************************************************************************************************************/

	//TC9  Update the quantity of item  and add to "save for later"


	public static String saveForLater(WebDriver driver) throws Exception {

		startReport("saveForLater", "C:\\Users\\Bhuvana\\Desktop\\amazon\\Report");
		try{

			driver.get("https://www.amazon.com/");

			String enteredText="Head First Java in Books";
			WebElement enterText = driver.findElement(byLocator("enterText"));
			enterText(enterText,enteredText, "Enter the text Head First Java, 2nd Edition");

			WebElement searchIcon = driver.findElement(byLocator("searchButton"));
			clickButton(searchIcon, "Click Search Button");

			WebElement clickItemBook = driver.findElement(byLocator("clickItemBook"));
			clickButton(clickItemBook, "click the text Head First Java, 2nd Edition link with option to add quantity ");

			WebElement quantityDropDown = driver.findElement(byLocator("quantityDropDown"));
			clickButton(quantityDropDown, "click the quantity drop down");

			Select select=new Select(quantityDropDown);
			select.selectByVisibleText("5");

			WebElement addToCartButton = driver.findElement(byLocator("addToCartButton"));
			clickButton(addToCartButton, "The item has been added to the cart and count incremented to 5");

			WebElement cartIcon = driver.findElement(byLocator("cartIcon"));
			clickButton(cartIcon, "Shopping cart is displayed with item added to the cartwith qty 5");

			WebElement quantityDropDownCart = driver.findElement(byLocator("quantityDropDownCart"));
			clickButton(quantityDropDownCart, "click the quantity drop down to change the qty to 4 from 5");

			Select select1=new Select(quantityDropDownCart);
			select1.selectByVisibleText("4");

			WebElement saveForLaterLink = (new WebDriverWait(driver, 10))
					.until(ExpectedConditions.elementToBeClickable(byLocator("saveForLaterLink")));

			clickButton(saveForLaterLink, "The save for later link is clicked");

			Thread.sleep(3000);

			String actual = driver.findElement(byLocator("secondText")).getText();
			String expected = "Head First Java, 2nd Edition has been moved to Save for Later.";
			String result = verify(expected,actual);
			System.out.println(result);
			Update_Report( result, "The shopping cart page is displayed with the message Head First Java, 2nd Edition has been moved to Save for Later.","Execution Completed");
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Fail";
		} finally {
			bw.close();
		}
	}


	/*********************************************************************************************************************/

	//TC10  Enter iphone in search text and check for 1st three Display in the drop down option

	public static String searchAndDisplay(WebDriver driver) throws Exception {
		startReport("searchAndDisplay", "C:\\Users\\Bhuvana\\Desktop\\amazon\\Report");
		try{

			driver.get("https://www.amazon.com/");

			String enteredText="iphone";
			WebElement enterText = driver.findElement(byLocator("enterText"));
			enterText(enterText,enteredText, "Enter the text iphone");

			Thread.sleep(2000);
			
			String keyword = driver.findElement(byLocator("firstDisplay")).getAttribute("data-keyword");
			String dept = driver.findElement(byLocator("firstDisplay")).getAttribute("data-store");
			
			String deptString = dept.isEmpty() ? "All Departments" : dept;
			
			String actual = keyword + " in " + deptString;
			
			String expected = "iphone charger in All Departments";
			result = verify(expected,actual);
			System.out.println(result);
			Update_Report( result, "iphone charger in All Departments is displayed","Execution Completed");
			if (result.equalsIgnoreCase("Fail")){
				return result;
			}
			
			keyword = driver.findElement(byLocator("secondDisplay")).getAttribute("data-keyword");
			dept = driver.findElement(byLocator("secondDisplay")).getAttribute("data-store");
			deptString = dept.isEmpty() ? "All Departments" : dept;
			actual = keyword + " in " + deptString;

			expected = "iphone charger in Electronics";
			result = verify(expected,actual);
			System.out.println(result);
			Update_Report( result, "iphone charger in Electronics is displayed","Execution Completed");
			if (result.equalsIgnoreCase("Fail")){
				return result;
			}

			keyword = driver.findElement(byLocator("thirdDisplay")).getAttribute("data-keyword");
			dept = driver.findElement(byLocator("thirdDisplay")).getAttribute("data-store");
			deptString = dept.isEmpty() ? "All Departments" : dept;
			actual = keyword + " in " + deptString;
			
			expected = "iphone charger in Cell Phones & Accessories";
			result = verify(expected,actual);
			System.out.println(result);
			Update_Report( result, "iphone charger is displayed","Execution Completed");
			return result;
		}
		catch(Exception e) {
			e.printStackTrace();
			return "Fail";
		} finally {
			bw.close();
		}
	}
}