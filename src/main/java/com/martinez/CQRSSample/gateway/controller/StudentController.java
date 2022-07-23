package com.martinez.CQRSSample.gateway.controller;

import com.martinez.CQRSSample.gateway.dto.StudentDto;
import com.martinez.CQRSSample.gateway.entity.Course;
import com.martinez.CQRSSample.gateway.entity.Enrollment.Grade;
import com.martinez.CQRSSample.gateway.entity.Student;
import com.martinez.CQRSSample.gateway.repository.CourseRepository;
import com.martinez.CQRSSample.gateway.repository.StudentRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")
@Slf4j
public record StudentController(StudentRepository studentRepository,
                                CourseRepository courseRepository) {

  @GetMapping()
  public List<StudentDto> GetList(String enrolled, int number) {
    return convertToDtos(
        studentRepository.getList(enrolled, number));
  }

  private List<StudentDto> convertToDtos(List<Student> students) {
    //TODO
    return List.of();
  }

  @PostMapping()
  public void create(@RequestBody StudentDto studentDto) throws Exception {
    Student student = new Student(studentDto.getName(), studentDto.getEmail());
    if (studentDto.getCourse1() != null &&
        studentDto.getCourse1Grade() != null) {
      Course course = courseRepository.getByName(studentDto.getCourse1());
      student.enroll(course, Grade.valueOf(studentDto.getCourse1Grade()));
    }
    if (studentDto.getCourse2() != null &&
        studentDto.getCourse2Grade() != null) {
      Course course = courseRepository.getByName(studentDto.getCourse2());
      student.enroll(course, Grade.valueOf(studentDto.getCourse2Grade()));
    }
    studentRepository.save(student);
  }

  @DeleteMapping()
  public void delete(long id) {
    Student student = studentRepository.getById(id);
    if (student == null) {
      log.error("No student found for Id {id}");
    }
    studentRepository.delete(student);
  }


}
