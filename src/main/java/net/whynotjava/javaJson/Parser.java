package net.whynotjava.javaJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public static JsonNode parse(String str){
        return parse(Tokenizer.tokenize(str));
    }
    private static JsonNode parse(List<String> tokens){
        String type = getTokenType(tokens.get(0));
        // System.out.println(tokens.get(0) + " is of type " + type);
        JsonNode node = null;
        switch (type) {
            case "string":
                node = new JsonNode(type, getUnquotedString(tokens.get(0)));
                tokens.remove(0);
                break;
            case "true":
            case "false":
            case "null":
            case "number":
                node = new JsonNode(type, tokens.get(0));
                tokens.remove(0);
                break;
            case "object":
                tokens.remove(0);
                node = parseObject(tokens);
                break;
            case "array":
                tokens.remove(0);
                node = parseArray(tokens);
                break;
            default:
                throw new RuntimeException("Can NOT parse type: " + type);
        }
        return node;
    }
    private static JsonNode parseObject(List<String> tokens){
        Map<String, JsonNode> subNodes = new HashMap<>();
        while(true){
            String name = getUnquotedString(tokens.get(0));
            tokens.remove(0);

            // expect :
            if(!tokens.get(0).equals(":"))
                throw new RuntimeException("Object Parser expected ':' (colon). instead got: "+tokens.get(0));
            tokens.remove(0);
            
            JsonNode node = parse(tokens);
            subNodes.put(name, node);

            if(tokens.get(0).equals("}")){
                tokens.remove(0);
                break;
            } else if(tokens.get(0).equals(",")){
                tokens.remove(0);
            } else {
                throw new RuntimeException("Unexpected token, must be either closing '}' or extend ','. Instead got: " + tokens.get(0));
            }
        }

        return new JsonNode("object", subNodes);
    }
    private static JsonNode parseArray(List<String> tokens){
        List<JsonNode> array = new ArrayList<>();
        while(true){
            JsonNode node = parse(tokens);
            array.add(node);

            if(tokens.get(0).equals("]")){
                tokens.remove(0);
                break;
            } else if(tokens.get(0).equals(",")){
                tokens.remove(0);
            } else {
                throw new RuntimeException("Unexpected token, must be either closing ']' or extend ','. Instead got: " + tokens.get(0));
            }
        }

        return new JsonNode("array", array);
    }
    private static String getTokenType(String token){
        String type = null;
        if(token.equals("{"))          type = "object";
        else if(token.equals("["))     type = "array";
        else if(token.startsWith("\""))type = "string";
        else if(token.equals("true"))  type = "true";
        else if(token.equals("false")) type = "false";
        else if(token.equals("null"))  type = "null";
        else if(token.startsWith("-")) type = "number";
        else if(Character.isDigit(token.charAt(0)))  type = "number";
        else throw new RuntimeException("Token is an unknown type. Token: " + token);
        return type;
    }
    private static String getUnquotedString(String name){
        return name.substring(1, name.length() - 1);
    }
}
