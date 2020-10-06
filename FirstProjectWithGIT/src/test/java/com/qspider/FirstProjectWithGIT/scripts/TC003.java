package com.qspider.FirstProjectWithGIT.scripts;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qspider.FirstProjectWithGIT.generic.ExcelLibrary;
import com.qspider.FirstProjectWithGIT.generic.Utilities;
import com.qspider.FirstProjectWithGIT.pages.CategoryPage;
import com.qspider.FirstProjectWithGIT.pages.HomePage;
import com.qspider.FirstProjectWithGIT.pages.OrderDetailPage;
import com.qspider.FirstProjectWithGIT.pages.ProductDetailPage;
//Quick View With Excel
public class TC003 extends BaseTest
{
	@Test(description="Using Data Driven Verify Whether the Added Product From Quick View is displayed in ODP")
	public void testItemAddedToKartUsingExcel()
	{
		HomePage hp = new HomePage(driver, webActionUtil);
		String sheetName = "TC003";
		
		String menuItem = ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 0);
		String productID = Utilities.getIntText(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 1));
		
		int quantity=Utilities.returnInteger(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 2));
		
		String size=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 3);
		String color=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 4);
		
		CategoryPage category = hp.clickOnMenu(menuItem);
		ProductDetailPage pdp = category.OpenProductInQuickView(productID);
		Assert.assertNotEquals(pdp, null);
		pdp.addItemToKart(quantity, size, color);
		OrderDetailPage odp = pdp.clickOnProccedToCheckout();
		Assert.assertEquals(odp.isProductDisplayed(productID), true);
	}
}
