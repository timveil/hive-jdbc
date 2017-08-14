package veil.hdp.hive.jdbc.metadata;


import veil.hdp.hive.jdbc.Builder;
import veil.hdp.hive.jdbc.bindings.TPrimitiveTypeEntry;
import veil.hdp.hive.jdbc.bindings.TTypeDesc;
import veil.hdp.hive.jdbc.bindings.TTypeEntry;

import java.util.List;

public class ColumnTypeDescriptor {

    private final HiveType hiveType;

    private ColumnTypeDescriptor(HiveType hiveType) {
        this.hiveType = hiveType;
    }

    public static ColumnTypeDescriptorBuilder builder() {
        return new ColumnTypeDescriptorBuilder();
    }

    public HiveType getHiveType() {
        return hiveType;
    }

    @Override
    public String toString() {
        return "ColumnTypeDescriptor{" +
                "hiveType=" + hiveType +
                '}';
    }

    public static class ColumnTypeDescriptorBuilder implements Builder<ColumnTypeDescriptor> {

        private TTypeDesc typeDesc;
        private HiveType hiveType;

        private ColumnTypeDescriptorBuilder() {
        }

        ColumnTypeDescriptorBuilder thriftType(TTypeDesc typeDesc) {
            this.typeDesc = typeDesc;
            return this;
        }

        public ColumnTypeDescriptorBuilder hiveType(HiveType hiveType) {
            this.hiveType = hiveType;
            return this;
        }

        public ColumnTypeDescriptor build() {

            if (hiveType != null) {
                return new ColumnTypeDescriptor(hiveType);
            } else {
                List<TTypeEntry> typeEntries = typeDesc.getTypes();
                TPrimitiveTypeEntry primitiveTypeEntry = typeEntries.get(0).getPrimitiveEntry();

                return new ColumnTypeDescriptor(HiveType.valueOf(primitiveTypeEntry.getType()));
            }

        }
    }
}
