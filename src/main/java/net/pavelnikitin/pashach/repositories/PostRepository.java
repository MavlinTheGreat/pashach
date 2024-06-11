package net.pavelnikitin.pashach.repositories;

import net.pavelnikitin.pashach.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {}
