package br.com.hosseinramon.java8;

@FunctionalInterface
interface Validador<T> {
	boolean valida(T t);
}
