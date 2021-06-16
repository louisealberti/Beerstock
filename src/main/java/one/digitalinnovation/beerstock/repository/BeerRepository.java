package one.digitalinnovation.beerstock.repository;

import java.util.Optional;

import one.digitalinnovation.beerstock.entity.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {
	
	Optional<Beer> findByName(String name);
	
}
