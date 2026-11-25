package com.example;

// ============================================================
// ACTIVITY 4 -- A CUSTOM EVENT
// ============================================================
// Two pieces make an event type of your own:
//   1. a class extending javafx.event.Event
//   2. a static EventType<T> constant identifying it
//
// The EventType's parent (here Event.ANY) places it in the type
// hierarchy, which is what lets addEventHandler match on it.
// ============================================================

import javafx.event.Event;
import javafx.event.EventType;

public class LoginSuccessEvent extends Event {

    public static final EventType<LoginSuccessEvent> LOGIN_SUCCESS =
            new EventType<>(Event.ANY, "LOGIN_SUCCESS");

    // The payload the event carries to every listener.
    private final String username;

    public LoginSuccessEvent(String username) {
        super(LOGIN_SUCCESS);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
