package net.whynotjava.javaJson;

import java.util.ArrayList;
import java.util.List;

class Tokenizer {
    static List<String> tokenize(String str){
        List<String> tokens = new ArrayList<>();
        str = str.trim();
        while(str.length() > 0){
            char c = str.charAt(0);
            switch (c) {
                case '{':
                    tokens.add(String.valueOf(c));
                    str = str.substring(1);
                    break;
                case '}':
                    tokens.add(String.valueOf(c));
                    str = str.substring(1);
                    break;
                case '[':
                    tokens.add(String.valueOf(c));
                    str = str.substring(1);
                    break;
                case ']':
                    tokens.add(String.valueOf(c));
                    str = str.substring(1);
                    break;
                case ',':
                    tokens.add(String.valueOf(c));
                    str = str.substring(1);
                    break;
                case ':':
                    tokens.add(String.valueOf(c));
                    str = str.substring(1);
                    break;
                case '"':
                    ParsedTokenObject psString = parseString(str);
                    tokens.add(psString.str);
                    str = str.substring(psString.offset);
                    break;            
                default:
                    ParsedTokenObject psLiteral = parseLiterals(str);
                    tokens.add(psLiteral.str);
                    str = str.substring(psLiteral.offset);
                    break;
            }
            str = str.trim();
        }
        
        return tokens;
    }
    private static ParsedTokenObject parseLiterals(String str){
        String out = "";
        char trueSeperators[] = {',','{','}','[',']',':',0x20, 0x09, 0x0A, 0x0D};
        int offset = 0;
        char curChar;
        while(true){
            curChar = str.charAt(offset);
            offset++;
            if(contains(trueSeperators, curChar))
                break;
            out += String.valueOf(curChar);
        }
        return new ParsedTokenObject(out, offset-1);
    }
    private static boolean contains(char arr[], char v){
        for(int i = 0; i<arr.length; i++){
            if(arr[i] == v)
                return true;
        }
        return false;
    }
    private static ParsedTokenObject parseString(String str){
        String out = "\"";
        int offset = 1;
        char prevChar = '"';
        char curChar;
        while(true){
            curChar = str.charAt(offset);
            offset++;
            out += String.valueOf(curChar);

            if(curChar == '"' && prevChar != '\\')
                break;

            prevChar = curChar;
        }
        return new ParsedTokenObject(out, offset);
    }
}
