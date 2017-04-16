package veil.hdp.hive.jdbc.metadata;


import org.apache.hive.service.cli.thrift.TColumnDesc;

public class ColumnDescriptor {

    private final String name;
    private final String normalizedName;
    private final String comment;
    private final ColumnTypeDescriptor columnTypeDescriptor;

    // should be 1 based to match ResultSet
    private final int position;


    public ColumnDescriptor(String name, ColumnTypeDescriptor columnTypeDescriptor, int position) {
        this.name = name;
        this.normalizedName = normalizeName(name);
        this.comment = null;
        this.columnTypeDescriptor = columnTypeDescriptor;
        this.position = position;
    }

    public ColumnDescriptor(TColumnDesc columnDesc) {
        name = columnDesc.getColumnName();
        normalizedName = normalizeName(columnDesc.getColumnName());
        comment = columnDesc.getComment();
        columnTypeDescriptor = new ColumnTypeDescriptor(columnDesc.getTypeDesc());
        position = columnDesc.getPosition();
    }

    public String getName() {
        return name;
    }

    public String getNormalizedName() {
        return normalizedName;
    }

    public String getComment() {
        return comment;
    }

    public ColumnTypeDescriptor getColumnType() {
        return columnTypeDescriptor;
    }

    public int getPosition() {
        return position;
    }

    private static String normalizeName(String name) {

        if (name.contains(".")) {
            name = name.substring(name.lastIndexOf('.') + 1);
        }

        return name.toLowerCase();
    }
}
