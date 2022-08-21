package com.martinez.CQRSSample.CQRS.command;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditPersonalInfoCommand implements Command {

  private long id;
  private String name;
  private String email;
}
