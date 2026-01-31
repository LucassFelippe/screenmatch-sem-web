package br.com.alura.screenmatch.Principal;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.service.ConsumoAPI;
import br.com.alura.screenmatch.service.ConverteDados;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private ConsumoAPI consumo = new ConsumoAPI();

    private ConverteDados conversor = new ConverteDados();

    private  final String ENDERECO = "http://www.omdbapi.com/";
    private  final String  APIKEY = "?apikey=98f10c4b&t=";

    public void exibeMenu(){
        System.out.println("Digite o nome da série para busca: ");

        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + APIKEY + nomeSerie.replace(" ", "+"));
        DadosSerie dados = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dados);





    }
}
