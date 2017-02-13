package com.ironyard.data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by osmanidris on 2/10/17.
 */
@Entity
public class ChatMessage {
    @Id @GeneratedValue
    private Long ID;

    private String messageText;
    @ManyToOne
    private ChatUser msgUser;

    public String getMesssageText() {
        return messageText;
    }

    public ChatMessage(){}

    public ChatMessage(String messageText, ChatUser msgUser) {
        this.messageText = messageText;
        this.msgUser = msgUser;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public ChatUser getMsgUser() {
        return msgUser;
    }

    public void setMsgUser(ChatUser msgUser) {
        this.msgUser = msgUser;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
