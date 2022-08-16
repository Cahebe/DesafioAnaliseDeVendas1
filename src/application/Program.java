package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Sale;

public class Program {
	
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Entre o caminho do arquivo: ");
		String path = sc.nextLine();
		
		//Leitura do arquivo e Instancia��o da classe Sale
		try (BufferedReader br = new BufferedReader(new FileReader(path))){
			List<Sale> list = new ArrayList<>();
			
			String line = br.readLine();
			while (line != null) {
				String[] fields = line.split(",");
				list.add(new Sale(Integer.parseInt(fields[0]), 
						Integer.parseInt(fields[1]), fields[2], 
						Integer.parseInt(fields[3]), Double.parseDouble(fields[4])));
				line = br.readLine();
				}
				
			//Uso de Lambda para resolver o problema
				list.sort((s1, s2) -> s2.averagePrice().compareTo(s1.averagePrice()));
				
				System.out.println("Cinco primeiras vendas de 2016 de maior pre�o m�dio");
				List<String> sellers = list.stream()
				.filter(p -> p.getYear() == 2016)
				.map(p -> p.toString())
				.limit(5)
				.collect(Collectors.toList());
				
				sellers.forEach(System.out::println);
				System.out.println(" ");
				
				       double logan = list.stream()
						.filter(p -> p.getMonth() == 7 | p.getMonth() == 1 && p.getSeller().equals("Logan"))
						.map(p -> p.getTotal())
						.reduce(0.0, (x, y) -> x + y);
				    		    
						
				System.out.println("Valor total vendido pelo vendedor Logan nos meses 1 e 7 = " 
				+ String.format("%.2f", logan));
				
			
		} catch (IOException e) {
			System.out.println("Erro: " + path + " (O sistema n�o pode encontrar o arquivo especificado)");
		}
		
		sc.close();
	}

}
