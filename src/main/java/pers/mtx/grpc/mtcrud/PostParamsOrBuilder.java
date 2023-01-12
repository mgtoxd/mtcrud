// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mtcrud.proto

package pers.mtx.grpc.mtcrud;

public interface PostParamsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:mtCrud.PostParams)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string id = 1;</code>
   * @return The id.
   */
  String getId();
  /**
   * <code>string id = 1;</code>
   * @return The bytes for id.
   */
  com.google.protobuf.ByteString
      getIdBytes();

  /**
   * <code>string ct = 2;</code>
   * @return The ct.
   */
  String getCt();
  /**
   * <code>string ct = 2;</code>
   * @return The bytes for ct.
   */
  com.google.protobuf.ByteString
      getCtBytes();

  /**
   * <code>string ut = 3;</code>
   * @return The ut.
   */
  String getUt();
  /**
   * <code>string ut = 3;</code>
   * @return The bytes for ut.
   */
  com.google.protobuf.ByteString
      getUtBytes();

  /**
   * <code>string dbName = 4;</code>
   * @return The dbName.
   */
  String getDbName();
  /**
   * <code>string dbName = 4;</code>
   * @return The bytes for dbName.
   */
  com.google.protobuf.ByteString
      getDbNameBytes();

  /**
   * <code>string tbName = 5;</code>
   * @return The tbName.
   */
  String getTbName();
  /**
   * <code>string tbName = 5;</code>
   * @return The bytes for tbName.
   */
  com.google.protobuf.ByteString
      getTbNameBytes();

  /**
   * <code>map&lt;string, string&gt; valueMap = 6;</code>
   */
  int getValueMapCount();
  /**
   * <code>map&lt;string, string&gt; valueMap = 6;</code>
   */
  boolean containsValueMap(
      String key);
  /**
   * Use {@link #getValueMapMap()} instead.
   */
  @Deprecated
  java.util.Map<String, String>
  getValueMap();
  /**
   * <code>map&lt;string, string&gt; valueMap = 6;</code>
   */
  java.util.Map<String, String>
  getValueMapMap();
  /**
   * <code>map&lt;string, string&gt; valueMap = 6;</code>
   */

  /* nullable */
String getValueMapOrDefault(
      String key,
      /* nullable */
String defaultValue);
  /**
   * <code>map&lt;string, string&gt; valueMap = 6;</code>
   */

  String getValueMapOrThrow(
      String key);
}