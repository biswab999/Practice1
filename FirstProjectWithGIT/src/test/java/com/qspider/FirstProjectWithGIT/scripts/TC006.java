package com.qspider.FirstProjectWithGIT.scripts;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qspider.FirstProjectWithGIT.generic.ExcelLibrary;
import com.qspider.FirstProjectWithGIT.generic.Utilities;
import com.qspider.FirstProjectWithGIT.pages.CategoryPage;
import com.qspider.FirstProjectWithGIT.pages.HomePage;
import com.qspider.FirstProjectWithGIT.pages.OrderDetailPage;
import com.qspider.FirstProjectWithGIT.pages.ProductDetailPage;

public class TC006 extends BaseTest
{
	@Test(description="Verify Whether the deleted Product is not displayed in ODP")
	public void testDeleteAddedItemToKart()
	{
		HomePage hp = new HomePage(driver, webActionUtil);
		String sheetName = "TC006";
		
		String menuItem = ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 0);
		String productId = Utilities.getIntText(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 1));
		
		int quantity=Utilities.returnInteger(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 2));
		
		String size=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 3);
		String color=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 4);
		
		CategoryPage category = hp.clickOnMenu(menuItem);
		ProductDetailPage pdp = category.clickOnProduct(productId);
		pdp.increaseQuantity(quantity);
		pdp.selectSize(size);
		pdp.selectColor(color);
		pdp.clickOnAddToKart();
		OrderDetailPage odp = pdp.clickOnProccedToCheckout();
		Assert.assertEquals(odp.isProductDisplayed(productId), true);
		odp.deleteProduct(productId);
		Assert.assertEquals(odp.isProductNotDisplayed(productId), true);
	}
}
