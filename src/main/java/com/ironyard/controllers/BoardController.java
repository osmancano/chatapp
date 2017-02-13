package com.ironyard.controllers;

import com.ironyard.data.ChatBoard;
import com.ironyard.data.ChatMessage;
import com.ironyard.repositories.ChatBoardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by osmanidris on 2/12/17.
 */
@Controller
public class BoardController {
    @Autowired
    private ChatBoardRepo chatBoardRepo;

    @RequestMapping(value = "/secure/boards")
    public String boardsList(Model data) {
        Iterable found = chatBoardRepo.findAll();
        // put list into model
        data.addAttribute("bList", found);
        return "/secure/boards";
    }

    @RequestMapping(value = "/secure/boards/create", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createNewBoard(ChatBoard newBoard) {
        chatBoardRepo.save(newBoard);
        return "forward:/secure/boards";
    }

    @RequestMapping(value = "/secure/boards/messages")
    public String boardMessages(Model data, @RequestParam Long boardId) {
        ChatBoard found = chatBoardRepo.findOne(boardId);
        List<ChatMessage> boardMessages = found.getBoardMessages();
        // put list into model
        if(boardMessages.size() > 0) {
            System.out.println(boardMessages.get(0).getMesssageText());
        }
        data.addAttribute("selectedBoard", found);
        data.addAttribute("bMessages", boardMessages);
        return "/secure/currentBoard";
    }

}

