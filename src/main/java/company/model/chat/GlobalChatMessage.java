package company.model.chat;

import company.model.User;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "global_chat_messages")
public class GlobalChatMessage {

    public enum MessageType{
        CHAT, JOIN, LEAVE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    protected long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    protected User sender;

    @Column(length = 1024)
    protected String text;

    @Column(name = "message_type")
    protected MessageType messageType;

    protected Timestamp timestamp;

    public GlobalChatMessage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }
}
