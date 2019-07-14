package com.wire.qa.migration.service;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.wire.qa.migration.io.NameResponse;

/**
 * 
 * The main service that migrates a given full name . Here we validate if the
 * given full name matches a specified pattern and proceed ahead for the further
 * validations
 * 
 * @author bharat.gopalan
 *
 */
@Service
public class MigrationServiceImpl implements MigrationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MigrationServiceImpl.class);
	private static final String EMPTY_NAME_ERROR_MESSAGE = "Empty names are not allowed!";
	private static final String INVALID_NAME_ERROR_MESSAGE = "The given name %s is invalid, Name should be of the format "
			+ "'Prefix. Full Name , Suffix .'(Prefix and suffix are optional). An example will be Rev. Martin Luther King, Jr. Also the first name should "
			+ "be atleast 5 characters length and if a suffix is present it should end with a dot";
	/*
	 * The Salutation is optional and I have assumed that the salutation has a
	 * minimum of 2 chars and max of 4 . Possible examples are Mr. / Miss. , It is
	 * Quite possible to have spaces before or after the dot
	 */
	private static final String SALUTATION_MATCH_REGEX = "^(([\\w]{2,4})(\\s+)?\\.)?";
	/*
	 * Suffix optional and is at the end of the name starting with a comma and
	 * ending in a dot. Comma can start or end with space and there could be a space
	 * before or after the dot
	 */
	private static final String SUFFIX_MATCH_REGEX = "((\\s+)?,(\\s+)?([\\w]{2,})(\\s+)?\\.)?$";
	/*
	 * Name forms the middle of the string , Name is mandatory might have middle
	 * name as well as last name. I have assumed that the first name has a min
	 * length of 5
	 */
	private static final String NAME_MATCH_REGEX = "((\\s+)?[\\w]{5,}[\\w\\s+]*)";

	private static final String WHITESPACE_REGEX = "\\s+";

	private void validateNameFormat(final String givenFullName) {
		if (givenFullName == null || givenFullName.trim().isEmpty()) {
			throw new IllegalArgumentException(EMPTY_NAME_ERROR_MESSAGE);
		}
		if (!givenFullName
				.matches(SALUTATION_MATCH_REGEX + NAME_MATCH_REGEX + SUFFIX_MATCH_REGEX)) {
			throw new IllegalArgumentException(
					String.format(INVALID_NAME_ERROR_MESSAGE, givenFullName));
		}
	}

	/**
	 * Parse the given string get the salutation . We assume that the salutation is
	 * min 2 char in length
	 * 
	 * @param fullName The full name to parse
	 * @return A string representing a salutation (if any) or null
	 */
	private String getSalutation(final String fullName) {
		String salutation = getStringMatching(fullName, SALUTATION_MATCH_REGEX);
		if (salutation != null) {
			salutation = salutation.replaceAll(WHITESPACE_REGEX, "");
		}
		return salutation;
	}

	/**
	 * Parse the given string to get the suffix. The Suffix should always end in a
	 * dot and suffix should have atleast two characters
	 * 
	 * @param fullName The full name to parse
	 * @return A string representing a suffix (if any) or null
	 */
	private String getSuffix(final String fullName) {
		String suffix = getStringMatching(fullName, SUFFIX_MATCH_REGEX);
		if (suffix != null) {
			suffix = suffix.replaceAll(",", "").replaceAll(WHITESPACE_REGEX, "");
		}
		return suffix;
	}

	/**
	 * A utility method to match a given regex against a string
	 * 
	 * @param input The input string to match against
	 * @param regex The regex
	 * @return A matching string (if any) or null
	 */
	private String getStringMatching(final String input, final String regex) {
		LOGGER.debug("trying to locate a pattern {} in the input {}", regex, input);
		Pattern pattern = Pattern.compile(regex);
		String match = null;
		Matcher m = pattern.matcher(input);
		while (m.find()) {
			final String matchingGroup = m.group().trim();
			if (matchingGroup.length() > 0) {
				match = matchingGroup;
			}
		}
		return match;
	}

	private void setNames(final String nameString, final NameResponse migratedNameResponse) {
		final List<String> names = Arrays.asList(nameString.split(WHITESPACE_REGEX));
		migratedNameResponse.setFirstname(names.get(0));
		if (names.size() == 2) {
			migratedNameResponse.setLastname(names.get(1));
		} else if ((names.size() > 2)) {
			migratedNameResponse.setMiddlename(names.get(1));
			migratedNameResponse.setLastname(String.join(" ", names.subList(2, names.size())));
		}
	}

	public NameResponse migrateName(String fullName) {
		fullName = fullName.trim();
		LOGGER.debug("Got the full name string {}", fullName);
		validateNameFormat(fullName);
		final NameResponse migratedNameResponse = new NameResponse();
		migratedNameResponse.setSalutation(getSalutation(fullName));
		migratedNameResponse.setSuffix(getSuffix(fullName));
		setNames(getStringMatching(fullName, NAME_MATCH_REGEX), migratedNameResponse);
		return migratedNameResponse;
	}
}
