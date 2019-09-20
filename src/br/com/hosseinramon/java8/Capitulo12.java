//***********************************************************************
//
//	Capítulo 12 - Mais Java 8 com Reflection, JVM, APIs e Limitações
//
//***********************************************************************

package br.com.hosseinramon.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Capitulo12 {

	public static void main(String ... args) {
		
 		Usuario user1 = new Usuario("Paulo Silveira", 150);
		Usuario user2 = new Usuario("Rodrigo Turini", 120);
		Usuario user3 = new Usuario("Guilherme Silveira", 190);

 		List<Usuario> usuarios = Arrays.asList(user1, user2, user3);
 		
		// Inicia lista sem o perador diamante
		List<Usuario> lista1 = new ArrayList<Usuario>();
		
		// Inicia lista com o operador diamente
		List<Usuario> lista2 = new ArrayList<>();
		
		// Necessidade de uma tipagem correta
		usuarios.sort(Comparator.comparingInt((Usuario u) -> u.getPontos())
				.thenComparing(u -> u.getNome()));
	}
}
