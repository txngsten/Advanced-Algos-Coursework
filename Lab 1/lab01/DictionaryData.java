
public class DictionaryData {

    /**
     * Creates a new DictionaryData object based upon the the String line that
     * contains the data about the new data item.
     *
     * @param line the data about the new data item
     */
    private String word;

    public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	private int freq;

    public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}
    
    private int rank;

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public DictionaryData(String line) {
        String[] ops = line.split(" ");
        
        rank = Integer.parseInt(ops[0]);
        word = ops[1];
        freq = Integer.parseInt(ops[2]);
    }

    /**
     * A string representation of the DataDictionary object. For example
     *
     * "orange: frequency = 518"
     *
     * @return a string representation of the DataDictionary object
     */
    @Override
    public String toString() {
        return word + ": frequency = " + freq;
    }
}
