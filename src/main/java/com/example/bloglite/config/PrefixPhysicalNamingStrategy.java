package com.example.bloglite.config;

import org.hibernate.cfg.ImprovedNamingStrategy;

public class PrefixPhysicalNamingStrategy extends ImprovedNamingStrategy
{
    /**
     * TODO Make this an injectable application property
     */
    public static final String TABLE_NAME_PREFIX = "Bl";

    @Override
    public String classToTableName(String className) {
        String prefixlessClassName = className.replaceFirst(TABLE_NAME_PREFIX, "");
        return super.classToTableName(prefixlessClassName);
    }
}
