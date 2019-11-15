package spellChecker;

public interface Storage {

	public void insert(String word);
	public boolean find(String word);
	public String[] suggest(String word);
}
