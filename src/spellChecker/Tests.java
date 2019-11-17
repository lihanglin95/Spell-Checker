package spellChecker;

import org.junit.Assert;
import org.junit.Test;

public class Tests {
    @Test
    public void test() {
        Trie trie = new Trie();
        trie.insert("a");
        trie.insert("apple");
        Assert.assertEquals(true, trie.find("a"));
        Assert.assertEquals(true,trie.find("apple"));
    }
    @Test
    public void testSuggest(){
        Trie trie = new Trie();
        trie.insert("a");
        Assert.assertEquals("a",trie.suggest("df")[0]);

    }
}