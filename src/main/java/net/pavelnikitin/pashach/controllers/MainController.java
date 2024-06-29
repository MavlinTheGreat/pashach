package net.pavelnikitin.pashach.controllers;

import net.pavelnikitin.pashach.models.Board;
import net.pavelnikitin.pashach.repositories.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private BoardRepository boardRepository;

    @GetMapping("/")
    public String MainPage(Model model) {
        Iterable<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        for (Board board: boards) {
            System.out.println(board.getName());
        }
        return "index";
    }

}
