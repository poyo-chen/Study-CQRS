package com.martinez.CQRSSample.gateway.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {

  private long id;
  private String name;
  private String email;
  private String course1;
  private String course1Grade;
  private String course1DisenrollmentComment;
  private int course1Credits;
  private String course2;
  private String course2Grade;
  private String course2DisenrollmentComment;
  private int course2Credits;
}
