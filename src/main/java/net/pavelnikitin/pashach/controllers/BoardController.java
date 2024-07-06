package net.pavelnikitin.pashach.controllers;

import jakarta.servlet.http.HttpServletRequest;
import net.pavelnikitin.pashach.models.Board;
import net.pavelnikitin.pashach.models.Post;
import net.pavelnikitin.pashach.models.Topic;
import net.pavelnikitin.pashach.repositories.BoardRepository;
import net.pavelnikitin.pashach.repositories.PostRepository;
import net.pavelnikitin.pashach.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TopicRepository threadRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{board_code}")
    private String BoardPage(@PathVariable String board_code, Model model) {
        List<Board> boards = boardRepository.findByCodename(board_code);
        Board board;
        if (boards.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ваш ресурс везде искали, но так и не нашли");
        }
        board = boards.get(0);
        System.out.println(board);
        Iterable<Topic> topics = board.getTopics();
        for (Topic thr : topics) {
            System.out.println(thr);
        }
        model.addAttribute("board_id", board.getBoard_id());
        model.addAttribute("board_name", board.getName());
        model.addAttribute("board_code", board.getCodename());
        model.addAttribute("topics", topics );
        return "board";
    }

    @PostMapping("/{board_code}/add")
    private RedirectView ThreadPost_add(@PathVariable String board_code, @RequestParam String author_pseudo,
                                  @RequestParam String title, @RequestParam String post_content, HttpServletRequest request) {
        String authorIp = request.getRemoteAddr();
        Post post = Post.builder().authorPseudo(author_pseudo).title(title).postContent(post_content)
                .authorIp(authorIp).build();
        postRepository.save(post);
        Topic topic = new Topic();
        topic.addPost(post);
        threadRepository.save(topic);
        List<Board> boards = boardRepository.findByCodename(board_code);
        Board board;
        if (boards.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ваш ресурс везде искали, но так и не нашли");
        }
        board = boards.get(0);
        board.addThread(topic);
        boardRepository.save(board);

        return new RedirectView("/");
    }
}
