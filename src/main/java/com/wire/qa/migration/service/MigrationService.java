package com.wire.qa.migration.service;

import com.wire.qa.migration.io.NameResponse;

public interface MigrationService {

	NameResponse migrateName(final String fullName);

}
