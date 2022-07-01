package com.example.bloglite.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

public class PrefixPhysicalNamingStrategy extends SpringPhysicalNamingStrategy
{
    private static final String TABLE_NAME_PREFIX = "Bl";

    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context)
    {
        final String prefixedClassName = name.getText();
        final String prefixlessClassName = prefixedClassName.replaceFirst(TABLE_NAME_PREFIX, "");
        return super.toPhysicalTableName(new Identifier(prefixlessClassName, true), context);
    }
}
