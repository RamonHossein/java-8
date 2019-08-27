//***********************************************************************
//
//	Capítulo 3 - Interfaces Funcionais
//
//**********************************************************************

package br.com.hosseinramon.java8;

public class Capitulo03 {

	public static void main(String ... args) {

 		// Imprimir valores de 0 até 1000 - Versão 1
		Runnable r1 = new Runnable() {
			public void run() {
				for(int i = 0; i <= 1000; i++) {
					System.out.println(i);
				}
			}
		};

 		new Thread(r1).start();

 		// Imprimir valores de 0 até 1000 - Versão 2
		Runnable r2 = () -> {
			for(int i = 0; i <= 1000; i++) {
				System.out.println(i);
			}
		};

 		new Thread(r2).start();

 		// Imprimir valores de 0 até 1000 - Versão 3
		new Thread(() -> {
			for(int i = 0; i <= 1000; i++) {
				System.out.println(i);
			}
		}).start();

 		// Método responsável por validar CPF - Versão 1
		Validador<String> validatorCPF1 = new Validador<String>() {
			public boolean valida(String valor) {
				return valor.matches("[0-9]{5}-[0-9]{3}");
			}
		};

 		// Método responsável por validar CPF - Versão 2
		Validador<String> validatorCPF2 = valor -> {
			return valor.matches("[0-9]{5}-[0-9]{3}");
		};

 		// Método responsável por validar CPF - Versão 3
		Validador<String> validatorCPF3 = valor -> valor.matches("[0-9]{5}-[0-9]{3}");

 		// O que é o objeto retornado pela expressão?
		Runnable obj = () -> {
			System.out.println("O que sou eu? Que Lambda?");
		};

 		System.out.println(obj);
		System.out.println(obj.getClass());
	}
}
