package com.martinez.CQRSSample.gateway;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Disenrollment {

  @Id
  @GeneratedValue
  private long id;
  @OneToOne
  private Student student;
  @OneToOne
  private Course course;
  private Date dateTime;
  private String comment;

  public Disenrollment(Student student, Course course, String comment) {
    this.student = student;
    this.course = course;
    this.comment = comment;
    this.dateTime = new Date();
  }
}
