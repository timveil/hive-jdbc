package veil.hdp.hive.jdbc;

import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HivePreparedStatement extends AbstractPreparedStatement {


    private static final char PLACEHOLDER = '?' ;
    private static final char SINGLE_QUOTE = '\'' ;
    private static final char DOUBLE_QUOTE = '"' ;
    private static final char BACKSLASH = '\\' ;
    private static final String NULL_STRING = "NULL";

    private static final char[] ESCAPE_CHARS = new char[]{SINGLE_QUOTE, BACKSLASH, DOUBLE_QUOTE};

    static {
        Arrays.sort(ESCAPE_CHARS);
    }

    private final String sql;

    private final Map<Integer, String> parameterValues;


    private HivePreparedStatement(HiveConnection connection, String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
        super(connection, resultSetType, resultSetConcurrency, resultSetHoldability);
        this.sql = sql;
        parameterValues = new HashMap<>();
    }

    @Override
    public void clearParameters() throws SQLException {
        parameterValues.clear();
    }

    @Override
    public ResultSet executeQuery() throws SQLException {
        return super.executeQuery(updateSql(sql));
    }

    @Override
    public int executeUpdate() throws SQLException {
        return super.executeUpdate(updateSql(sql));
    }

    @Override
    public boolean execute() throws SQLException {
        return super.execute(updateSql(sql));
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        return super.getMetaData();
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        parameterValues.put(parameterIndex, Byte.toString(x));
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        parameterValues.put(parameterIndex, Short.toString(x));
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        parameterValues.put(parameterIndex, Integer.toString(x));
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        parameterValues.put(parameterIndex, Long.toString(x));
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        parameterValues.put(parameterIndex, Float.toString(x));
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        parameterValues.put(parameterIndex, Double.toString(x));
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        String newValue = StringUtils.replace(x, Character.toString(SINGLE_QUOTE), String.valueOf(BACKSLASH) + SINGLE_QUOTE);
        parameterValues.put(parameterIndex, StringUtils.wrap(newValue, SINGLE_QUOTE));
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        parameterValues.put(parameterIndex, StringUtils.wrap(x.toString(), SINGLE_QUOTE));
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        parameterValues.put(parameterIndex, StringUtils.wrap(x.toString(), SINGLE_QUOTE));
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
       setNull(parameterIndex, sqlType, null);
    }

    @Override
    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        parameterValues.put(parameterIndex, NULL_STRING);
    }



    private String updateSql(String originalSql) {

        if (!originalSql.contains(String.valueOf(PLACEHOLDER))) {
            return originalSql;
        }

        StringBuilder builder = new StringBuilder(originalSql.length());

        int escapeCount = 0;
        int parameterCount = 1;

        for (int i = 0; i < originalSql.length(); i++) {
            char currentChar = sql.charAt(i);

            if (Arrays.binarySearch(ESCAPE_CHARS, currentChar) >= 0) {
                builder.append(currentChar);
                escapeCount++;
            } else if (currentChar == PLACEHOLDER && escapeCount % 2 == 0 && parameterValues.containsKey(parameterCount)) {
                builder.append(parameterValues.get(parameterCount));
                parameterCount++;
            } else {
                builder.append(currentChar);
            }
        }

        return builder.toString();

    }


    public static class Builder {

        private HiveConnection connection;
        private String sql;
        private int resultSetType;
        private int resultSetConcurrency;
        private int resultSetHoldability;


        public HivePreparedStatement.Builder connection(HiveConnection connection) {
            this.connection = connection;
            return this;
        }

        public HivePreparedStatement.Builder sql(String sql) {
            this.sql = sql;
            return this;
        }

        public HivePreparedStatement.Builder type(int resultSetType) {
            this.resultSetType = resultSetType;
            return this;
        }

        public HivePreparedStatement.Builder concurrency(int resultSetConcurrency) {
            this.resultSetConcurrency = resultSetConcurrency;
            return this;
        }

        public HivePreparedStatement.Builder holdability(int resultSetHoldability) {
            this.resultSetHoldability = resultSetHoldability;
            return this;
        }


        public HivePreparedStatement build() {
            return new HivePreparedStatement(connection, StringUtils.trim(sql), resultSetType, resultSetConcurrency, resultSetHoldability);
        }
    }
}
