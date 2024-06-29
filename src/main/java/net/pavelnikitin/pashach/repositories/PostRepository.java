package net.pavelnikitin.pashach.repositories;

import net.pavelnikitin.pashach.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}
