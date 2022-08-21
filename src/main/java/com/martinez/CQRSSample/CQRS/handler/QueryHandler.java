package com.martinez.CQRSSample.CQRS.handler;

public interface QueryHandler<T, S> {

  S handle(T query);
}
