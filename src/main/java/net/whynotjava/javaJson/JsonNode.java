package net.whynotjava.javaJson;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class JsonNode {
    private String type;

    private String value;
    private Map<String, JsonNode> subNodes;
    private List<JsonNode> array;

    public JsonNode(String type){
        this.type = type;
    }
    public JsonNode(String type, String value){
        this.type = type;
        this.value = value;
    }
    public JsonNode(String type, Map<String, JsonNode> subNodes){
        this.type = type;
        this.subNodes = subNodes;
    }
    public JsonNode(String type, List<JsonNode> array){
        this.type = type;
        this.array = array;
    }

    public String getType(){
        return type;
    }
    public String getValueAsString(){
        return value;
    }
    public JsonNode get(String name) throws IllegalStateException{
        if(!type.equals("object"))
            throw new IllegalStateException("JsonNode is not of type object.");
        return subNodes.get(name);
    }
    public Map<String, JsonNode> getMap() throws IllegalStateException{
        if(!type.equals("object"))
            throw new IllegalStateException("JsonNode is not of type object.");
        return subNodes;
    }
    public JsonNode getIndex(int i) throws IllegalStateException{
        if(!type.equals("array"))
            throw new IllegalStateException("JsonNode is not of type array.");
        return array.get(i);
    }
    public List<JsonNode> getArray() throws IllegalStateException{
        if(!type.equals("array"))
            throw new IllegalStateException("JsonNode is not of type array.");
        return array;
    }
    public String getAsString() throws IllegalStateException{
        if(!type.equals("string"))
            throw new IllegalStateException("JsonNode is not of type string.");
        return value;
    }
    public Boolean getAsLiteral() throws IllegalStateException{
        switch (type) {
            case "null":
                return null;
            case "true":
                return true;
            case "false":
                return false;
        }
        throw new IllegalStateException("JsonNode is not of type literal.");
    }
    public BigDecimal getAsBigDecimal() throws IllegalStateException{
        if(!type.equals("number"))
            throw new IllegalStateException("JsonNode is not of type 'number'. Instead it is of type: " + type);
        return new BigDecimal(value);
    }
    public double getAsDouble() throws IllegalStateException{
        BigDecimal v = getAsBigDecimal();
        return v.doubleValue();
    }
    public double getAsFloat() throws IllegalStateException{
        BigDecimal v = getAsBigDecimal();
        return v.floatValue();
    }
    public BigInteger getAsBigInteger() throws IllegalStateException{
        if(!type.equals("number"))
            throw new IllegalStateException("JsonNode is not of type 'number'. Instead it is of type: " + type);
        return new BigInteger(value);
    }
    public int getAsInt() throws IllegalStateException{
        BigInteger v = getAsBigInteger();
        return v.intValue();
    }
    public short getAsShort() throws IllegalStateException{
        BigInteger v = getAsBigInteger();
        return v.shortValue();
    }
    public long getAsLong() throws IllegalStateException{
        BigInteger v = getAsBigInteger();
        return v.longValue();
    }
}
