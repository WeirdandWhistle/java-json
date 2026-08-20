package net.whynotjava.javaJson;

public class ParsedTokenObject {
    public final String str;
    public final int offset;
    public ParsedTokenObject(String str, int offset){
        this.str = str;
        this.offset = offset;
    }
}
