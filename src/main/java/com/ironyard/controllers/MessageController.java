package com.ironyard.controllers;

import com.ironyard.data.ChatBoard;
import com.ironyard.data.ChatMessage;
import com.ironyard.data.ChatUser;
import com.ironyard.repositories.ChatBoardRepo;
import com.ironyard.repositories.ChatMessageRepo;
import com.ironyard.repositories.ChatUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by osmanidris on 2/12/17.
 */
@Controller
public class MessageController {
    @Autowired
    private ChatMessageRepo chatMessageRepo;
    @Autowired
    private ChatBoardRepo chatBoardRepo;
    @Autowired
    private ChatUserRepo chatUserRepo;
    @RequestMapping(path = "/secure/messages/create", method = RequestMethod.POST)
    public String createMovie(HttpSession session, Model dataToJsp, @RequestParam String messageText,
                              @RequestParam Long boardId){
        String dest = "forward:/secure/boards/messages";
        ChatBoard currBoard = chatBoardRepo.findOne(boardId);
        ChatUser user = (ChatUser) session.getAttribute("user");
        ChatMessage msg = new ChatMessage(messageText,user);
        chatMessageRepo.save(msg);
        currBoard.getBoardMessages().add(msg);
        // save to database
        chatBoardRepo.save(currBoard);

        return dest;
    }
}
