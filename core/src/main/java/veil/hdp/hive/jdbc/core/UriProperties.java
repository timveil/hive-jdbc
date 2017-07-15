package veil.hdp.hive.jdbc.core;

import java.net.URI;
import java.util.Properties;

@FunctionalInterface
public interface UriProperties {
    void load(URI uri, Properties properties);
}
