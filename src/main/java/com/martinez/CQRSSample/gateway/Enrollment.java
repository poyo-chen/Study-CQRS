package com.martinez.CQRSSample.gateway;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Enrollment {

  @Id
  @GeneratedValue
  private long id;
  @OneToOne
  private Student student;
  @OneToOne
  private Course course;
  private Grade grade;

  public Enrollment(Student student, Course course, Grade grade) {
    this.student = student;
    this.course = course;
    this.grade = grade;
  }

  public void update(Course course, Grade grade) {
    this.course = course;
    this.grade = grade;
  }

  @Getter
  @RequiredArgsConstructor
  enum Grade {
    A(1), B(2), C(3), D(4), E(5), F(6);
    private final int value;
  }
}
