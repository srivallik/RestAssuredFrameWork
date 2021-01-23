/**
 * 
 */
package com.qa.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.util.TestBase;
import com.qa.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

/**
 * @author DELL
 *
 */
public class WeatherInfoTests extends TestBase {

	@BeforeTest
	public void setUp() {
		TestBase.init();
	}

	@DataProvider
	public Object[][] getData() {
		Object testdata[][] = TestUtil.getDataFromSheet(TestUtil.CountrySheetName);
		return testdata;
	}

	@Test(priority = 1, dataProvider = "getData")
	public void WeatherInfoTest(String city, String HTTPMethod, String capital, String currencies, String region) {

		RestAssured.baseURI = prop.getProperty("serviceurl");

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "/" + city);

		String responseBody = response.getBody().asString();
		System.out.println("response body: " + responseBody);

		Assert.assertEquals(responseBody.contains(capital), true);
		int status = response.getStatusCode();
		System.out.println("response code: " + status);
		Assert.assertEquals(status, TestUtil.RESPONSE_CODE_200);

		Headers headers = response.getHeaders();
		// System.out.println("Headers are: "+headers);

		JsonPath jsonPathValue = response.jsonPath();
		String cityName = jsonPathValue.get("name").toString();
		System.out.println("city name is: " + cityName);
		
		Assert.assertEquals(cityName, city);

		String capitalName = jsonPathValue.get("capital").toString();
		System.out.println("capital name is: " + capitalName);
		Assert.assertEquals(capitalName, capital);

		String currency = jsonPathValue.get("currencies").toString();
		System.out.println("Currency Name is: " + currency);
		Assert.assertEquals(currency, currencies);

		String regionName = jsonPathValue.getString("region").toString();
		System.out.println("Region name is: " + regionName);
		Assert.assertEquals(regionName, region);

	}

}
