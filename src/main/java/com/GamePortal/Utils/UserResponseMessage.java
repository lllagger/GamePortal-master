package com.GamePortal.Utils;

import com.GamePortal.Utils.AbstractUtils.IResponceMessage;

public class UserResponseMessage implements IResponceMessage {

    private String message;

    public UserResponseMessage(String message) {
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
