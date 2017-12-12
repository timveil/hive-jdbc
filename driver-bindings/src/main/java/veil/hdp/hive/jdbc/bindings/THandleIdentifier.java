/**
 * Autogenerated by Thrift Compiler (0.11.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package veil.hdp.hive.jdbc.bindings;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.11.0)", date = "2017-12-12")
public class THandleIdentifier implements org.apache.thrift.TBase<THandleIdentifier, THandleIdentifier._Fields>, java.io.Serializable, Cloneable, Comparable<THandleIdentifier> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("THandleIdentifier");

  private static final org.apache.thrift.protocol.TField GUID_FIELD_DESC = new org.apache.thrift.protocol.TField("guid", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField SECRET_FIELD_DESC = new org.apache.thrift.protocol.TField("secret", org.apache.thrift.protocol.TType.STRING, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new THandleIdentifierStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new THandleIdentifierTupleSchemeFactory();

  private java.nio.ByteBuffer guid; // required
  private java.nio.ByteBuffer secret; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    GUID((short)1, "guid"),
    SECRET((short)2, "secret");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // GUID
          return GUID;
        case 2: // SECRET
          return SECRET;
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
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.GUID, new org.apache.thrift.meta_data.FieldMetaData("guid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    tmpMap.put(_Fields.SECRET, new org.apache.thrift.meta_data.FieldMetaData("secret", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING        , true)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(THandleIdentifier.class, metaDataMap);
  }

  public THandleIdentifier() {
  }

  public THandleIdentifier(
    java.nio.ByteBuffer guid,
    java.nio.ByteBuffer secret)
  {
    this();
    this.guid = org.apache.thrift.TBaseHelper.copyBinary(guid);
    this.secret = org.apache.thrift.TBaseHelper.copyBinary(secret);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public THandleIdentifier(THandleIdentifier other) {
    if (other.isSetGuid()) {
      this.guid = org.apache.thrift.TBaseHelper.copyBinary(other.guid);
    }
    if (other.isSetSecret()) {
      this.secret = org.apache.thrift.TBaseHelper.copyBinary(other.secret);
    }
  }

  public THandleIdentifier deepCopy() {
    return new THandleIdentifier(this);
  }

  @Override
  public void clear() {
    this.guid = null;
    this.secret = null;
  }

  public byte[] getGuid() {
    setGuid(org.apache.thrift.TBaseHelper.rightSize(guid));
    return guid == null ? null : guid.array();
  }

  public java.nio.ByteBuffer bufferForGuid() {
    return org.apache.thrift.TBaseHelper.copyBinary(guid);
  }

  public void setGuid(byte[] guid) {
    this.guid = guid == null ? (java.nio.ByteBuffer)null : java.nio.ByteBuffer.wrap(guid.clone());
  }

  public void setGuid(java.nio.ByteBuffer guid) {
    this.guid = org.apache.thrift.TBaseHelper.copyBinary(guid);
  }

  public void unsetGuid() {
    this.guid = null;
  }

  /** Returns true if field guid is set (has been assigned a value) and false otherwise */
  public boolean isSetGuid() {
    return this.guid != null;
  }

  public void setGuidIsSet(boolean value) {
    if (!value) {
      this.guid = null;
    }
  }

  public byte[] getSecret() {
    setSecret(org.apache.thrift.TBaseHelper.rightSize(secret));
    return secret == null ? null : secret.array();
  }

  public java.nio.ByteBuffer bufferForSecret() {
    return org.apache.thrift.TBaseHelper.copyBinary(secret);
  }

  public void setSecret(byte[] secret) {
    this.secret = secret == null ? (java.nio.ByteBuffer)null : java.nio.ByteBuffer.wrap(secret.clone());
  }

  public void setSecret(java.nio.ByteBuffer secret) {
    this.secret = org.apache.thrift.TBaseHelper.copyBinary(secret);
  }

  public void unsetSecret() {
    this.secret = null;
  }

  /** Returns true if field secret is set (has been assigned a value) and false otherwise */
  public boolean isSetSecret() {
    return this.secret != null;
  }

  public void setSecretIsSet(boolean value) {
    if (!value) {
      this.secret = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case GUID:
      if (value == null) {
        unsetGuid();
      } else {
        if (value instanceof byte[]) {
          setGuid((byte[])value);
        } else {
          setGuid((java.nio.ByteBuffer)value);
        }
      }
      break;

    case SECRET:
      if (value == null) {
        unsetSecret();
      } else {
        if (value instanceof byte[]) {
          setSecret((byte[])value);
        } else {
          setSecret((java.nio.ByteBuffer)value);
        }
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case GUID:
      return getGuid();

    case SECRET:
      return getSecret();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case GUID:
      return isSetGuid();
    case SECRET:
      return isSetSecret();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof THandleIdentifier)
      return this.equals((THandleIdentifier)that);
    return false;
  }

  public boolean equals(THandleIdentifier that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_guid = true && this.isSetGuid();
    boolean that_present_guid = true && that.isSetGuid();
    if (this_present_guid || that_present_guid) {
      if (!(this_present_guid && that_present_guid))
        return false;
      if (!this.guid.equals(that.guid))
        return false;
    }

    boolean this_present_secret = true && this.isSetSecret();
    boolean that_present_secret = true && that.isSetSecret();
    if (this_present_secret || that_present_secret) {
      if (!(this_present_secret && that_present_secret))
        return false;
      if (!this.secret.equals(that.secret))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetGuid()) ? 131071 : 524287);
    if (isSetGuid())
      hashCode = hashCode * 8191 + guid.hashCode();

    hashCode = hashCode * 8191 + ((isSetSecret()) ? 131071 : 524287);
    if (isSetSecret())
      hashCode = hashCode * 8191 + secret.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(THandleIdentifier other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetGuid()).compareTo(other.isSetGuid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGuid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.guid, other.guid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetSecret()).compareTo(other.isSetSecret());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetSecret()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.secret, other.secret);
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
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("THandleIdentifier(");
    boolean first = true;

    sb.append("guid:");
    if (this.guid == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.guid, sb);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("secret:");
    if (this.secret == null) {
      sb.append("null");
    } else {
      org.apache.thrift.TBaseHelper.toString(this.secret, sb);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (!isSetGuid()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'guid' is unset! Struct:" + toString());
    }

    if (!isSetSecret()) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'secret' is unset! Struct:" + toString());
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

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class THandleIdentifierStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public THandleIdentifierStandardScheme getScheme() {
      return new THandleIdentifierStandardScheme();
    }
  }

  private static class THandleIdentifierStandardScheme extends org.apache.thrift.scheme.StandardScheme<THandleIdentifier> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, THandleIdentifier struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // GUID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.guid = iprot.readBinary();
              struct.setGuidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // SECRET
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.secret = iprot.readBinary();
              struct.setSecretIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, THandleIdentifier struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.guid != null) {
        oprot.writeFieldBegin(GUID_FIELD_DESC);
        oprot.writeBinary(struct.guid);
        oprot.writeFieldEnd();
      }
      if (struct.secret != null) {
        oprot.writeFieldBegin(SECRET_FIELD_DESC);
        oprot.writeBinary(struct.secret);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class THandleIdentifierTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public THandleIdentifierTupleScheme getScheme() {
      return new THandleIdentifierTupleScheme();
    }
  }

  private static class THandleIdentifierTupleScheme extends org.apache.thrift.scheme.TupleScheme<THandleIdentifier> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, THandleIdentifier struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeBinary(struct.guid);
      oprot.writeBinary(struct.secret);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, THandleIdentifier struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.guid = iprot.readBinary();
      struct.setGuidIsSet(true);
      struct.secret = iprot.readBinary();
      struct.setSecretIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

