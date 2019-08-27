//***********************************************************************
//
//	Capítulo 7 - Streams e Collectors
//
//***********************************************************************

package br.com.hosseinramon.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Capitulo07 {

	public static void main(String ... args) {

 		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);

 		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

 		// Filtrar os 2 usuários com mais pontos e torná-los moderadores
		usuarios.sort(Comparator.comparing(Usuario::getPontos).reversed());
		usuarios.subList(0, 2).forEach(Usuario::tornaModerador);

 		// Imprimir todos os usuários com mais de 130 pontos - versão 1
		Stream<Usuario> stream1 = usuarios.stream()
				.filter(u -> u.getPontos() > 130);

 		stream1.forEach(System.out::println);

 		// Imprimir todos os usuários com mais de 130 pontos - versão 2
		usuarios.stream().filter(u -> u.getPontos() > 130);

 		usuarios.forEach(System.out::println);

 		// Imprimir todos os usuários com mais de 130 pontos - versão 3
		usuarios.stream().filter(u -> u.getPontos() > 130)
				.forEach(System.out::println);

 		// Tornar os usuários com mais de 130 pontos em moderadores
		usuarios.stream().filter(u -> u.getPontos() > 130)
				.forEach(Usuario::tornaModerador);

 		// Criar uma lista de usuários com mais de 130 pontos
		List<Usuario> maisQue130 = new ArrayList<>();
		usuarios.stream().filter(u -> u.getPontos() > 130)
				.forEach(maisQue130::add);

 		// Resgatar elementos do stream para uma lista - versão 1
		Supplier<ArrayList<Usuario>> supplier = ArrayList::new;
		BiConsumer<ArrayList<Usuario>, Usuario> accumulador = ArrayList::add;
		BiConsumer<ArrayList<Usuario>, ArrayList<Usuario>> combiner = ArrayList::addAll;

 		List<Usuario> maisQue100 = usuarios.stream()
				.filter(u -> u.getPontos() > 100)
				.collect(supplier, accumulador, combiner);

 		// Resgatar elementos do stream para uma lista - versão 2
		usuarios.stream().filter(u -> u.getPontos() > 100)
				.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

 		// Resgatar elementos do stream para uma lista - versão 3
		usuarios.stream().filter(u -> u.getPontos() > 100).collect(Collectors.toList());

 		// Criar uma lista de usuários com mais de 150 pontos
		List<Usuario> maisQue150 = usuarios.stream()
				.filter(u -> u.getPontos()> 150)
				.collect(Collectors.toList());

 		// Criar um set de usuários com mais de 120 pontos
		Set<Usuario> maisQue120 = usuarios.stream()
				.filter(u -> u.getPontos()> 150)
				.collect(Collectors.toSet());

 		// Definir no código a forma da coleta dos usuários
		Set<Usuario> set = usuarios.stream()
				.collect(Collectors.toCollection(HashSet::new));

 		// Criar uma lista com as pontuações de todos os usuários - versão 1
		List<Integer> pontos1 = new ArrayList<>();
		usuarios.forEach(u -> pontos1.add(u.getPontos()));

 		// Criar uma lista com as pontuações de todos os usuários - versão 2
		List<Integer> pontos2 = usuarios.stream()	
				.map(u -> u.getPontos()).
				collect(Collectors.toList());

 		// Criar uma lista com as pontuações de todos os usuários - versão 3
		List<Integer> pontos3 = usuarios.stream()
				.map(Usuario::getPontos)
				.collect(Collectors.toList());

 		// Criar um stream de inteiros com as pontuações de todos os usuários - versão 1
		Stream<Integer> stream2 = usuarios.stream().map(Usuario::getPontos);

 		// Criar um stream de inteiros com as pontuações de todos os usuários - versão 2
		IntStream stream3 = usuarios.stream().mapToInt(Usuario::getPontos);

 		// Retornar a pontuação média dos usuários
		double pontuacaomedia1 = usuarios.stream().mapToInt(Usuario::getPontos)
				.average().getAsDouble();

 		// Retorna a pontuação média dos usuários, se não existir retorna zero
		double pontuacaomedia2 = usuarios.stream().mapToInt(Usuario::getPontos)
				.average().orElse(0.0);

 		// Retorna a pontuação média dos usuários, se não existir lança uma exception
		double pontuacaomedia3 = usuarios.stream().mapToInt(Usuario::getPontos)
				.average().orElseThrow(IllegalStateException::new);

 		// Retorna a pontuação do usuário com maior número de pontos
		Optional<Usuario> max = usuarios.stream()
				.max(Comparator.comparingInt(Usuario::getPontos));

 		// Retorna o usuário com maior número de pontos
		Optional<String> maxNome = usuarios.stream()
				.max(Comparator.comparingInt(Usuario::getPontos))
				.map(u -> u.getNome());
	}
}
