package veil.hdp.hive.jdbc.utils;

import org.slf4j.helpers.MessageFormatter;

public class Utils {

    public static String format(String format, Object... params) {
        return MessageFormatter.arrayFormat(format, params).getMessage();
    }
}