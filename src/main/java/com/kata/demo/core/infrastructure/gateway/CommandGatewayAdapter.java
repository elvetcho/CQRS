package com.kata.demo.core.infrastructure.gateway;

import com.kata.demo.core.domain.coreapi.command.Command;
import com.kata.demo.core.domain.port.infrastructure.CommandGateway;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class CommandGatewayAdapter implements CommandGateway {
    private final ApplicationEventPublisher applicationEventPublisher;

    public CommandGatewayAdapter(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void send(Command command) {
        applicationEventPublisher.publishEvent(command);
    }
}
