package br.com.b2w.starwars.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.b2w.starwars.models.Planeta;
import br.com.b2w.starwars.repositories.PlanetaRepository;

@Service
public class PlanetaService {

	@Autowired
	private PlanetaRepository repo;

	public List<Planeta> findAll() {
		return repo.findAll();
	}

	public Planeta save(Planeta planeta) {
		try {
			/*
			 * ao salvar, é verificado do planeta a ser cadastrado quantas aparições em filmes o mesmo teve
			 * o resultado é setado para a propriedade quantidadeAparicoesFilmes no objeto planeta
			 */
			planeta.setQuantidadeAparicoesFilmes(buscarQuantidadeAparicoesFilmes(planeta.getNome()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return repo.save(planeta);
	}

	/*
	 * Método para realizar busca do numero de aparições em filmes do sistema a ser cadastrado
	 * 
	 * @param String - nome do planeta a ser cadastrado
	 * @return Integer - numero de aparições do planeta em filmes
	 */
	private Integer buscarQuantidadeAparicoesFilmes(String nome) throws MalformedURLException, IOException {
		String url = "https://swapi.co/api/planets/?search=" + nome + "&format=json";
		url = url.replace(" ", "%20");

		HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JsonNode json = new ObjectMapper().readTree(response.toString());
		JsonNode results = json.get("results");
		JsonNode planeta = results.get(0);
		JsonNode films = planeta.get("films");

		return films.size();
	}

	public List<Planeta> buscarPorNome(String nome) {
		return repo.buscarPorNome(nome);
	}

	public Optional<Planeta> buscarPorID(String id) {
		return repo.findById(id);
	}

	public void deletar(String id) {
		repo.deleteById(id);
	}
}
