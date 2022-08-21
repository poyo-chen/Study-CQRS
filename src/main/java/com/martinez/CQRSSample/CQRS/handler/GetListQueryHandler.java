package com.martinez.CQRSSample.CQRS.handler;

import com.martinez.CQRSSample.CQRS.command.GetListQuery;
import com.martinez.CQRSSample.CQRS.dto.StudentDto;
import com.martinez.CQRSSample.CQRS.repository.StudentRepository;
import java.util.List;

public class GetListQueryHandler implements QueryHandler<GetListQuery, List<StudentDto>> {

  @Override
  public List<StudentDto> handle(GetListQuery query) {
    StudentRepository studentRepo = new StudentRepository();
    return studentRepo.getList(query.enrolledIn(), query.numberOfCourses());
  }

}
