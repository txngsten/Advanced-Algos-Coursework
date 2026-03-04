
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

public class Dictionary {

    private SortedMap<String, DictionaryData> dictionaryMap;

    public Dictionary() {
        dictionaryMap = new TreeMap<>();
    }

    /**
     * Extends this dictionary by adding the new word
     * identified with <code>word</code> and the data <code>data</code>.
     * 
     * @param word the word to add to the <code>Dictionary</code>.
     * @param data the data about the word s
     */
    public void insert(String word, DictionaryData data) {
        dictionaryMap.put(word.toLowerCase(), data);
    }

    /**
     * Removes the word identified by <code>word</code> from this Dictionary.
     * 
     * @param word the word to remove to the <code>Dictionary</code>.
     * @return
     */
    public DictionaryData remove(String word) {
        DictionaryData value = dictionaryMap.get(word.toLowerCase());
        dictionaryMap.remove(word.toLowerCase());

        return value;
    }

    /**
     * Look up the dictionary entry for a particular word
     *
     * @param word the particular word to look up.
     * @return the data associated with the word identified by <code>word</code>.
     */
    public DictionaryData lookup(String word) {
        return dictionaryMap.get(word.toLowerCase());
    }

    /**
     * Check to see whether a word is in the dictionary or not (returns true/false)
     * 
     * @param word the word to
     * @return <code>true</code> if in this <code>Dictionary</code>,
     *         <code>false</code> otherwise.
     */
    public boolean contains(String word) {
        return dictionaryMap.containsKey(word.toLowerCase());
    }

    /**
     * Builder method for the <code>Dictionary</code> class that builds
     * a dictionary from the given file name.
     * It is expected that each entry is on a seperate line in the form
     * 
     * <pre>
     *     862 buddy 2743
     * </pre>
     * 
     * where 862 is the rank, buddy is the word, and 2743 is the frequency.
     *
     * @param fileName the file to load the dictionary data from
     * @return the created <code>Dictionary</code> or <code>null</code> on error.
     */
    @SuppressWarnings("resource")
    public static Dictionary readInDictionary(String fileName) {
        Dictionary d = new Dictionary();
        Scanner fileScanner;

        try {
            // use a FileInputStream to ensure correct reading end-of-file
            fileScanner = new Scanner(new FileInputStream("data" + File.separator + fileName));

            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                // System.out.println("nextLine: " + nextLine); uncomment if you want to see
                // what is read in
                DictionaryData data = new DictionaryData(nextLine);
                String[] key = nextLine.split(" ");
                d.insert(key[1].toLowerCase(), data);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("could not find the file " + fileName + " in the data directory!");
            return null;
        }

        return d;
    }

    /**
     * Read in a file and list all the words not found in this dictionary.
     * 
     * @param fileName the file to read and check.
     * @return List of words not found in this <code>Dictionary</code>.
     */
    @SuppressWarnings("resource")
    public List<String> spellCheck(String fileName) {
        List<String[]> allWords = new ArrayList<>();
        Scanner fileScanner;

        try {
            // use a FileInputStream to ensure correct reading end-of-file
            fileScanner = new Scanner(new FileInputStream("data" + File.separator + fileName));

            while (fileScanner.hasNextLine()) {
                String nextLine = fileScanner.nextLine();
                String[] line = nextLine.split(" ");

                allWords.add(line);
            }

        } catch (FileNotFoundException ex) {
            System.out.println("could not find the file " + fileName + " in the data directory!");
            return null;
        }

        List<String> notInDict = new ArrayList<>();
        for (String[] wordList : allWords) {
            for (String word : wordList) {
                if (!contains(word) && !word.isEmpty()) {
                    notInDict.add(word);
                }
            }
        }

        return notInDict;
    }

    /**
     * Creates a list of the words in this dictionary by alphabetical order.
     * 
     * @return the list of alphabetical sorted dictionary words.
     */
    public List<DictionaryData> alphabeticalList() {
        List<DictionaryData> alphabeticalOrder = new ArrayList<>();
        for (Map.Entry<String, DictionaryData> entry : dictionaryMap.entrySet()) {
            alphabeticalOrder.add(entry.getValue());
        }

        return alphabeticalOrder;
    }

    /**
     * Creates a list of the words in this dictionary by ascending order of
     * frequency (those of the same frequency should be
     * then ordered in reverse alphabetical order).
     * 
     * @return the list of frequency sorted dictionary words.
     */
    public List<DictionaryData> frequencyOrderedList() {
        List<DictionaryData> frequencyOrder = new ArrayList<>();

        for (Map.Entry<String, DictionaryData> entry : dictionaryMap.entrySet()) {
            frequencyOrder.add(entry.getValue());
        }

        frequencyOrder.sort(Comparator.comparing(DictionaryData::getFreq)
                .thenComparing(DictionaryData::getWord,
                        Comparator.reverseOrder()));

        return frequencyOrder;
    }

}
