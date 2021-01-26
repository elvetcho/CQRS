package com.kata.demo.core.infrastructure.gateway;

import com.kata.demo.core.domain.coreapi.event.Event;
import com.kata.demo.core.domain.port.infrastructure.EventGateway;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventGatewayAdapter implements EventGateway {
    private final ApplicationEventPublisher eventPublisher;

    public EventGatewayAdapter(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(Event event) {
        eventPublisher.publishEvent(event);
    }
}
