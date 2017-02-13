package com.ironyard.controllers;

import com.ironyard.data.ChatPermissions;
import com.ironyard.data.ChatUser;
import com.ironyard.repositories.ChatPermissionRepo;
import com.ironyard.repositories.ChatUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by osmanidris on 2/10/17.
 */
@Controller
public class UserController {
    @Autowired
    private ChatUserRepo chatUserRepo;
    @Autowired
    private ChatPermissionRepo chatPermissionRepo;

    @RequestMapping(path = "/open/authenticate", method = RequestMethod.POST)
    public String login(HttpSession session, Model data, @RequestParam(name = "username") String usr, @RequestParam String password){
        ChatUser found = chatUserRepo.findByUsernameAndPassword(usr, password);
        String destinationView = "home";
        if(found == null){
            // no user found, login must fail
            destinationView = "/open/login";
            data.addAttribute("message", true);
        }else{
            List<String> permissions= new ArrayList<>();
            for(int i =0 ;i < found.getUserPermissions().size();i++){
                permissions.add(found.getUserPermissions().get(i).getKey());
            }

            session.setAttribute ("user", found);
            session.setAttribute ("permissions", permissions);
            destinationView = "/secure/home";
        }
        return destinationView;
    }

    @RequestMapping(path = "/secure/users/create", method = RequestMethod.POST)
    public String createMovie(Model dataToJsp, @RequestParam String username,
                              @RequestParam String password,
                              @RequestParam String displayName,
                              @RequestParam(required=false) List<String> permissions){
        // save to database
        ChatUser cUser = new ChatUser(username,password,displayName);
        List<ChatPermissions> userPermissions = new ArrayList<>();
        if(permissions.size()>0){
            for(int i = 0; i < permissions.size();i++){
                userPermissions.add(chatPermissionRepo.findByKey(permissions.get(i)));
            }
            cUser.setUserPermissions(userPermissions);
        }
        chatUserRepo.save(cUser);
        return "forward:/secure/users";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/secure/home";
    }

    @RequestMapping(path = "/secure/users")
    public String listUsers(Model xyz){
        String destination = "/secure/users";
        Iterable found = chatUserRepo.findAll();
        // put list into model
        xyz.addAttribute("uList", found);
        // go to jsp
        return destination;
    }

    @RequestMapping(path = "/secure/user/delete", method = RequestMethod.GET)
    public String deleteUser(HttpSession session,Model dataToJsp, @RequestParam Long id){
        ChatUser loggedInUser = (ChatUser) session.getAttribute("user");
        String msg = "Logged in user is not allowed to be deleted";
        if(loggedInUser.getID()!= id) {
            chatUserRepo.delete(id);
            msg = "User is deleted successfully !";
        }
        dataToJsp.addAttribute("success_user_operation_msg",msg);
        return "forward:/secure/users";
    }

    @RequestMapping(path = "/secure/logout")
    public String login(HttpSession session){
        session.removeAttribute("user");
        String destinationView = "/open/login";
        return destinationView;
    }

}
