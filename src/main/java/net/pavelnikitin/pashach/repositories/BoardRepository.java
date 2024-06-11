package net.pavelnikitin.pashach.repositories;

import org.springframework.data.repository.CrudRepository;
import net.pavelnikitin.pashach.models.Board;

public interface BoardRepository extends CrudRepository<Board, Integer> {}
