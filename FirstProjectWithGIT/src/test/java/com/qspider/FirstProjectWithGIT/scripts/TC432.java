package com.qspider.FirstProjectWithGIT.scripts;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qspider.FirstProjectWithGIT.generic.ExcelLibrary;
import com.qspider.FirstProjectWithGIT.generic.Utilities;
import com.qspider.FirstProjectWithGIT.pages.CategoryPage;
import com.qspider.FirstProjectWithGIT.pages.HomePage;
import com.qspider.FirstProjectWithGIT.pages.OrderDetailPage;
import com.qspider.FirstProjectWithGIT.pages.ProductDetailPage;
public class TC432 extends BaseTest
{
	@Test(description="verify the unit price and total price in ODP")
	public void testProductPriceInODP()
	{
		String sheetName="TC007";
		String menuItem = ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 0);
		String productID = Utilities.getIntText(ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 1));
		
		String size=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 2);
		String color=ExcelLibrary.getCellData(XL_PATH, sheetName, 1, 3);
		
		HomePage hp = new HomePage(driver, webActionUtil);
		CategoryPage category = hp.clickOnMenu(menuItem);
		ProductDetailPage pdp = category.clickOnProduct(productID);
		String PDPUnitPrice = pdp.getProductUnitPrice(); 
		pdp.selectSize(size);
		pdp.selectColor(color);
		pdp.clickOnAddToKart();
		OrderDetailPage odpPage = pdp.clickOnProccedToCheckout();
		/*Assert.assertEquals(odpPage.verifyUnitPrice("3","$5"),
					true, "Actual Price Displayed::"+odpPage.getODPUnitPrice("3"));*/
		Assert.assertEquals(odpPage.getODPUnitPrice(productID), PDPUnitPrice);
	}
}
