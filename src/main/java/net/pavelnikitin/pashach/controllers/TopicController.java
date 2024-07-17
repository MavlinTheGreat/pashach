package net.pavelnikitin.pashach.controllers;

import jakarta.servlet.http.HttpServletRequest;
import net.pavelnikitin.pashach.models.Post;
import net.pavelnikitin.pashach.models.Topic;
import net.pavelnikitin.pashach.repositories.PostRepository;
import net.pavelnikitin.pashach.repositories.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Optional;

@Controller
public class TopicController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping("/{board_code}/{topic_code}")
    private String TopicPage(@PathVariable String board_code, @PathVariable Long topic_code, Model model) {
        Optional<Topic> topics = topicRepository.findById(topic_code);
        Topic topic;
        if (topics.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ваш ресурс везде искали, но так и не нашли");
        }
        topic = topics.get();
        Iterable<Post> posts = topic.getPosts();
        Post first_post = topic.getPosts().get(0);
        model.addAttribute("topic_title", topic.getTitle());
        model.addAttribute("topic_id", topic.getThread_id());
        System.out.println(posts);
        model.addAttribute("posts", posts);
        return "topic";
    }

    @PostMapping("/{board_code}/{topic_code}/add")
    private RedirectView addPostTopic(@PathVariable String board_code, @PathVariable Long topic_code,
                                      @RequestParam String author_pseudo, @RequestParam String post_content,
                                      HttpServletRequest request, Model model) {
        String authorIp = request.getRemoteAddr();
        if (author_pseudo.isBlank()) {
            author_pseudo = "Анон";
        }
        Post post = Post.builder().authorPseudo(author_pseudo).postContent(post_content)
                .authorIp(authorIp).build();
        postRepository.save(post);
        Optional<Topic> topics = topicRepository.findById(topic_code);
        Topic topic;
        if (topics.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ваш ресурс везде искали, но так и не нашли");
        }
        topic = topics.get();
        topic.addPost(post);
        topicRepository.save(topic);
        return new RedirectView(String.format("/%s/%s", board_code, topic_code));
    }
}
