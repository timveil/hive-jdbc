package veil.hdp.hive.jdbc.core.data;

import veil.hdp.hive.jdbc.core.metadata.ColumnDescriptor;

import java.util.BitSet;
import java.util.List;

public class ByteColumnData extends ColumnData<Byte> {
    ByteColumnData(ColumnDescriptor descriptor, List<Byte> values, BitSet nulls) {
        super(descriptor, values, nulls);
    }
}