package com.martinez.CQRSSample.gateway;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Course {

  @Id
  @GeneratedValue
  private long id;
  private String name;
  private int credits;
}
