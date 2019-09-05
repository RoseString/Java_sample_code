import java.util.Random;

/**
 * This represents a sorting object
 * It implement the Sorting interface
 * 
 * @author Dan Sun
 * @version 1.0
 */
public class Sorting implements SortingInterface {

	@Override
	public <T extends Comparable<T>> void bubblesort(T[] arr) {
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 0; i < arr.length - 1; i++) {
				if (arr[i].compareTo(arr[i + 1]) > 0) {
					swap(arr, i, i + 1);
					swapped = true;
				}
			}
		}
	}

	@Override
	public <T extends Comparable<T>> void insertionsort(T[] arr) {

		for (int i = 0; i < arr.length; i++) {
			int j = i;
			while (j > 0 && arr[j].compareTo(arr[j - 1]) < 0) {
				swap(arr, j - 1, j);
				j--;
			}
		}
	}

	@Override
	public <T extends Comparable<T>> void selectionsort(T[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			int minIndex = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[j].compareTo(arr[minIndex]) < 0) {
					minIndex = j;
				}
			}
			if (minIndex != i) {
				swap(arr, i, minIndex);
			}
		}
	}

	@Override
	public <T extends Comparable<T>> void quicksort(T[] arr, Random r) {
		quicksortHelper(arr, 0, arr.length - 1, r);
	}

	/**
	 * This is a helper method for quicksort method
 	 * 
 	 * @param arr An array.
 	 * @param lowerBound The lower bound of the array.
 	 * @param upperBound The upper bound of the array.
 	 * @param r A random object.
 	 */
	private <T extends Comparable<T>> void quicksortHelper(T[] arr, int lowerBound, int upperBound, Random r) {

		if (arr.length <= 1) {
			return;
		}

		int pivotIndex = r.nextInt(upperBound + 1 - lowerBound) + lowerBound;

		int lowerPointer = lowerBound;
		int upperPointer = upperBound;

		while (lowerPointer < upperPointer) {
			
			while (arr[lowerPointer].compareTo(arr[pivotIndex]) <= 0 && lowerPointer < pivotIndex) {
				lowerPointer++;
			}
			while (arr[upperPointer].compareTo(arr[pivotIndex]) >= 0 && upperPointer > pivotIndex) {
				upperPointer--;
			}
			if (lowerPointer < upperPointer) {
				swap(arr, lowerPointer, upperPointer);
			}
			if (lowerPointer == pivotIndex) {
				pivotIndex = upperPointer;
			} else if (upperPointer == pivotIndex) {
				pivotIndex = lowerPointer;
			}
		}
		
		if (pivotIndex > lowerBound) {
			quicksortHelper(arr, lowerBound, pivotIndex - 1, r);
		} 
		if (pivotIndex < upperBound) {
			quicksortHelper(arr, pivotIndex + 1, upperBound, r);
		}
	}

	@Override
	public <T extends Comparable<T>> T[] mergesort(T[] arr) {
		@SuppressWarnings("unchecked")
			T[] left = (T[]) new Comparable[arr.length / 2];
		@SuppressWarnings("unchecked")
			T[] right = (T[]) new Comparable[arr.length - left.length];

		int middle = arr.length / 2;

		if (arr.length <= 1) {
			return arr;
		}

		for (int i = 0; i < middle; i++) {
			left[i] = arr[i];
		}
		for (int i = middle; i < arr.length; i++) {
			right[i - middle] = arr[i];
		}

		left = mergesort(left);
		right = mergesort(right);

		T[] temp = (T[]) merge(left, right);

		for (int i = 0; i < arr.length; i++) {
			arr[i] = temp[i];
		}

		return arr;
	}

	/**
	 * This is a helper method for mergesort method
	 * It merges two arrays
 	 * 
 	 * @param left The left part of an array.
 	 * @param right The right part of an array.
 	 * @return The merged array.
 	 */
	private <T extends Comparable<T>> T[] merge(T[] left, T[] right) {
		@SuppressWarnings("unchecked")
			T[] result = (T[]) new Comparable[left.length + right.length];

		int leftPointer = 0;
		int rightPointer = 0;
		int j = 0;

		while (leftPointer < left.length && rightPointer < right.length) {
			if (left[leftPointer].compareTo(right[rightPointer]) < 0) {
				result[j] = left[leftPointer];
				leftPointer++;
			} else {
				result[j] = right[rightPointer];
				rightPointer++;
			}
			j++;
		}
		while (leftPointer < left.length) {
			result[j] = left[leftPointer];
			leftPointer++;
			j++;
		}	
		while (rightPointer < right.length) {
			result[j] = right[rightPointer];
			rightPointer++;
			j++;
		}
		return result;
	}

	@Override
	public int[] radixsort(int[] arr) {
		int max = getMax(arr);
		for (int exp = 1; max / exp > 0; exp *= 10) {
			arr = countingSort(arr, exp); //integrate counting sort
		}
		return arr;
	}

	/**
	 * A helper method for radixsort
 	 * It is to get the max value of an array
 	 * @param arr An array.
 	 * @return The max value of an array
 	 */
	private int getMax(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max) {
				max = arr[i];
			}
		}
		return max;
	}

	/**
	 * A helper method for radixsort
 	 * It is to stable sort an array
 	 * @param arr An array.
 	 * @param exp The digit that we are working on.
 	 * @return The sorted array.
 	 */
	private int[] countingSort(int[] arr, int exp) {
		int[] result = new int[arr.length];
		int[] count = new int[10];
		for (int i = 0; i < 10; i++) {
			count[i] = 0;
		}
		for (int i = 0; i < arr.length; i++) {
			count[(arr[i] / exp) % 10]++;
		}
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}
		for (int i = arr.length - 1; i >= 0; i--) {
			count[(arr[i] / exp) % 10]--;
			result[count[(arr[i] / exp) % 10]] = arr[i];
		}
		for (int i = 0; i < arr.length; i++) {
			arr[i] = result[i];
		}

		return arr;
	}

	/**
	 * A helper method to swap two elements of an array
 	 * @param arr An array.
 	 * @param m The first element.
 	 * @param n The second element.
 	 */
	private <T extends Comparable<T>> void swap(T[] arr, int m, int n) {
		T temp = arr[m];
		arr[m] = arr[n];
		arr[n] = temp;
	}
}
