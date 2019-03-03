package sistema;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Produto;

public class Programa {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the file path: ");
		String sourceFileStr = sc.next();

		List<Produto> prods = new ArrayList<>();

		File sourceFile = new File(sourceFileStr);
		String outputDir = sourceFile.getParent();

		String sourceFileStrFolder = sourceFile.getParent();
		boolean success = new File(sourceFileStrFolder + "\\out").mkdirs();

		String outputFile = sourceFileStrFolder + "\\out\\summary.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) {
			String line = br.readLine();
			while (line != null) {
				String[] items = line.split(", ");
				prods.add(new Produto(items[0].toString(), Double.valueOf(items[1]), Integer.parseInt(items[2])));
				line = br.readLine();
			}
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
				for (Produto produto : prods) {
					bw.write(produto.getNome()+", "+String.format("%.2f", produto.getValorTotal()));
					bw.newLine();
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sc.close();

	}

}
