package com.wire.qa.migration.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wire.qa.migration.io.NameResponse;
import com.wire.qa.migration.service.MigrationService;

@RestController
@RequestMapping("/api/v1")
public class MigrationController {
	private static final Logger LOGGER = LoggerFactory.getLogger(MigrationController.class);

	@Autowired
	private MigrationService migrationService;

	@RequestMapping(method = RequestMethod.GET, path = "/migrate")
	public ResponseEntity<NameResponse> migrateName(
			@RequestParam("fullName") final String fullName) {
		LOGGER.debug("Got the name {}", fullName);
		return new ResponseEntity<NameResponse>(migrationService.migrateName(fullName),
				HttpStatus.OK);
	}
}
