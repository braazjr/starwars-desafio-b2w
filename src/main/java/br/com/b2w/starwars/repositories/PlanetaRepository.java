package br.com.b2w.starwars.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.b2w.starwars.models.Planeta;

@Repository
public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	@Query(value = "{nome:?0}")
	List<Planeta> buscarPorNome(String nome);

}
