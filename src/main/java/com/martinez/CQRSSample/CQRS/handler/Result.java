package com.martinez.CQRSSample.CQRS.handler;

public class Result {

  public static boolean isSuccess;

  String message;

  public Result(String message) {
    this.message = message;
  }

  public static Result fail(String message) {
    isSuccess = false;
    return new Result(message);
  }

  public static Result ok() {
    isSuccess = true;
    return new Result("");
  }

  public String error() {
    return message;
  }
}
