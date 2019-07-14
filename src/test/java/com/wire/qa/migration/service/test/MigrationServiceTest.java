package com.wire.qa.migration.service.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.wire.qa.migration.io.NameResponse;
import com.wire.qa.migration.service.MigrationService;
import com.wire.qa.migration.service.MigrationServiceImpl;

/**
 * The Test NG class for migration service unit tests. Here we verify the
 * service layer . The preferred way is to mock the service and inject it . The
 * use case in this case is very simple, So I instantiate the service directly
 * 
 * @author bharat.gopalan
 *
 */
public class MigrationServiceTest {

	private static final MigrationService MIGRATION_SERVICE = new MigrationServiceImpl();

	private String checkAndSetEmptyValuesAsNull(final String value) {
		if (value == null || value.isEmpty()) {
			return null;
		}
		return value.trim();
	}

	private NameResponse buildMigrationResponse(final String firstName, final String middleName,
			final String lastName, final String salutation, final String suffix) {
		final NameResponse response = new NameResponse();
		response.setFirstname(checkAndSetEmptyValuesAsNull(firstName));
		response.setMiddlename(checkAndSetEmptyValuesAsNull(middleName));
		response.setLastname(checkAndSetEmptyValuesAsNull(lastName));
		response.setSalutation(checkAndSetEmptyValuesAsNull(salutation));
		response.setSuffix(checkAndSetEmptyValuesAsNull(suffix));
		return response;
	}

	@DataProvider(name = "namesDataProvider")
	private Object[][] namesDataProvider() {
		return new String[][] { { "Frida Kahlo", "Frida", null, "Kahlo", null, null },
				{ "Oscar Claude Monet", "Oscar", "Claude", "Monet", null, null },
				{ "Vincent Willem van Gogh", "Vincent", "Willem", "van Gogh", null, null },
				{ "Ms. Berthe Morisot", "Berthe", null, "Morisot", "Ms.", null },
				{ "George Barret, Jr.", "George", null, "Barret", null, "Jr." } };
	}

	@DataProvider(name = "invalidNamesProvider")
	private Object[][] invalidNamesProvider() {
		return new Object[][] { 
			//Empty name 
			{ "" }, 
			//Invalid dots in the name 
			{ "Mr..Invalid Name,,Jr" } , 
			//Name less than 5 letters 
			{"foob"},
			//Salutation less than 2 chars 
			{"M.invalid salutation"},
			// Suffix less than two character
			{"Invalid Suffix, C."},
			// Suffix without ending in dot
			{"Suffix without ending in dot, Jr"}};
	}

	@Test(description = "Check the common positive migration cases provided in the code challenge", 
			dataProvider = "namesDataProvider")
	public void verifyMigratedNames(final String fullName, final String expectedFirstName,
			final String expectedMiddleName, final String expectedLastName,
			final String expectedSalutation, final String expectedSuffix) {
		final NameResponse expectedResponse = buildMigrationResponse(expectedFirstName,
				expectedMiddleName, expectedLastName, expectedSalutation, expectedSuffix);
		final NameResponse actualResponse = MIGRATION_SERVICE.migrateName(fullName);
		Assert.assertEquals(actualResponse.getFirstname(), expectedResponse.getFirstname(),
				"The migrated first name does not match with the expected value for the full name "
						+ fullName);
		Assert.assertEquals(actualResponse.getLastname(), expectedResponse.getLastname(),
				"The migrated last name does not match with the expected value for the full name "
						+ fullName);
		Assert.assertEquals(actualResponse.getMiddlename(), expectedResponse.getMiddlename(),
				"The migrated middle name does not match with the expected value for the full name "
						+ fullName);
		Assert.assertEquals(actualResponse.getSalutation(), expectedResponse.getSalutation(),
				"The migrated salutation name does not match with the expected value for the full name "
						+ fullName);
		Assert.assertEquals(actualResponse.getSuffix(), expectedResponse.getSuffix(),
				"The migrated suffix name does not match with the expected value  for the full name "
						+ fullName);
	}

	/**
	 * Here we except the service to throw an exception
	 */
	@Test(expectedExceptions = IllegalArgumentException.class, 
			description = "Check if the system throws exception on  an empty name", 
			dataProvider = "invalidNamesProvider")
	public void verifyInvalidName(final String invalidFullName) {
		MIGRATION_SERVICE.migrateName(invalidFullName);
	}
	
	
	

}
