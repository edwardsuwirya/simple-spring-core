package com.enigmacamp.util;

public class SkipMigration implements IDbMigration {

    @Override
    public DbMigrationResult migrate() {
        return DbMigrationResult.MIGRATION_SKIP;
    }
}
