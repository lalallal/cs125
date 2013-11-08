//UIUC CS125 FALL 2013 MP. File: LinkedList.java, CS125 Project: Challenge6-RecursionSee, Version: 2013-11-05T16:03:10-0600.767323587
/**
 * @author alding2
 *
 */
public class LinkedList {
	// Get and Set methods are NOT necessary!

	private LinkedList next; 	
	private final String word;

	/** Constructs this link.
	 * @param word ; a single word (never null).
	 * @param next ; the next item in the chain (null, if there are no more items).
	 */
	public LinkedList(String word, LinkedList next) {
		this.word = word;
		this.next = next;
	}

	/**
	 * Converts the entire linked list into a string representation.
	 */
	public String toString() {
		if (next == null)
			return word;// BASE CASE; no more recursion required

		// Recursive case:
		String restOfString = next.toString(); // Forward Recursion
		return word + ";" + restOfString;
	}

	/**
	 * Returns the number of entries in the linked list.
	 * @return number of entries.
	 */
	public int getCount() {
		if (next == null)
			return 1; // BASE CASE; no more recursion required!

		// Recursive case:
		return 1 + next.getCount(); // Forward recursion
	}
	
	/** Creates a new LinkedList entry at the end of this linked list.
	 * Recursively finds the last entry then adds a new link to the end.
	 * @param word
	 */
	public void append(String word) {		
		if (next == null) {
			next = new LinkedList(word, null);
			return;
		}
		else next.append(word);
	}
	/**
	 * Recursively counts the total number of letters used.
	 * 
	 * @return total number of letters in the words of the linked list
	 */
	public int getLetterCount() {
		if (next == null) return this.word.length();
		else return this.word.length() + next.getLetterCount();
		// returns the total number of letters. word1.length() +
		// word2.length()+...
		// "A" -> "CAT" -> null returns 1 + 3 = 4.
	}

	/**
	 * Recursively searches for and returns the longest word.
	 * @return the longest word i.e. word.length() is maximal.
	 */
	public String getLongestWord() {
		String longest = word;
		if (next == null) return longest;
		String potentialLongest = next.getLongestWord();
		if (potentialLongest.length() > longest.length()) longest = potentialLongest;
		return longest;	
	}

	/** Converts linked list into a sentence (a single string representation).
	* Each word pair is separated by a space.
	* A period (".") is appended after the last word.
	* The last link represents the last word in the sentence.*/
	public String getSentence() {
		if (next == null) return word + ".";
		else return word + " " + next.getSentence();
	}
	
	/**
	 * Converts linked list into a sentence (a single string representation).
	 * Each word pair is separated by a space. A period (".") is appended after
	 * the last word. The last link represents the first word in the sentence
	 * (and vice versa). The partialResult is the partial string constructed
	 * from earlier links. This partialResult is initially an empty string. 
	 */
	public String getReversedSentence(String partialResult) {
		if (next == null) return word + partialResult + ".";	
		else { return next.getReversedSentence(" " + this.word + partialResult); }	
	}
	
	public LinkedList(String[] words) {
		word = words[0];
		for (int i = 1; i < words.length; i++) {
			this.append(words[i]);
		}
	}
	/** Creates a linked list of words from an array of strings.
	 * Each string in the array is a word. */
	public static LinkedList createLinkedList(String[] words) {
		if(words.length == 0) return null;
		return new LinkedList(words);
		// Hint: This is a wrapper method. You'll need to create your
		// own recursive method.
		// Yes this is possible _without_ loops!
	}

	/**
	 * Searches for the following word in the linked list. Hint: use .equals not ==
	 * to compare strings.
	 * 
	 * @param word
	 * @return true if the linked list contains the word (case sensivitive)
	 */
	public boolean contains(String word) {
		if (this.word.equals(word)) return true;
		else if (next == null) return false; 
		else return next.contains(word);
	}

	/** Recursively searches for the given word in the linked list.
	 * If this link matches the given word then return this link.
	 * Otherwise search the next link.
	 * If no matching links are found return null.
	 * @param word the word to search for.
	 * @return The link that contains the search word.
	 */
	public LinkedList find(String input) {
		if (word.equals(input)) return this;
		if (next == null) return null;
		return next.find(input);
	}

	/**
	 * Returns the last most link that has the given word, or returns null if
	 * the word cannot be found.
	 * Hint: Would forward recursion be useful?
	 * @param word the word to search for.
	 * @return the last LinkedList object that represents the given word, or null if it is not found.
	 */
	LinkedList n;
	public LinkedList findLast(String input) {
		if (word.equals(input)) n = this;
		if (next == null) return this;
		return next.find(input);
	}
	
	public LinkedList insert(String string) {
		if (string.charAt(0) <= this.word.charAt(0))
			return new LinkedList(string,this);
		if (next == null) {
			this.next = new LinkedList(string,null);
			return this;
		}
		this.next = next.insert(string);
		return this;
	}

}
