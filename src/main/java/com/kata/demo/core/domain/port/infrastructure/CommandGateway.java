package com.kata.demo.core.domain.port.infrastructure;

import com.kata.demo.core.domain.coreapi.command.Command;

public interface CommandGateway {
    void send(Command command);
}
