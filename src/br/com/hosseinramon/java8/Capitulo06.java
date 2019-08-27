//***********************************************************************
//
//	Capítulo 6 - Method References
//
//***********************************************************************

package br.com.hosseinramon.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.ToIntBiFunction;

public class Capitulo06 {

	public static void main(String ... args) {

 		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);

 		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);

 		// Tornar todos os usuários moderadores - versão 1
		usuarios.forEach(u -> u.tornaModerador());

 		// Tornar todos os usuários moderadores - versão 2
		usuarios.forEach(Usuario::tornaModerador);

 		// Tornar todos os usuários moderadores - versão 3
		Consumer<Usuario> tornaModerador = Usuario::tornaModerador;
		usuarios.forEach(tornaModerador);

 		// Ordenando usuários por nome - versão 1
		usuarios.sort(Comparator.comparing(u -> u.getNome()));

 		// Ordenando usuários por nome - versão 2
		usuarios.sort(Comparator.comparing(Usuario::getNome));

 		// Ordenando usuários por nome - versão 3
		Function<Usuario, String> byName = Usuario::getNome;
		usuarios.sort(Comparator.comparing(byName));

 		// Ordenando usuários por pontos - versão 1
		usuarios.sort(Comparator.comparingInt(u -> u.getPontos()));

 		// Ordenando usuários por pontos - versão 2
		usuarios.sort(Comparator.comparing(Usuario::getPontos));

 		// Ordenando usuários por pontos e em caso de empate por nomes - versão 1
		Comparator<Usuario> c = Comparator.comparingInt(Usuario::getPontos)
				.thenComparing(Usuario::getNome);

 		// Ordenando usuários por pontos e em caso de empate por nomes - versão 2
		usuarios.sort(Comparator.comparingInt(Usuario::getPontos)
				.thenComparing(Usuario::getNome));

 		// Ordenando usuários por nomes e colocando elementos nulos no final da lista
		usuarios.sort(Comparator.nullsLast(Comparator.comparing(Usuario::getNome)));

 		// Ordenando usuários por pontos de forma decrescente.
		usuarios.sort(Comparator.comparing(Usuario::getPontos).reversed());

 		// Method Reference que invoca um de seus métodos - versão 1
		Runnable bloco1 = user1::tornaModerador;
		bloco1.run();

 		// Method Reference que invoca um de seus métodos - versão 2
		Runnable bloco2 = () -> user1.tornaModerador();
		bloco2.run();

 		// Imprimir o nome de todos os usuários
		usuarios.forEach(System.out::println);

 		// Referenciando construtores com (1) argumento
		Function<String, Usuario> criadorDeUsuarios1 = Usuario::new;
		Usuario rodrigo1 = criadorDeUsuarios1.apply("Rodrigo Turini");
		Usuario paulo1 = criadorDeUsuarios1.apply("Paulo Silveira");

 		// Referenciando construtores com (2) argumentos
		BiFunction<String, Integer, Usuario> criadorDeUsuarios2 = Usuario::new;
		Usuario rodrigo2 = criadorDeUsuarios2.apply("Rodrigo Turini", 50);
		Usuario paulo2 = criadorDeUsuarios2.apply("Paulo Silveira", 300);

 		// Outros tipos de referencias
		BiFunction<Integer, Integer, Integer> max1 = Math::max;
		ToIntBiFunction<Integer, Integer> max2 = Math::max;
		IntBinaryOperator max3 = Math::max;
	}
}
