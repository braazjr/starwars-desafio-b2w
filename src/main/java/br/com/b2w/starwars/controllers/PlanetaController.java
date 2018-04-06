package br.com.b2w.starwars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	 * @return Planeta - Planeta salvo
	 */
	@PostMapping
	public Planeta salvarPlaneta(@RequestBody Planeta planeta) {
		return service.save(planeta);
	}
	
	/*
	 * Serviço GET para busca por nome
	 * 
	 * @param String - Nome dos planetas a serem encontrado
	 * @return List<Planeta> - Lista de planetas encontrados
	 */
	@GetMapping("/buscarPorNome/{nome}")
	public List<Planeta> buscarPorNome(@PathVariable String nome) {
		return service.buscarPorNome(nome);
	}
	
	/*
	 * Serviço GET para busca por ID
	 * 
	 * @param String ID - ID do planeta a ser encontrado
	 * @return Planeta - Planeta encontrado
	 */
	@GetMapping("/buscarPorID/{id}")
	public Planeta buscarPorID(@PathVariable String id) {
		return service.buscarPorID(id).get();
	}
	
	/*
	 * Serviço DELETE para excluir planeta por ID
	 * 
	 * @param String ID - ID do planeta a ser deletado
	 */
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable String id) {
		service.deletar(id);
	}
}
