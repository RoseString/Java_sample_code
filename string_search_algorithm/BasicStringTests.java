import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.Before;

/**
 * Basic String Search Tests
 * CS 1332 Spring 2014
 * Student Edition
 * 
 * @author Jonathan Jemson
 * 
 * @version 1.2
 * --- Changelog ---
 * 1.2 Fix testRabinKarp6() to find 0 matches
 *     Fix testBuildBoyerMooreCharTable() 
 *     to check for length() in * case
 *     
 * 1.1 Initial release to students
 *
 */
public class BasicStringTests
{
    private static final int EXPECTED_BASE = 433;
    private static final int HASH_AB = EXPECTED_BASE * 65 + 66;
    StringSearchInterface searcher;
    @Before
    public void setUp()
    {
	searcher = new StringSearch();
    }
    @Test
    public void testBoyerMoore()
    {
	String text1 = "helloworldmynameisworldphone";
	String pattern1 = "world";
	List<Integer> ind = searcher.boyerMoore(pattern1, text1);
	assertEquals(2, ind.size());
	assertEquals(new Integer(5), ind.get(0));
	assertEquals(new Integer(18), ind.get(1));
    }
    @Test
    public void testBoyerMoore2()
    {
	String text2 = "aaxaxaaaxaxaaaxax";
	String pattern2 = "aaxax";
	List<Integer> ind2 = searcher.boyerMoore(pattern2, text2);
	assertEquals(3, ind2.size());
	assertEquals(new Integer(0), ind2.get(0));
	assertEquals(new Integer(6), ind2.get(1));
	assertEquals(new Integer(12), ind2.get(2));
    }
    @Test
    public void testBoyerMoore3()
    {
	String text2 = "aaaaa";
	String pattern2 = "a";
	List<Integer> ind2 = searcher.boyerMoore(pattern2, text2);
	assertEquals(5, ind2.size());
	assertEquals(new Integer(0), ind2.get(0));
	assertEquals(new Integer(1), ind2.get(1));
	assertEquals(new Integer(2), ind2.get(2));
	assertEquals(new Integer(3), ind2.get(3));
	assertEquals(new Integer(4), ind2.get(4));
    }
    @Test
    public void testBoyerMoore4()
    {
	String text4 = "crush kkyle with aardvarks";
	String pattern4 = "aardvark";
	List<Integer> ind4 = searcher.boyerMoore(pattern4, text4);
	assertEquals(1, ind4.size());
	assertEquals(new Integer(17), ind4.get(0));
    }
    @Test
    public void testBoyerMoore5()
    {
	String text5 = "aaaaabb";
	String pattern5 = "aaa";
	List<Integer> ind = searcher.boyerMoore(pattern5, text5);
	assertEquals(3, ind.size());
	assertEquals(new Integer(0), ind.get(0));
	assertEquals(new Integer(1), ind.get(1));
	assertEquals(new Integer(2), ind.get(2));
    }
    @Test
    public void testBoyerMoore6()
    {
	String text6 = "world";
	String pattern6 = "loremipsumdolorworldsitamet";
	List<Integer> ind = searcher.boyerMoore(pattern6, text6);
	assertEquals(0, ind.size());
    }
    @Test
    public void testBoyerMoore7()
    {
	String text = "crush kkyle with abardvarks";
	String needle = "aba";
	List<Integer> ind = searcher.boyerMoore(needle, text);
	assertEquals(1, ind.size());
	assertEquals(new Integer(17), ind.get(0));
    }
    @Test
    public void testBuildBoyerMooreCharTable()
    {
	String needle = "aardvark";
	int[] m = searcher.buildLastTable(needle);
	for (int i = Character.MIN_VALUE; i <= Character.MAX_VALUE; i++)
	{
	    if (i == 'a')
		assertEquals(2, m[i]);
	    else if (i == 'r')
		assertEquals(1, m[i]);
	    else if (i == 'd')
		assertEquals(4, m[i]);
	    else if (i == 'v')
		assertEquals(3, m[i]);
	    else if (i == 'k')
		assertEquals(1, m[i]);
	    else
		assertEquals(needle.length(), m[i]);
	}
    }
    @Test
    public void testRabinKarp()
    {
	String myText = "helloworldmynameisworldphone";
	String pattern = "world";
	List<Integer> ind = searcher.rabinKarp(pattern, myText);
	assertEquals(2, ind.size());
	assertEquals(new Integer(5), ind.get(0));
	assertEquals(new Integer(18), ind.get(1));
    }
    @Test
    public void testRabinKarp2()
    {
	String text2 = "aaxaxaaaxaxaaaxax";
	String pattern2 = "aaxax";
	List<Integer> ind2 = searcher.rabinKarp(pattern2, text2);
	assertEquals(3, ind2.size());
	assertEquals(new Integer(0), ind2.get(0));
	assertEquals(new Integer(6), ind2.get(1));
	assertEquals(new Integer(12), ind2.get(2));
    }
    @Test
    public void testRabinKarp3()
    {
	String text2 = "aaaaa";
	String pattern2 = "a";
	List<Integer> ind2 = searcher.rabinKarp(pattern2, text2);
	assertEquals(5, ind2.size());
	assertEquals(new Integer(0), ind2.get(0));
	assertEquals(new Integer(1), ind2.get(1));
	assertEquals(new Integer(2), ind2.get(2));
	assertEquals(new Integer(3), ind2.get(3));
	assertEquals(new Integer(4), ind2.get(4));
    }
    @Test
    public void testRabinKarp4()
    {
	String text4 = "crush kkyle with aardvarks";
	String pattern4 = "aardvark";
	List<Integer> ind4 = searcher.rabinKarp(pattern4, text4);
	assertEquals(1, ind4.size());
	assertEquals(new Integer(17), ind4.get(0));
    }
    @Test
    public void testRabinKarp5()
    {
	String text5 = "aaaaabb";
	String pattern5 = "aaa";
	List<Integer> ind = searcher.rabinKarp(pattern5, text5);
	assertEquals(3, ind.size());
	assertEquals(new Integer(0), ind.get(0));
	assertEquals(new Integer(1), ind.get(1));
	assertEquals(new Integer(2), ind.get(2));
    }
    @Test
    public void testRabinKarp6()
    {
	String text6 = "world";
	String pattern6 = "loremipsumdolorworldsitamet";
	List<Integer> ind = searcher.rabinKarp(pattern6, text6);
	assertEquals(0, ind.size());
    }
    @Test
    public void testRabinKarpTrivial()
    {
	String myText = "xhi";
	String pattern = "hi";
	List<Integer> ind = searcher.rabinKarp(pattern, myText);
	assertEquals(1, ind.size());
	assertEquals(new Integer(1), ind.get(0));
    }

    @Test
    public void testHashString()
    {
	assertEquals('A', searcher.generateHash("A"));
	assertEquals(HASH_AB, searcher.generateHash("AB"));
    }
    @Test
    public void testUpdateHash()
    {
	assertEquals('A', searcher.generateHash("A"));
	assertEquals('B', searcher.updateHash(65, 1, 'A', 'B'));
    }

}
