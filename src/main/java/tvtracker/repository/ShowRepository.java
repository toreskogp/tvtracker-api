package tvtracker.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tvtracker.domain.Show;

@Repository
public interface ShowRepository extends CrudRepository<Show, Integer> {
	
	Show findById(int id);
	
	Show findByTvmazeId(int id);
}
