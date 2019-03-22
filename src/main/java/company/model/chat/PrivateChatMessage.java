package company.model.chat;

import company.model.User;

public class PrivateChatMessage extends GlobalChatMessage {

    private User receiver;

    public PrivateChatMessage() {
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

}
