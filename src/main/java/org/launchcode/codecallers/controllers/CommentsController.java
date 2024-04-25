package org.launchcode.codecallers.controllers;

import org.launchcode.codecallers.models.Comments;
import org.launchcode.codecallers.models.data.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/comments")
public class CommentsController {

    @Autowired
    CommentsRepository commentsRepository;

    @PostMapping("/create")
    public String addComment(@RequestBody Comments newComments) {

        Comments comments = new Comments(
                newComments.getUserID(),
                newComments.getUsername(),
                newComments.getDate(),
                newComments.getComment());


        commentsRepository.save(comments);

        return "";
    }

    @GetMapping("/index")
    public List<Comments> findAll() {
        return(List<Comments>) commentsRepository.findAll();
    }
}
