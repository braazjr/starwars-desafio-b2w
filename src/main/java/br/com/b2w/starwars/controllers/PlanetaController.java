package br.com.b2w.starwars.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b2w.starwars.models.Planeta;
import br.com.b2w.starwars.service.PlanetaService;

@RestController
@RequestMapping("/planeta")
public class PlanetaController {

	@Autowired
	private PlanetaService service;

	/*
	 * Serviço GET para busca de planetas
	 * 
	 * @return List<Planeta> - Lista de planetas
	 */
	@GetMapping("/buscarPlanetas")
	public List<Planeta> buscarPlanetas() {
		return service.findAll();
	}

	/*
	 * Serviço POST para salvar planeta
	 * 
	 * @param Planeta - Objeto planeta a ser salvo
	 * 
	 * @return Planeta - Planeta salvo
	 */
	@PostMapping
	public ResponseEntity<?> salvarPlaneta(@RequestBody @Valid Planeta planeta) {
		Planeta planetaSalvo = service.save(planeta);

		if (planetaSalvo != null) {
			return ResponseEntity.ok(planetaSalvo);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	/*
	 * Serviço GET para busca por nome
	 * 
	 * @param String - Nome dos planetas a serem encontrado
	 * 
	 * @return List<Planeta> - Lista de planetas encontrados
	 */
	@GetMapping("/buscarPorNome/{nome}")
	public ResponseEntity<?> buscarPorNome(@PathVariable String nome) {
		if (nome != null) {
			return ResponseEntity.ok(service.buscarPorNome(nome));
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	/*
	 * Serviço GET para busca por ID
	 * 
	 * @param String ID - ID do planeta a ser encontrado
	 * 
	 * @return Planeta - Planeta encontrado
	 */
	@GetMapping("/buscarPorID/{id}")
	public ResponseEntity<?> buscarPorID(@PathVariable String id) {
		if (id != null) {
			return ResponseEntity.ok(service.buscarPorID(id));
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}

	/*
	 * Serviço DELETE para excluir planeta por ID
	 * 
	 * @param String ID - ID do planeta a ser deletado
	 * 
	 * @return Boolean - true em caso de sucesso
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletar(@PathVariable String id) {
		if (id != null) {
			service.deletar(id);
			return ResponseEntity.ok(true);
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	}
}
