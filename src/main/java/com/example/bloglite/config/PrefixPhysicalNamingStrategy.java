package com.example.bloglite.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class PrefixPhysicalNamingStrategy extends PhysicalNamingStrategyStandardImpl {

    /**
     * TODO Make this an injectable application property
     */
    public static final String TABLE_NAME_PREFIX = "Bl";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
        String originalClassName = name.getText();
        String prefixlessClassName = originalClassName.replaceFirst(TABLE_NAME_PREFIX, "");
        Identifier newIdentifier = new Identifier(prefixlessClassName, name.isQuoted());
        return super.toPhysicalTableName(newIdentifier, context);
    }
}
