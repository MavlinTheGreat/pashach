package net.pavelnikitin.pashach.controllers;

import net.pavelnikitin.pashach.repositories.PostRepository;
import net.pavelnikitin.pashach.repositories.ThreadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BoardController {
    @Autowired
    private ThreadRepository threadRepository;

    @Autowired
    private PostRepository postRepository;
}
