package br.com.pedrodeveloper.scramble;

import java.awt.AWTException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
	
	public static void main(String[] args) throws AWTException, IOException {
		boolean exit = false;
		Scanner scanner = new Scanner(System.in);
		while (!exit) {
			//Mostra o menu
			System.out.println();
			System.out.println("Solucionador de Anagramas (Scramble)");
			System.out.println();
			System.out.println("Categorias:");
			for (Dictionaries category : Dictionaries.values()) {
				System.out.println(category.ordinal() + " - " + category.getMenuDescription());
			}

			//Selecao de categoria
			System.out.print("Informe a categoria: ");
			String menuOption = scanner.nextLine();
			Integer option = null;
			try {
				option = Integer.parseInt(menuOption);
			} catch (Exception e) {
			}
			if (option == null || option.compareTo(Dictionaries.values().length) >= 0 || option.compareTo(0) < 0) {
				System.out.println("Categoria inválida. Por favor selecione um número contido no menu.");
				continue;
			}
			
			
			Dictionaries dictionary = Dictionaries.values()[option];
			String fileName = dictionary.getFileName();
			
			//Prompta pedindo o nome do arquivo caso tenha sido informdo "Outros"
			if (dictionary.equals(Dictionaries.OTHER)) {
				System.out.print("Informe o nome do arquivo dentro da pasta /dictionary: ");
				fileName = scanner.nextLine();
			}
			
			System.out.print("Insira o anagrama: ");
			String word = scanner.nextLine();
			
			Map<Character, Integer> charCount = countCharacters(word);

			System.out.println();
			System.out.println("####################################");
			System.out.println("Palavras encontradas para o anagrama " + word + ": ");
			System.out.println();
			
			//Lê as linhas do arquivo com a rotina
			try (Stream<String> lines = Files.lines(Paths.get("dictionary/" + fileName))) {
				lines.forEach(line -> {
					Map<Character, Integer> lineCharCount = countCharacters(line);

					if (charCount.equals(lineCharCount))
						System.out.println(line);
				});
			} catch (NoSuchFileException e) {
				System.out.print("Arquivo não encontrado. Coloque um arquivo com o dicionário que você quer usar dentro da pasta /dictionary");
			}

			System.out.println();
			System.out.println("####################################");
			System.out.println();
		}
		scanner.close();
	}

	/**
	 * Conta as letras da palavra informada e devolve um mapa contendo 
	 * as letras e a quantidade de vezes que ela se repete na palavra.<br>
	 * <br>
	 * <b>Por exemplo:</b> a palavra <i>banana</i> retorna o mapa 
	 * <i>{b=1, a=3, n=2}</i>.
	 * @param word	A palavra/anagrama que se deseja contar as letras.
	 * @return	Um mapa contendo as letras como chave e a qtde de vezes 
	 * 			que ela se repete como valor.
	 */
	private static Map<Character, Integer> countCharacters(String word) {
		Map<Character, Integer> charCount = new HashMap<>();
		for (Character character : word.toCharArray()) {
			
			charCount.merge(Character.toLowerCase(character), 1, Integer::sum);
		}
		return charCount;
	}
	
}
