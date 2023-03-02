package com.GamePortal.Utils;

import com.GamePortal.Utils.AbstractUtils.IResponceMessage;

public class GameResponseMessage implements IResponceMessage {
    private String message;

    public GameResponseMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
