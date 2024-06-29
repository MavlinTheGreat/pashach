package net.pavelnikitin.pashach.repositories;

import net.pavelnikitin.pashach.models.Thread;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThreadRepository extends JpaRepository<Thread, Long> {}
