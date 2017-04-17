package speechanalyser;

import codeoperations.CodeAction;
import codeoperations.Statement;


import java.io.*;
import java.util.HashMap;

/**
 * Created by mb on 17/04/2017.
 */
public class Trie {
    private class TrieNode {
        String key;
        Statement statement;
        HashMap<String,TrieNode> children = new HashMap();
    }

    TrieNode root = new TrieNode();

    public void addNode(String key, String statement) {
        TrieNode curr = this.root;
        String[] split = key.split(" ");

        for(int i = 0; i < split.length; i++) {
            String word = split[i];

            if(!curr.children.containsKey(word)) {
                curr.children.put(word, new TrieNode());
            }

            curr = curr.children.get(word);
        }

        curr.statement = Statement.valueOf(statement);
    }

    public Statement getStatement(String key) {
        TrieNode curr = this.root;
        String[] split = key.split(" ");

        for(int i = 0; i < split.length; i++) {
            String word = split[i];

            if(!curr.children.containsKey(word)) {
                return null;
            }

            curr = curr.children.get(word);
        }

        return curr.statement;
    }

    public static void main(String[] args) throws IOException {
        Trie t = new Trie();

        File file = new File("speech2code/src/main/resources/analyser/map.txt");

        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        String line = null;

        while((line = br.readLine()) != null) {
            String[] split = line.split("~");
            String key = split[0];
            String statement = split[1];

            t.addNode(key,statement);
        }

    }

}
