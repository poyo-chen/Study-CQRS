package com.martinez.CQRSSample.CQRS.handler;

import com.martinez.CQRSSample.CQRS.command.Command;

public interface CommandHandler<T extends Command> {

  Result handle(T command);
}
