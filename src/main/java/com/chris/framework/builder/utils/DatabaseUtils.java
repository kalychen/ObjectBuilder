package com.chris.framework.builder.utils;

import com.chris.framework.builder.model.Column;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ObjectBuilder
 * com.chris.framework.builder.utils
 * Created by Chris Chen
 * 2018/2/11
 * Explain:
 */
public class DatabaseUtils {
    /**
     * 获取一张数据表列集合
     *
     * @param conn
     * @param tableName
     * @return
     * @throws Exception
     */
    public static List<Column> getTableColumnList(Connection conn, String tableName) throws Exception {
        Statement statement = conn.createStatement();
        DatabaseMetaData databaseMetaData = conn.getMetaData();
        String sql = "SELECT * FROM " + tableName;
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        ResultSet rsFK = databaseMetaData.getImportedKeys(conn.getCatalog(), conn.getSchema(), tableName);

        //获取列属性
        List<Column> columnList = new ArrayList<>();
        Column column = null;
        int columnCount = rsmd.getColumnCount();
        for (int i = 1; i <= columnCount; i++) {
            column = new Column();

            column.setColumnId(i);
            column.setColumnName(rsmd.getColumnName(i));
            column.setColumnType(rsmd.getColumnTypeName(i));
            column.setColumnClassName(rsmd.getColumnClassName(i));
            column.setColumnDisplaySize(rsmd.getColumnDisplaySize(i));
            column.setIsNullable(rsmd.isNullable(i));
            column.setAutoIncrement(rsmd.isAutoIncrement(i));
            column.setSearchable(rsmd.isSearchable(i));
            column.setColumnName(rsmd.getColumnName(i));

            //外键约束
            rsFK.beforeFirst();
            while (rsFK.next()) {
                String fkColumnName = rsFK.getString("FKCOLUMN_NAME");
                if (fkColumnName.equals(column.getColumnName())) {
                    column.setPkTableName(rsFK.getString("PKTABLE_NAME"));
                    column.setPkColumnName(rsFK.getString("PKCOLUMN_NAME"));
                }
            }
            columnList.add(column);
        }
        return columnList;
    }
}
