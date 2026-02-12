package br.com.alura.screenmatch.Principal;

import br.com.alura.screenmatch.model.DadosEpisodio;
import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporadas;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private final Scanner leitura = new Scanner(System.in);

    private final ConsumoAPI consumo = new ConsumoAPI();

    private final ConverteDados conversor = new ConverteDados();

    private  final String ENDERECO = "http://www.omdbapi.com/";
    private  final String  APIKEY = "?apikey=98f10c4b&t=";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca: ");

        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + APIKEY + nomeSerie.replace(" ", "+"));
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);

        List<DadosTemporadas> temporadas = new ArrayList<>();

		for(int i=1;i<dados.totalTemporadas();i++){
			json = consumo.obterDados(ENDERECO + APIKEY + nomeSerie.replace(" ", "+") + "&season=" + i);
			DadosTemporadas dadosTemporadas = conversor.obterDados(json, DadosTemporadas.class);
			temporadas.add(dadosTemporadas);
		}

		temporadas.forEach(System.out::println);

//        for (int i = 0; i < temporadas.size(); i++) {
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                System.out.println(episodiosTemporada.get(j).titulo());
//
//            }
//        }

        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\n Top 5 episodios ");
            dadosEpisodios.stream()
                    .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                    .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                    .limit(5)
                    .forEach(System.out::println);



    }
}
