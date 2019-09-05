import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * A driver class to test Sorting class
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

		int size = -1;
		boolean again = true;

		while (size != 0) {
			Scanner keyboard = new Scanner(System.in);
			System.out.println();
			System.out.println("Please enter the size of the array you would like to sort or enter 0 to exit: ");
			try {
				size = keyboard.nextInt();
				again = false;
			} catch (InputMismatchException e) {
				System.out.println("Please enter an integer! Try again!");
				again = true;
			}
			if (!again && size != 0) {			
				Integer[] arr = new Integer[size];
				Random rand = new Random();
				Sorting s = new Sorting();

				System.out.print("The unsorted array is: ");
				for (int i = 0; i < size; i++) {
					arr[i] = rand.nextInt(1000001);
					System.out.print(arr[i] + " ");
				}
				System.out.println();

				//bubblesort	
				Integer[] temp = new Integer[size];
				for (int i = 0; i < size; i++) {
					temp[i] = arr[i];
				}
				long t0 = System.nanoTime();
				s.<Integer>bubblesort(temp);
				long t1 = System.nanoTime();
				long t = t1 - t0;
				double seconds = t / 1000000000.0;
				System.out.print("The sorted array is: ");
				for (int i = 0; i < size; i++) {
					System.out.print(temp[i] + " ");
				}
				System.out.println();
				System.out.println();

				System.out.print("Bubblesort: ");
				System.out.println(seconds + " seconds");

				//insertionsort
				System.out.print("Insertionsort: ");
				for (int i = 0; i < size; i++) {
					temp[i] = arr[i];
				}
				t0 = System.nanoTime();
				s.<Integer>insertionsort(temp);
				t1 = System.nanoTime();
				t = t1 - t0;
				seconds = t / 1000000000.0;
				System.out.println(seconds + " seconds");

				//selectionsort
				System.out.print("Selectionsort: ");
				for (int i = 0; i < size; i++) {
					temp[i] = arr[i];
				}
				t0 = System.nanoTime();
				s.<Integer>selectionsort(temp);
				t1 = System.nanoTime();
				t = t1 - t0;
				seconds = t / 1000000000.0;
				System.out.println(seconds + " seconds");

				//quicksort
				System.out.print("Quicksort: ");
				for (int i = 0; i < size; i++) {
					temp[i] = arr[i];
				}
				t0 = System.nanoTime();
				s.<Integer>quicksort(temp, rand);
				t1 = System.nanoTime();
				t = t1 - t0;
				seconds = t / 1000000000.0;
				System.out.println(seconds + " seconds");

				//mergesort
				System.out.print("Mergesort: ");
				for (int i = 0; i < size; i++) {
					temp[i] = arr[i];
				}
				t0 = System.nanoTime();
				temp = s.<Integer>mergesort(temp);
				t1 = System.nanoTime();
				t = t1 - t0;
				seconds = t / 1000000000.0;
				System.out.println(seconds + " seconds");

				//radixsort
				System.out.print("Radixsort: ");
				int[] radixTemp = new int[size];
				for (int i = 0; i < size; i++) {
					radixTemp[i] = (int) arr[i];
				}
				t0 = System.nanoTime();
				radixTemp = s.radixsort(radixTemp);
				t1 = System.nanoTime();
				t = t1 - t0;
				seconds = t / 1000000000.0;
				System.out.println(seconds + " seconds");
			}
		}
	}
}
