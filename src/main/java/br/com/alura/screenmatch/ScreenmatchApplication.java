package br.com.alura.screenmatch;

import br.com.alura.screenmatch.Principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();

		principal.exibeMenu();


//		List<DadosTemporadas> temporadas = new ArrayList<>();
//
//		for(int i=1;i<dados.totalTemporadas();i++){
//			json = consumoApi.obterDados("http://www.omdbapi.com/?apikey=98f10c4b&i=tt0238784&Season="+ i);
//			DadosTemporadas dadosTemporadas = conversor.obterDados(json, DadosTemporadas.class);
//			temporadas.add(dadosTemporadas);
//		}
//
//		temporadas.forEach(System.out::println);
	}


}
