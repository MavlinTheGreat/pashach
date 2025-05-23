package net.pavelnikitin.pashach.controllers;

import jakarta.servlet.http.HttpServletRequest;
import net.pavelnikitin.pashach.models.Board;
import net.pavelnikitin.pashach.models.Pickrelated;
import net.pavelnikitin.pashach.models.Post;
import net.pavelnikitin.pashach.models.Topic;
import net.pavelnikitin.pashach.repositories.BoardRepository;
import net.pavelnikitin.pashach.repositories.PickrelatedRepository;
import net.pavelnikitin.pashach.repositories.PostRepository;
import net.pavelnikitin.pashach.repositories.TopicRepository;
import net.pavelnikitin.pashach.services.PickrelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TopicRepository threadRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PickrelatedRepository pickrelatedRepository;

    @Autowired
    private PickrelatedService pickrelatedService;

    @GetMapping("/{board_code}")
    private String BoardPage(@PathVariable String board_code, Model model) {
        List<Board> boards = boardRepository.findByCodename(board_code);
        Board board;
        if (boards.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ваш ресурс везде искали, но так и не нашли");
        }
        board = boards.get(0);
        Iterable<Topic> topics = board.getTopics();
        model.addAttribute("board_id", board.getBoard_id());
        model.addAttribute("board_name", board.getName());
        model.addAttribute("board_code", board.getCodename());
        model.addAttribute("board_desc", board.getDescription());
        model.addAttribute("topics", topics );
        return "board";
    }

    @PostMapping("/{board_code}/add")
    private RedirectView ThreadPost_add(@PathVariable String board_code, @RequestParam String author_pseudo,
                                  @RequestParam String title, @RequestParam String post_content,
                                        @RequestParam("picrel") MultipartFile file,
                                        HttpServletRequest request) {
        String authorIp = request.getRemoteAddr();
        if (author_pseudo.isBlank()) {
            author_pseudo = "Анон";
        }
        if (title.isBlank()) {
            title = "(Без названия)";
        }


        Post post = Post.builder().authorPseudo(author_pseudo).postContent(post_content)
                .authorIp(authorIp).creationDate(LocalDateTime.now()).build();
        if (!file.isEmpty()) {
            Pickrelated pickrelated = new Pickrelated();
            pickrelatedRepository.save(pickrelated);
            pickrelatedService.addPic(pickrelated, file);
            pickrelatedRepository.save(pickrelated);
            post.addPickrelated(pickrelated);
        }
        postRepository.save(post);
        Topic topic = new Topic();
        topic.setTitle(title);
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

        return new RedirectView("/" + board_code);
    }
}
