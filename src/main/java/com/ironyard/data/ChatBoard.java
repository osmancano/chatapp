package com.ironyard.data;

import javax.persistence.*;
import java.util.List;

/**
 * Created by osmanidris on 2/10/17.
 */
@Entity
public class ChatBoard {
    @Id @GeneratedValue
    private Long ID;
    private String boardName;
    private String Description;
    @OneToMany
    private List<ChatMessage> boardMessages;

    public ChatBoard(){
    }

    public String getBoardName() {
        return boardName;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
    @OneToMany
    public List<ChatMessage> getBoardMessages() {
        return boardMessages;
    }

    public void setBoardMessages(List<ChatMessage> boardMessages) {
        this.boardMessages = boardMessages;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }
}
