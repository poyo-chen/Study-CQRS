package com.martinez.CQRSSample.CQRS.handler;

import com.martinez.CQRSSample.CQRS.command.EditPersonalInfoCommand;
import com.martinez.CQRSSample.CQRS.repository.StudentRepository;
import com.martinez.CQRSSample.CRUD.entity.Student;

public class EditPersonalInfoCommandHandler implements CommandHandler<EditPersonalInfoCommand> {

  @Override
  public Result handle(EditPersonalInfoCommand command) {
    StudentRepository studentRepository = new StudentRepository();
    Student student = studentRepository.getById(command.getId());
    if (student == null) {
      return Result.fail("No student found with Id " + command.getId());
    }
    student.setName(command.getName());
    student.setEmail(command.getEmail());
    studentRepository.update(student);
    return Result.ok();
  }

}
