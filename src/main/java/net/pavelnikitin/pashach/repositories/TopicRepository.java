package net.pavelnikitin.pashach.repositories;

import net.pavelnikitin.pashach.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {}
