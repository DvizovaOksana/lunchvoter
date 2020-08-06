package ru.lunchvoter.util.exception;

import java.time.LocalTime;

public class LateForVoteException extends RuntimeException{
    public LateForVoteException(LocalTime message) {
        super(message.toString());
    }
}
