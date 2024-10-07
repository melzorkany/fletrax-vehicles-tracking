package com.fletrax.model;

public class TrackingState {
    private int continuousUndelayedMessageCounter;
    private int messageCounter;

    // Getters and Setters
    public void incrementUndelayedCounter() {
        this.continuousUndelayedMessageCounter++;
    }

    public void resetUndelayedCounter() {
        this.continuousUndelayedMessageCounter = 0;
    }

    public void incrementMessageCounter() {
        this.messageCounter++;
    }

    public int getContinuousUndelayedMessageCounter() {
        return continuousUndelayedMessageCounter;
    }

    public int getMessageCounter() {
        return messageCounter;
    }

    public void setContinuousUndelayedMessageCounter(int continuousUndelayedMessageCounter) {
        this.continuousUndelayedMessageCounter = continuousUndelayedMessageCounter;
    }

    public void setMessageCounter(int messageCounter) {
        this.messageCounter = messageCounter;
    }
}
