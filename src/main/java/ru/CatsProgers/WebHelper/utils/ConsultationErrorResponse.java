package ru.CatsProgers.WebHelper.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsultationErrorResponse extends RuntimeException {
    private String message;
    private long timestamp;

    public ConsultationErrorResponse(String message, long timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }
}
