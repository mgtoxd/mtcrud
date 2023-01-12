// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mtcrud.proto

package pers.mtx.grpc.mtcrud;

/**
 * Protobuf type {@code mtCrud.Data}
 */
public final class Data extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:mtCrud.Data)
    DataOrBuilder {
private static final long serialVersionUID = 0L;
  // Use Data.newBuilder() to construct.
  private Data(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private Data() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new Data();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return pers.mtx.grpc.mtcrud.mtcrudProto.internal_static_mtCrud_Data_descriptor;
  }

  @SuppressWarnings({"rawtypes"})
  @java.lang.Override
  protected com.google.protobuf.MapField internalGetMapField(
      int number) {
    switch (number) {
      case 1:
        return internalGetDataMap();
      default:
        throw new RuntimeException(
            "Invalid map field number: " + number);
    }
  }
  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return pers.mtx.grpc.mtcrud.mtcrudProto.internal_static_mtCrud_Data_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            pers.mtx.grpc.mtcrud.Data.class, pers.mtx.grpc.mtcrud.Data.Builder.class);
  }

  public static final int DATAMAP_FIELD_NUMBER = 1;
  private static final class DataMapDefaultEntryHolder {
    static final com.google.protobuf.MapEntry<
        java.lang.String, java.lang.String> defaultEntry =
            com.google.protobuf.MapEntry
            .<java.lang.String, java.lang.String>newDefaultInstance(
                pers.mtx.grpc.mtcrud.mtcrudProto.internal_static_mtCrud_Data_DataMapEntry_descriptor, 
                com.google.protobuf.WireFormat.FieldType.STRING,
                "",
                com.google.protobuf.WireFormat.FieldType.STRING,
                "");
  }
  private com.google.protobuf.MapField<
      java.lang.String, java.lang.String> dataMap_;
  private com.google.protobuf.MapField<java.lang.String, java.lang.String>
  internalGetDataMap() {
    if (dataMap_ == null) {
      return com.google.protobuf.MapField.emptyMapField(
          DataMapDefaultEntryHolder.defaultEntry);
    }
    return dataMap_;
  }

  public int getDataMapCount() {
    return internalGetDataMap().getMap().size();
  }
  /**
   * <code>map&lt;string, string&gt; dataMap = 1;</code>
   */

  @java.lang.Override
  public boolean containsDataMap(
      java.lang.String key) {
    if (key == null) { throw new NullPointerException("map key"); }
    return internalGetDataMap().getMap().containsKey(key);
  }
  /**
   * Use {@link #getDataMapMap()} instead.
   */
  @java.lang.Override
  @java.lang.Deprecated
  public java.util.Map<java.lang.String, java.lang.String> getDataMap() {
    return getDataMapMap();
  }
  /**
   * <code>map&lt;string, string&gt; dataMap = 1;</code>
   */
  @java.lang.Override

  public java.util.Map<java.lang.String, java.lang.String> getDataMapMap() {
    return internalGetDataMap().getMap();
  }
  /**
   * <code>map&lt;string, string&gt; dataMap = 1;</code>
   */
  @java.lang.Override

  public java.lang.String getDataMapOrDefault(
      java.lang.String key,
      java.lang.String defaultValue) {
    if (key == null) { throw new NullPointerException("map key"); }
    java.util.Map<java.lang.String, java.lang.String> map =
        internalGetDataMap().getMap();
    return map.containsKey(key) ? map.get(key) : defaultValue;
  }
  /**
   * <code>map&lt;string, string&gt; dataMap = 1;</code>
   */
  @java.lang.Override

  public java.lang.String getDataMapOrThrow(
      java.lang.String key) {
    if (key == null) { throw new NullPointerException("map key"); }
    java.util.Map<java.lang.String, java.lang.String> map =
        internalGetDataMap().getMap();
    if (!map.containsKey(key)) {
      throw new java.lang.IllegalArgumentException();
    }
    return map.get(key);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    com.google.protobuf.GeneratedMessageV3
      .serializeStringMapTo(
        output,
        internalGetDataMap(),
        DataMapDefaultEntryHolder.defaultEntry,
        1);
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (java.util.Map.Entry<java.lang.String, java.lang.String> entry
         : internalGetDataMap().getMap().entrySet()) {
      com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
      dataMap__ = DataMapDefaultEntryHolder.defaultEntry.newBuilderForType()
          .setKey(entry.getKey())
          .setValue(entry.getValue())
          .build();
      size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(1, dataMap__);
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof pers.mtx.grpc.mtcrud.Data)) {
      return super.equals(obj);
    }
    pers.mtx.grpc.mtcrud.Data other = (pers.mtx.grpc.mtcrud.Data) obj;

    if (!internalGetDataMap().equals(
        other.internalGetDataMap())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (!internalGetDataMap().getMap().isEmpty()) {
      hash = (37 * hash) + DATAMAP_FIELD_NUMBER;
      hash = (53 * hash) + internalGetDataMap().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static pers.mtx.grpc.mtcrud.Data parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pers.mtx.grpc.mtcrud.Data parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pers.mtx.grpc.mtcrud.Data parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pers.mtx.grpc.mtcrud.Data parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pers.mtx.grpc.mtcrud.Data parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static pers.mtx.grpc.mtcrud.Data parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static pers.mtx.grpc.mtcrud.Data parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pers.mtx.grpc.mtcrud.Data parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static pers.mtx.grpc.mtcrud.Data parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static pers.mtx.grpc.mtcrud.Data parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static pers.mtx.grpc.mtcrud.Data parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static pers.mtx.grpc.mtcrud.Data parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(pers.mtx.grpc.mtcrud.Data prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code mtCrud.Data}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:mtCrud.Data)
      pers.mtx.grpc.mtcrud.DataOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return pers.mtx.grpc.mtcrud.mtcrudProto.internal_static_mtCrud_Data_descriptor;
    }

    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetDataMap();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @SuppressWarnings({"rawtypes"})
    protected com.google.protobuf.MapField internalGetMutableMapField(
        int number) {
      switch (number) {
        case 1:
          return internalGetMutableDataMap();
        default:
          throw new RuntimeException(
              "Invalid map field number: " + number);
      }
    }
    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return pers.mtx.grpc.mtcrud.mtcrudProto.internal_static_mtCrud_Data_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              pers.mtx.grpc.mtcrud.Data.class, pers.mtx.grpc.mtcrud.Data.Builder.class);
    }

    // Construct using pers.mtx.grpc.mtcrud.Data.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      internalGetMutableDataMap().clear();
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return pers.mtx.grpc.mtcrud.mtcrudProto.internal_static_mtCrud_Data_descriptor;
    }

    @java.lang.Override
    public pers.mtx.grpc.mtcrud.Data getDefaultInstanceForType() {
      return pers.mtx.grpc.mtcrud.Data.getDefaultInstance();
    }

    @java.lang.Override
    public pers.mtx.grpc.mtcrud.Data build() {
      pers.mtx.grpc.mtcrud.Data result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public pers.mtx.grpc.mtcrud.Data buildPartial() {
      pers.mtx.grpc.mtcrud.Data result = new pers.mtx.grpc.mtcrud.Data(this);
      int from_bitField0_ = bitField0_;
      result.dataMap_ = internalGetDataMap();
      result.dataMap_.makeImmutable();
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof pers.mtx.grpc.mtcrud.Data) {
        return mergeFrom((pers.mtx.grpc.mtcrud.Data)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(pers.mtx.grpc.mtcrud.Data other) {
      if (other == pers.mtx.grpc.mtcrud.Data.getDefaultInstance()) return this;
      internalGetMutableDataMap().mergeFrom(
          other.internalGetDataMap());
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              com.google.protobuf.MapEntry<java.lang.String, java.lang.String>
              dataMap__ = input.readMessage(
                  DataMapDefaultEntryHolder.defaultEntry.getParserForType(), extensionRegistry);
              internalGetMutableDataMap().getMutableMap().put(
                  dataMap__.getKey(), dataMap__.getValue());
              break;
            } // case 10
            default: {
              if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                done = true; // was an endgroup tag
              }
              break;
            } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.MapField<
        java.lang.String, java.lang.String> dataMap_;
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetDataMap() {
      if (dataMap_ == null) {
        return com.google.protobuf.MapField.emptyMapField(
            DataMapDefaultEntryHolder.defaultEntry);
      }
      return dataMap_;
    }
    private com.google.protobuf.MapField<java.lang.String, java.lang.String>
    internalGetMutableDataMap() {
      onChanged();;
      if (dataMap_ == null) {
        dataMap_ = com.google.protobuf.MapField.newMapField(
            DataMapDefaultEntryHolder.defaultEntry);
      }
      if (!dataMap_.isMutable()) {
        dataMap_ = dataMap_.copy();
      }
      return dataMap_;
    }

    public int getDataMapCount() {
      return internalGetDataMap().getMap().size();
    }
    /**
     * <code>map&lt;string, string&gt; dataMap = 1;</code>
     */

    @java.lang.Override
    public boolean containsDataMap(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      return internalGetDataMap().getMap().containsKey(key);
    }
    /**
     * Use {@link #getDataMapMap()} instead.
     */
    @java.lang.Override
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String> getDataMap() {
      return getDataMapMap();
    }
    /**
     * <code>map&lt;string, string&gt; dataMap = 1;</code>
     */
    @java.lang.Override

    public java.util.Map<java.lang.String, java.lang.String> getDataMapMap() {
      return internalGetDataMap().getMap();
    }
    /**
     * <code>map&lt;string, string&gt; dataMap = 1;</code>
     */
    @java.lang.Override

    public java.lang.String getDataMapOrDefault(
        java.lang.String key,
        java.lang.String defaultValue) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetDataMap().getMap();
      return map.containsKey(key) ? map.get(key) : defaultValue;
    }
    /**
     * <code>map&lt;string, string&gt; dataMap = 1;</code>
     */
    @java.lang.Override

    public java.lang.String getDataMapOrThrow(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      java.util.Map<java.lang.String, java.lang.String> map =
          internalGetDataMap().getMap();
      if (!map.containsKey(key)) {
        throw new java.lang.IllegalArgumentException();
      }
      return map.get(key);
    }

    public Builder clearDataMap() {
      internalGetMutableDataMap().getMutableMap()
          .clear();
      return this;
    }
    /**
     * <code>map&lt;string, string&gt; dataMap = 1;</code>
     */

    public Builder removeDataMap(
        java.lang.String key) {
      if (key == null) { throw new NullPointerException("map key"); }
      internalGetMutableDataMap().getMutableMap()
          .remove(key);
      return this;
    }
    /**
     * Use alternate mutation accessors instead.
     */
    @java.lang.Deprecated
    public java.util.Map<java.lang.String, java.lang.String>
    getMutableDataMap() {
      return internalGetMutableDataMap().getMutableMap();
    }
    /**
     * <code>map&lt;string, string&gt; dataMap = 1;</code>
     */
    public Builder putDataMap(
        java.lang.String key,
        java.lang.String value) {
      if (key == null) { throw new NullPointerException("map key"); }
      if (value == null) {
  throw new NullPointerException("map value");
}

      internalGetMutableDataMap().getMutableMap()
          .put(key, value);
      return this;
    }
    /**
     * <code>map&lt;string, string&gt; dataMap = 1;</code>
     */

    public Builder putAllDataMap(
        java.util.Map<java.lang.String, java.lang.String> values) {
      internalGetMutableDataMap().getMutableMap()
          .putAll(values);
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:mtCrud.Data)
  }

  // @@protoc_insertion_point(class_scope:mtCrud.Data)
  private static final pers.mtx.grpc.mtcrud.Data DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new pers.mtx.grpc.mtcrud.Data();
  }

  public static pers.mtx.grpc.mtcrud.Data getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<Data>
      PARSER = new com.google.protobuf.AbstractParser<Data>() {
    @java.lang.Override
    public Data parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      Builder builder = newBuilder();
      try {
        builder.mergeFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(builder.buildPartial());
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(e)
            .setUnfinishedMessage(builder.buildPartial());
      }
      return builder.buildPartial();
    }
  };

  public static com.google.protobuf.Parser<Data> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<Data> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public pers.mtx.grpc.mtcrud.Data getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
