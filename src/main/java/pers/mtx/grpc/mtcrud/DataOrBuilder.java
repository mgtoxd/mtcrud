// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mtcrud.proto

package pers.mtx.grpc.mtcrud;

public interface DataOrBuilder extends
    // @@protoc_insertion_point(interface_extends:mtCrud.Data)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>map&lt;string, string&gt; dataMap = 1;</code>
   */
  int getDataMapCount();
  /**
   * <code>map&lt;string, string&gt; dataMap = 1;</code>
   */
  boolean containsDataMap(
      String key);
  /**
   * Use {@link #getDataMapMap()} instead.
   */
  @Deprecated
  java.util.Map<String, String>
  getDataMap();
  /**
   * <code>map&lt;string, string&gt; dataMap = 1;</code>
   */
  java.util.Map<String, String>
  getDataMapMap();
  /**
   * <code>map&lt;string, string&gt; dataMap = 1;</code>
   */

  /* nullable */
String getDataMapOrDefault(
      String key,
      /* nullable */
String defaultValue);
  /**
   * <code>map&lt;string, string&gt; dataMap = 1;</code>
   */

  String getDataMapOrThrow(
      String key);
}
