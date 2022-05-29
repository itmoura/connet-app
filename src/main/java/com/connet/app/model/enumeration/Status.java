package com.connet.app.model.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.stream.Stream;

public enum Status {
    ACCEPTED, PENDING, REJECTED, CANCELED, DONE, IN_PROGRESS;

    @JsonCreator
    public static Status of(final String name) {
        return Stream.of(Status.values())
                .filter(targetEnum -> name.equals(targetEnum.name()))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
