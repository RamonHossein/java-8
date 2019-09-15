//***********************************************************************
//
//	Capítulo 10 - Chega de Calendar! Nova API de Datas
//
//***********************************************************************

package br.com.hosseinramon.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Locale;

public class Capitulo10 {

	public static void main(String ... args) {
		
		// Adiciona um mês - versão 1
		Calendar mesQueVem1 = Calendar.getInstance();
		mesQueVem1.add(Calendar.MONTH, 1);
		
		// Adiciona um mês - versão 2
		LocalDate mesQueVem2 = LocalDate.now().plusMonths(1);
		
		// Retira um ano
		LocalDate anoPassado = LocalDate.now().minusYears(1);
		
		// Imprimi data e horário atual
		LocalDateTime agora = LocalDateTime.now();
		
		System.out.println(agora);
		
		// Constrói um LocalDateTime a partir de um LocalDate
		LocalDateTime hojeAoMeioDia = LocalDate.now().atTime(12, 0);
		
		// Constrói um LocalDateTime como LocalDate + LocalTime
		LocalTime agora2 = LocalTime.now();
		LocalDate hoje = LocalDate.now();
		LocalDateTime dataEHora = hoje.atTime(agora2);
		
		// Adiciona um timeZone
		ZonedDateTime dataComHoraETimezone = dataEHora.atZone(
				ZoneId.of("America/Sao_Paulo"));
		
		// Converte ZoneDateTime para LocalDateTime
		LocalDateTime semTimeZone = dataComHoraETimezone.toLocalDateTime();
		
		// Usando o método factory 'of' para a construção de novas instâncias
		LocalDate date = LocalDate.of(2014, 12, 25);
		
		LocalDateTime dateTime = LocalDateTime.of(2014, 12, 25, 10, 30);
		
		// Adiciona valores com o método 'with' (set)
		LocalDate dataDoPassado = LocalDate.now().withYear(1988);
		
		// recupera valores (get)
		System.out.println(dataDoPassado.getYear());
		
		// Comparando datas com o método 'is'
		LocalDate hoje2 = LocalDate.now();
		LocalDate amanha = LocalDate.now().plusDays(1);
		
		System.out.println(hoje.isBefore(amanha));
		System.out.println(hoje.isAfter(amanha));
		System.out.println(hoje.isEqual(amanha));
		
		// Comparando horários iguais com timeZones diferentes
		ZonedDateTime tokyo = ZonedDateTime
				.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("Asia/Tokyo"));
		ZonedDateTime saoPaulo = ZonedDateTime
				.of(2011, 5, 2, 10, 30, 0, 0, ZoneId.of("America/Sao_Paulo"));
		
		System.out.println(tokyo.isEqual(saoPaulo));
		
		// Imprimi o dia do mês atual
		System.out.println("hoje é dia: " + MonthDay.now().getDayOfMonth());
		
		// Imprimi utilizando Enums no lugar das constantes (Calendar)
		System.out.println(LocalDate.of(2014, 12, 25));
		System.out.println(LocalDate.of(2014, Month.DECEMBER, 25));
		
		// Imprimi utilizando os métodos auxiliares dos Enums 
		System.out.println(Month.DECEMBER.firstMonthOfQuarter());
		System.out.println(Month.DECEMBER.plus(2));
		System.out.println(Month.DECEMBER.minus(1));
		
		// Imprimi o nome do mês formatado
		Locale pt = new Locale("pt");
		
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.FULL, pt));
		System.out.println(Month.DECEMBER.getDisplayName(TextStyle.SHORT, pt));
		
		// Formatando de datas - versão 1
		LocalDateTime agora3 = LocalDateTime.now();
		String resultado1 = agora3.format(DateTimeFormatter.ISO_LOCAL_TIME);
		
		// Formatando de datas - versão 2
		LocalDateTime agora4 = LocalDateTime.now();
		agora4.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		// Converte de String para LocalDate
		LocalDateTime agora5 = LocalDateTime.now();
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		String resultado2 = agora5.format(formatador);
		LocalDate agoraEmData = LocalDate.parse(resultado2, formatador);
		
		// Data e horário invalido
		LocalDate.of(2014, Month.FEBRUARY, 30);
		
		LocalDateTime horaInvalida = LocalDate.now().atTime(25, 0);
		
		// Diferença de dias utilizando Calendar
		Calendar agora6 = Calendar.getInstance();
		
		Calendar outraData1 = Calendar.getInstance();
		outraData1.set(1988, Calendar.JANUARY, 25);
		
		long diferenca = agora6.getTimeInMillis() - outraData1.getTimeInMillis();
		
		long milisSegundosDeUmDia = 1000 * 60 * 60 * 24;
		
		long dias1 = diferenca / milisSegundosDeUmDia;
		
		// Diferença de dias meses e anos utilizando ChronoUnit
		LocalDate agora7 = LocalDate.now();
		
		LocalDate outraData2 = LocalDate.of(1989, Month.JANUARY, 25);
		
		long dias2 = ChronoUnit.DAYS.between(outraData2, agora7);
		long meses = ChronoUnit.MONTHS.between(outraData2, agora7);
		long anos = ChronoUnit.YEARS.between(outraData2, agora7);
		
		System.out.printf("%s dias, %s meses e %s anos\n", dias2, meses, anos);
		
		// Período entre duas datas
		LocalDate agora8 = LocalDate.now();
		LocalDate outraData3 = LocalDate.of(1989, Month.JANUARY, 25);
		
		Period periodo1 = Period.between(outraData3, agora8);
		
		System.out.printf("%s dias, %s meses e %s anos\n", 
				periodo1.getDays(), periodo1.getMonths(), periodo1.getYears());
		
		// Invertendo períodos negativos
		Period periodo2 = Period.between(agora8, outraData3);
		
		if(periodo2.isNegative()) {
			periodo2 = periodo2.negated();
		}

		System.out.printf("%s dias, %s meses e %s anos\n", 
				periodo2.getDays(), periodo2.getMonths(), periodo2.getYears());
	}
}
