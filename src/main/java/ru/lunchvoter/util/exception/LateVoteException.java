package ru.lunchvoter.util.exception;

import java.time.LocalTime;

public class LateVoteException extends RuntimeException{
    public LateVoteException(LocalTime message) {
        super(message.toString());
    }
}
