package net.whynotjava.javaJson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

public class Main {
    public static void main(String[] args){
        System.out.println("Helo world!");
        
        try (BufferedReader read = new BufferedReader(new FileReader(new File("test1.json")))){
            String tmp = read.readAllAsString();
            List<String> tokens = Tokenizer.tokenize(tmp);

            // for(int i = 0; i<tokens.size(); i++){
            //     System.out.println("token: '"+tokens.get(i)+"'");
            // }

            JsonNode node = Parser.parse(tokens);

            System.out.println(node.getArray());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}