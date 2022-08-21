package com.martinez.CQRSSample.CQRS.handler;

import com.martinez.CQRSSample.CQRS.command.Command;

public record DatabaseRetryDecorator(CommandHandler handler, Config config) implements
    CommandHandler {

  @Override
  public Result handle(Command command) {
    for (int i = 0; ; i++) {
      try {
        Result result = handler.handle(command);
        return result;
      } catch (Exception e) {
        if (i > config.getNumberOfDatabaseRetries() || !isDatabaseException(e)) {
          throw new RuntimeException(e);
        }
      }
    }
  }

  private boolean isDatabaseException(Exception exception) {
    String message = exception.getMessage();
    if (message == null) {
      return false;
    }
    return message.contains("connection");
  }
}
