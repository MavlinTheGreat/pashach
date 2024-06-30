package net.pavelnikitin.pashach.repositories;

import org.springframework.data.repository.CrudRepository;
import net.pavelnikitin.pashach.models.Board;

import java.util.List;

public interface BoardRepository extends CrudRepository<Board, Integer> {
    List<Board> findByCodename(String codename);
}
