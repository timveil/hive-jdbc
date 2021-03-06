/*
 *    Copyright 2018 Timothy J Veil
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package veil.hdp.hive.jdbc.test;

import com.google.common.base.Joiner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

final class Printer {
    private static final Logger log = LogManager.getLogger(Printer.class);
    private static final String SEPARATOR = ",";

    private Printer() {
    }


    private static void printResultSetMetaData(ResultSetMetaData rsmd) {

        try {
            int columnCount = rsmd.getColumnCount();

            for (int i = 0; i < columnCount; i++) {

                String metadata = "column class name [" + rsmd.getColumnClassName(i + 1) + "], " +
                        "column display size [" + rsmd.getColumnDisplaySize(i + 1) + "], " +
                        "column label [" + rsmd.getColumnLabel(i + 1) + "], " +
                        "column name [" + rsmd.getColumnName(i + 1) + "], " +
                        "table name [" + rsmd.getTableName(i + 1) + "], " +
                        "column type [" + rsmd.getColumnType(i + 1) + "], " +
                        "column type name [" + rsmd.getColumnTypeName(i + 1) + "], " +
                        "precision [" + rsmd.getPrecision(i + 1) + "], " +
                        "getScale [" + rsmd.getScale(i + 1) + "], " +
                        "isAutoIncrement [" + rsmd.isAutoIncrement(i + 1) + "], " +
                        "isCaseSensitive [" + rsmd.isCaseSensitive(i + 1) + "], " +
                        "isCurrency [" + rsmd.isCurrency(i + 1) + "], " +
                        "isNullable [" + rsmd.isNullable(i + 1) + "], " +
                        "isReadOnly [" + rsmd.isReadOnly(i + 1) + "], ";

                log.debug(metadata);
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

    }

    static void printResultSet(ResultSet rs, boolean printResults, boolean printMetaData) {


        try {
            ResultSetMetaData metaData = rs.getMetaData();

            if (printMetaData) {
                printResultSetMetaData(metaData);
            }

            int columnCount = metaData.getColumnCount();
            int counter = 0;

            while (rs.next()) {

                List<String> row = new ArrayList<>(columnCount);

                for (int i = 0; i < columnCount; i++) {

                    String columnName = metaData.getColumnName(i + 1);
                    String columnValue = rs.getString(i + 1);

                    row.add(columnName + " [" + columnValue + ']');

                }

                if (printResults) {
                    log.debug("row {} - data [{}]", counter, Joiner.on(SEPARATOR).join(row));
                }

                counter++;
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

    }
}
