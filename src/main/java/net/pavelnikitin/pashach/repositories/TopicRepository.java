package net.pavelnikitin.pashach.repositories;

import net.pavelnikitin.pashach.models.Board;
import net.pavelnikitin.pashach.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {}
