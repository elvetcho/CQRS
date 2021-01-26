package com.kata.demo.core.domain.port.infrastructure;

import com.kata.demo.core.domain.coreapi.event.Event;

public interface EventGateway {
    void publish(Event event);
}
