package com.martinez.CQRSSample.gateway;

import com.martinez.CQRSSample.gateway.Enrollment.Grade;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Student {

  @Id
  @GeneratedValue
  private long id;
  private String name;
  private String email;
  @OneToMany(targetEntity = Enrollment.class)
  private List<Enrollment> enrollments = new ArrayList<>();
  @OneToMany(targetEntity = Disenrollment.class)
  private List<Disenrollment> disenrollments = new ArrayList<>();

  private Enrollment getEnrollment(int index) {
    if (enrollments.size() > index) {
      return enrollments.get(index);
    }
    return null;
  }

  public void removeEnrollment(Enrollment enrollment) {
    enrollments.remove(enrollment);
  }

  public void addDisenrollmentComment(Enrollment enrollment, String comment) {
    Disenrollment disenrollment = new Disenrollment(enrollment.getStudent(), enrollment.getCourse(),
        comment);
    disenrollments.add(disenrollment);
  }

  public void enroll(Course course, Grade grade) throws Exception {
    if (enrollments.size() > 2) {
      throw new Exception("Cannot have more than 2 enrollments");
    }
    Enrollment enrollment = new Enrollment(this, course, grade);
    enrollments.add(enrollment);
  }
}
