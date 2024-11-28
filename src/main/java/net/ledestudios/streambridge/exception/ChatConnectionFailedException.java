package net.ledestudios.streambridge.exception;

import lombok.Getter;

@Getter
public class ChatConnectionFailedException extends IllegalStateException {

    private final int errorCode;
    private final String errorMessage;

    public ChatConnectionFailedException(int errorCode, String errorMessage) {
        super(String.format("Failed to connect to chat! (Message: %s, Code: %d)", errorMessage, errorCode));
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
