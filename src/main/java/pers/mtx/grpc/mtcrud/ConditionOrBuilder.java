// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mtcrud.proto

package pers.mtx.grpc.mtcrud;

public interface ConditionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:mtCrud.Condition)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>map&lt;string, string&gt; eq = 1;</code>
   */
  int getEqCount();
  /**
   * <code>map&lt;string, string&gt; eq = 1;</code>
   */
  boolean containsEq(
      String key);
  /**
   * Use {@link #getEqMap()} instead.
   */
  @Deprecated
  java.util.Map<String, String>
  getEq();
  /**
   * <code>map&lt;string, string&gt; eq = 1;</code>
   */
  java.util.Map<String, String>
  getEqMap();
  /**
   * <code>map&lt;string, string&gt; eq = 1;</code>
   */

  /* nullable */
String getEqOrDefault(
      String key,
      /* nullable */
String defaultValue);
  /**
   * <code>map&lt;string, string&gt; eq = 1;</code>
   */

  String getEqOrThrow(
      String key);

  /**
   * <code>map&lt;string, string&gt; gt = 2;</code>
   */
  int getGtCount();
  /**
   * <code>map&lt;string, string&gt; gt = 2;</code>
   */
  boolean containsGt(
      String key);
  /**
   * Use {@link #getGtMap()} instead.
   */
  @Deprecated
  java.util.Map<String, String>
  getGt();
  /**
   * <code>map&lt;string, string&gt; gt = 2;</code>
   */
  java.util.Map<String, String>
  getGtMap();
  /**
   * <code>map&lt;string, string&gt; gt = 2;</code>
   */

  /* nullable */
String getGtOrDefault(
      String key,
      /* nullable */
String defaultValue);
  /**
   * <code>map&lt;string, string&gt; gt = 2;</code>
   */

  String getGtOrThrow(
      String key);

  /**
   * <code>map&lt;string, string&gt; lt = 3;</code>
   */
  int getLtCount();
  /**
   * <code>map&lt;string, string&gt; lt = 3;</code>
   */
  boolean containsLt(
      String key);
  /**
   * Use {@link #getLtMap()} instead.
   */
  @Deprecated
  java.util.Map<String, String>
  getLt();
  /**
   * <code>map&lt;string, string&gt; lt = 3;</code>
   */
  java.util.Map<String, String>
  getLtMap();
  /**
   * <code>map&lt;string, string&gt; lt = 3;</code>
   */

  /* nullable */
String getLtOrDefault(
      String key,
      /* nullable */
String defaultValue);
  /**
   * <code>map&lt;string, string&gt; lt = 3;</code>
   */

  String getLtOrThrow(
      String key);

  /**
   * <code>map&lt;string, string&gt; like = 4;</code>
   */
  int getLikeCount();
  /**
   * <code>map&lt;string, string&gt; like = 4;</code>
   */
  boolean containsLike(
      String key);
  /**
   * Use {@link #getLikeMap()} instead.
   */
  @Deprecated
  java.util.Map<String, String>
  getLike();
  /**
   * <code>map&lt;string, string&gt; like = 4;</code>
   */
  java.util.Map<String, String>
  getLikeMap();
  /**
   * <code>map&lt;string, string&gt; like = 4;</code>
   */

  /* nullable */
String getLikeOrDefault(
      String key,
      /* nullable */
String defaultValue);
  /**
   * <code>map&lt;string, string&gt; like = 4;</code>
   */

  String getLikeOrThrow(
      String key);
}
