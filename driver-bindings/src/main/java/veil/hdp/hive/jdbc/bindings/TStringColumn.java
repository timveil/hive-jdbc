/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package veil.hdp.hive.jdbc.bindings;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2017-08-10")
public class TStringColumn implements org.apache.thrift.TBase<TStringColumn, TStringColumn._Fields>, java.io.Serializable, Cloneable, Comparable<TStringColumn> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("TStringColumn");

  private static final org.apache.thrift.protocol.TField VALUES_FIELD_DESC = new org.apache.thrift.protocol.TField("values", org.apache.thrift.protocol.TType.LIST, (short)1);
  private static final org.apache.thrift.protocol.TField NULLS_FIELD_DESC = new org.apache.thrift.protocol.TField("nulls", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new TStringColumnStandardSchemeFactory());
    schemes.put(TupleScheme.class, new TStringColumnTupleSchemeFactory());
  }

  private List<String> values; // required
  private ByteBuffer nulls; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    VALUES((short)1, "values"),
    NULLS((short)2, "nulls");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // VALUES
          return VALUES;
        case 2: // NULLS
          return NULLS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.VALUES, new org.apache.thrift.meta_data.FieldMetaData("values", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.NULLS, new org.apache.thrift.meta_data.FieldMetaData("nulls", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(TStringColumn.class, metaDataMap);
  }

  public TStringColumn() {
  }

  public TStringColumn(
    List<String> values,
    ByteBuffer nulls)
  {
    this();
    this.values = values;
    this.nulls = org.apache.thrift.TBaseHelper.copyBinary(nulls);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public TStringColumn(TStringColumn other) {
    if (other.isSetValues()) {
      List<String> __this__values = new ArrayList<String>(other.values);
      this.values = __this__values;
    }
    if (other.isSetNulls()) {
      this.nulls = org.apache.thrift.TBaseHelper.copyBinary(other.nulls);
    }
  }

  public TStringColumn deepCopy() {
    return new TStringColumn(this);
  }

  @Override
  public void clear() {
    this.values = null;
    this.nulls = null;
  }

  public int getValuesSize() {
    return (this.values == null) ? 0 : this.values.size();
  }

  public java.util.Iterator<String> getValuesIterator() {
    return (this.values == null) ? null : this.values.iterator();
  }

  public void addToValues(String elem) {
    if (this.values == null) {
      this.values = new ArrayList<String>();
    }
    this.values.add(elem);
  }

  public List<String> getValues() {
    return this.values;
  }

  public void setValues(List<String> values) {
    this.values = values;
  }

  public void unsetValues() {
    this.values = null;
  }

  /** Returns true if field values is set (has been assigned a value) and false otherwise */
  public boolean isSetValues() {
    return this.values != null;
  }

  public void setValuesIsSet(boolean value) {
    if (!value) {
      this.values = null;
    }
  }

  public byte[] getNulls() {
    setNulls(org.apache.thrift.TBaseHelper.rightSize(nulls));
    return nulls == null ? null : nulls.array();
  }

  public ByteBuffer bufferForNulls() {
    return org.apache.thrift.TBaseHelper.copyBinary(nulls);
  }

  public void setNulls(byte[] nulls) {
    this.nulls = nulls == null ? (ByteBuffer)null : ByteBuffer.wrap(Arrays.copyOf(nulls, nulls.length));
  }

  public void setNulls(ByteBuffer nulls) {
    this.nulls = org.apache.thrift.TBaseHelper.copyBinary(nulls);
  }

  public void unsetNulls() {
    this.nulls = null;
  }

  /** Returns true if field nulls is set (has been assigned a value) and false otherwise */
  public boolean isSetNulls() {
    return this.nulls != null;
  }

  public void setNullsIsSet(boolean value) {
    if (!value) {
      this.nulls = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case VALUES:
      if (value == null) {
        unsetValues();
      } else {
        setValues((List<String>)value);
      }
      break;

    case NULLS:
      if (value == null) {
        unsetNulls();
      } else {
        setNulls((ByteBuffer)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case VALUES:
      return getValues();

    case NULLS:
      return getNulls();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case VALUES:
      return isSetValues();
    case NULLS:
      return isSetNulls();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof TStringColumn)
      return this.equals((TStringColumn)that);
    return false;
  }

  public boolean equals(TStringColumn that) {
    if (that == null)
      return false;

    boolean this_present_values = true && this.isSetValues();
    boolean that_present_values = true && that.isSetValues();
    if (this_present_values || that_present_values) {
      if (!(this_present_values && that_present_values))
        return false;
      if (!this.values.equals(that.values))
        return false;
    }

    boolean this_present_nulls = true && this.isSetNulls();
    boolean that_present_nulls = true && that.isSetNulls();
    if (this_present_nulls || that_present_nulls) {
      if (!(this_present_nulls && that_present_nulls))
        return false;
      if (!this.nulls.equals(that.nulls))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_values = true && (isSetValues());
    list.add(present_values);
    if (present_values)
      list.add(values);

    boolean present_nulls = true && (isSetNulls());
    list.add(present_nulls);
    if (present_nulls)
      list.add(nulls);

    return list.hashCode();
  }

  @Override
  public int compareTo(TStringColumn other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetValues()).compareTo(other.isSetValues());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetValues()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.values, other.values);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetNulls()).compareTo(other.isSetNulls());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetNulls()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.nulls, other.nulls);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("TStringColumn(");
    boolean first = true;

    sb.append("values:");
    if (this.values == null) {
      sb.append("null");
    } else {
      sb.append(this.values);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("nulls:");
    if (this.nulls == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.nulls, sb);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetValues()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'values' is unset! Struct:" + toString());
    }

    if (!isSetNulls()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'nulls' is unset! Struct:" + toString());
    }

    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class TStringColumnStandardSchemeFactory implements SchemeFactory {
    public TStringColumnStandardScheme getScheme() {
      return new TStringColumnStandardScheme();
    }
  }

  private static class TStringColumnStandardScheme extends StandardScheme<TStringColumn> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, TStringColumn struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // VALUES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list102 = iprot.readListBegin();
                struct.values = new ArrayList<String>(_list102.size);
                String _elem103;
                for (int _i104 = 0; _i104 < _list102.size; ++_i104)
                {
                  _elem103 = iprot.readString();
                  struct.values.add(_elem103);
                }
                iprot.readListEnd();
              }
              struct.setValuesIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // NULLS
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.nulls = iprot.readBinary();
              struct.setNullsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, TStringColumn struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.values != null) {
        oprot.writeFieldBegin(VALUES_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, struct.values.size()));
          for (String _iter105 : struct.values)
          {
            oprot.writeString(_iter105);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.nulls != null) {
        oprot.writeFieldBegin(NULLS_FIELD_DESC);
        oprot.writeBinary(struct.nulls);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class TStringColumnTupleSchemeFactory implements SchemeFactory {
    public TStringColumnTupleScheme getScheme() {
      return new TStringColumnTupleScheme();
    }
  }

  private static class TStringColumnTupleScheme extends TupleScheme<TStringColumn> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, TStringColumn struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      {
        oprot.writeI32(struct.values.size());
        for (String _iter106 : struct.values)
        {
          oprot.writeString(_iter106);
        }
      }
      oprot.writeBinary(struct.nulls);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, TStringColumn struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      {
        org.apache.thrift.protocol.TList _list107 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRING, iprot.readI32());
        struct.values = new ArrayList<String>(_list107.size);
        String _elem108;
        for (int _i109 = 0; _i109 < _list107.size; ++_i109)
        {
          _elem108 = iprot.readString();
          struct.values.add(_elem108);
        }
      }
      struct.setValuesIsSet(true);
      struct.nulls = iprot.readBinary();
      struct.setNullsIsSet(true);
    }
  }

}

