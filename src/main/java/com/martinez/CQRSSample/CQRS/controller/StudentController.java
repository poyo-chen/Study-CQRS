package com.martinez.CQRSSample.CQRS.controller;

import com.martinez.CQRSSample.CQRS.command.EditPersonalInfoCommand;
import com.martinez.CQRSSample.CQRS.command.GetListQuery;
import com.martinez.CQRSSample.CQRS.dto.StudentPersonalInfoDto;
import com.martinez.CQRSSample.CQRS.handler.CommandHandler;
import com.martinez.CQRSSample.CQRS.handler.EditPersonalInfoCommandHandler;
import com.martinez.CQRSSample.CQRS.handler.GetListQueryHandler;
import com.martinez.CQRSSample.CQRS.handler.QueryHandler;
import com.martinez.CQRSSample.CQRS.handler.Result;
import javax.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {


  @PutMapping("{id}")
  public String editPersonalInfo(@PathParam("id") long id,
      @RequestBody StudentPersonalInfoDto dto) {
    //命令必須具體代表執行的操作，把要做什麼呈現出來
    //不希望看到命令自己去執行，而是應該有一個類別負責處裡命令(類似use case)
//    Command command=new EditPersonalInfoCommand();
//    command.execute();

    //controller與use case使用的命令物件轉換
    EditPersonalInfoCommand command = new EditPersonalInfoCommand();
    command.setEmail(dto.getEmail());
    command.setName(dto.getName());
    command.setId(id);
    CommandHandler handler = new EditPersonalInfoCommandHandler();
    Result result = handler.handle(command);
    return result.isSuccess ? "ok" : result.error();
  }

  @GetMapping
  public String getList(String enrolled, int number) {
    GetListQuery query = new GetListQuery(enrolled, number);
    QueryHandler handler = new GetListQueryHandler();
    //TODO 轉型未處裡
    Object handle = handler.handle(query);

    return (String) handle;
  }
}
