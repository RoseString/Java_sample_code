import java.util.List;
import java.util.ArrayList;

/**
 * A concrete String Search class that includes Rabin-Karp and
 * Boyer-Moore.
 *
 * @author Dan Sun
 * @version 1.0
 */
public class StringSearch implements StringSearchInterface {

	@Override
	public List<Integer> boyerMoore(String needle, String haystack) {
		if (needle == null | haystack == null)
			throw new IllegalArgumentException();
		List<Integer> positions = new ArrayList<Integer>(10);
		if (needle.length() > haystack.length() || needle.equals("") || haystack.equals(""))
			return positions;
		int[] map = buildLastTable(needle);
		int t = needle.length() - 1; //initial text position
		int p = needle.length() - 1; //initial needle position

		while (t < haystack.length()) {
			int prevPosition = t;
			boolean equal = true; //assume aligned text and pattern are equal
			int skip = 0;
			char maxChar = '\u0000';
			char minChar = '\u0000';
			while (equal && p >= 0) {
				if (!(needle.charAt(p) == haystack.charAt(t))) {
					equal = false;
					if (map[haystack.charAt(t)] != needle.length()) { //unmatched character is in the pattern
						skip = map[haystack.charAt(prevPosition)];  //skip map[lastMatch]
					} else { //unmatched character is not in the pattern
						skip = p + 1;
					}
				} else {
					if (maxChar == '\u0000') {
						maxChar = needle.charAt(p);
					}
					if (minChar == '\u0000') {
						minChar = needle.charAt(p);
					}
					if (map[needle.charAt(p)] > map[maxChar]) {
						maxChar = needle.charAt(p);
					} else if (map[needle.charAt(p)] < map[maxChar]) {
						minChar = needle.charAt(p);
					}
					t--;
					p--;
				}
			}
			if (equal) {
				if (maxChar == minChar) {
					skip = 1;
				} else {
					skip = map[minChar] + map[maxChar];
				}
				positions.add(prevPosition - needle.length() + 1);
			}	
			t = prevPosition + skip;
			p = needle.length() - 1;
		}
		return positions;
 	}

 	@Override
	public int[] buildLastTable(String needle) {
		int[] map = new int[Character.MAX_VALUE + 1];
		for (int i = 0; i < map.length; i++) {
			map[i] = needle.length();
		}
		for (int i = 0; i < needle.length(); i++) {
			map[needle.charAt(i)] = Math.max(needle.length() - 1 - i, 1);
 		}
		return map;
	}

	public static final int BASE = 433;

	@Override
	public int generateHash(String current) {
		int hash = 0;
	 	for (int i = 0; i < current.length(); i++) {
	 		hash += current.charAt(i) * pow(BASE, current.length() - 1 - i);
	 	}
	 	return hash;
	}

	@Override
	public int updateHash(int oldHash, int length, char oldChar, char newChar) {
		return (int) (BASE * (oldHash - oldChar * pow(BASE, length - 1)) + newChar);
	}

	@Override
	public List<Integer> rabinKarp(String needle, String haystack) {
		if (needle == null | haystack == null)
			throw new IllegalArgumentException();
	  	int oldHash = 0;
	  	char oldChar = '\u0000';
	  	int needleHash = generateHash(needle);
	  	List<Integer> positions = new ArrayList<Integer>(10);
	  	if (needle.length() > haystack.length() || needle.equals("") || haystack.equals(""))
			return positions;
	  	for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
	  		int newHash = 0;
	  		if (i == 0) {
	  			newHash = generateHash(haystack.substring(i, needle.length()));
	  		} else {
	  			char newChar = haystack.charAt(i + needle.length() - 1);
	  			newHash = updateHash(oldHash, needle.length(), oldChar, newChar);
	  		}
	  		if (newHash == needleHash) {
	  			boolean equal = true;
	  			for (int j = 0; j < needle.length() && equal; j++) {
	  				if (haystack.charAt(i + j) != needle.charAt(j))
	  					equal = false;
	  			}
	  			if (equal)
	  				positions.add(i);
	  		}
	  		oldChar = haystack.charAt(i);
	  		oldHash = newHash;
	  	}
	  	return positions;
	  }

	/**
 	 * A private power method
 	 *
 	 * @param base The base
 	 * @param exp The exponent
 	 * @return return the result
 	 */
	private int pow(int base, int exp) {
	 	int value = 1;
	 	for (int i = exp; i > 0; i--) {
	 		value *= base;
	 	}
	 	return value;
	}
}
