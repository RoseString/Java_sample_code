import java.util.List;
import java.util.Scanner;

/**
 * A driver class for StringSearch
 *
 * @author Dan Sun
 * @version 1.0
 */
public class Driver {

	/**
 	 * This is the main method
 	 * @param args Arguments. Not used in this program
 	 */
	public static void main(String[] args) {
		StringSearch ss = new StringSearch();
		String text = "";
		String pattern = "";
		boolean quit = false;
		Scanner keyboard;

		while (!quit) {
			System.out.print("Enter some text (empty string to quit): ");
			keyboard = new Scanner(System.in);
			text = keyboard.nextLine();
			if (text.equals("")) {
				quit = true;	
			}
			if (!quit) {
				while (pattern.equals("")) {
					System.out.print("Enter the pattern: ");

					keyboard = new Scanner(System.in);
					pattern = keyboard.nextLine();
					System.out.println();
				}
				System.out.println("Booyer-Moore:");
				long t0 = System.nanoTime();
				List<Integer> boyerMooreIndices = ss.boyerMoore(pattern, text);
				long t1 = System.nanoTime();
				double seconds = (t1 - t0) / 1000000000.0;

				System.out.print("Text found at indices: ");
				for (int index: boyerMooreIndices) {
					System.out.print(index + " ");
				}
				System.out.println();
				System.out.println("Took " + seconds + " seconds to find all occurrences");
				System.out.println();

				System.out.println("Rabin-Karp:");
				t0 = System.nanoTime();
				List<Integer> rabinKarpIndices = ss.rabinKarp(pattern, text);
				t1 = System.nanoTime();
				seconds = (t1 - t0) / 1000000000.0;

				System.out.print("Text found at indices: ");
				for (int index: rabinKarpIndices) {
					System.out.print(index + " ");
				}
				System.out.println();
				System.out.println("Took " + seconds + " seconds to find all occurrences");
				System.out.println();
			}
			text = "";
			pattern = "";
		}
		System.out.println("Bye-bye!");
	}
}