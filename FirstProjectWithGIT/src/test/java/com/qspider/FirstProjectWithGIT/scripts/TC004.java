package com.qspider.FirstProjectWithGIT.scripts;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qspider.FirstProjectWithGIT.generic.ExcelLibrary;
import com.qspider.FirstProjectWithGIT.generic.Utilities;
import com.qspider.FirstProjectWithGIT.pages.CategoryPage;
import com.qspider.FirstProjectWithGIT.pages.HomePage;
import com.qspider.FirstProjectWithGIT.pages.OrderDetailPage;
import com.qspider.FirstProjectWithGIT.pages.ProductDetailPage;
public class TC004 extends BaseTest
{
	@DataProvider
	public Object[][] getData()
	{
		return ExcelLibrary.getAllExcelSheetData(XL_PATH, "TC004");
	}
	
	@Test(dataProvider="getData", description="Test With Multiple Sets Of Data")
	public void testMultipleItemAddedToKartUsingExcel(String menuItem,
													  String productID,
													  String quantity,
													  String size,
													  String color)
	{
		HomePage hp = new HomePage(driver, webActionUtil);
				
		productID = Utilities.getIntText(productID);
		int quant=Utilities.returnInteger(quantity);
		
		CategoryPage category = hp.clickOnMenu(menuItem);
		ProductDetailPage pdp = category.OpenProductInQuickView(productID);
		Assert.assertNotEquals(pdp, null);
		pdp.addItemToKart(quant, size, color);
		OrderDetailPage odp = pdp.clickOnProccedToCheckout();
		Assert.assertEquals(odp.isProductDisplayed(productID), true);
	}
}
