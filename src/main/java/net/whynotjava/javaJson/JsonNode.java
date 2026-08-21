package net.whynotjava.javaJson;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class JsonNode {
    private String type;

    private String value;
    private Map<String, JsonNode> subNodes = new HashMap<>();
    private List<JsonNode> array = new ArrayList<>();

    public JsonNode(){
        type = "object";
    }
    public JsonNode(boolean isArray){
        if(isArray) type = "array";
        else type = "object";
    }
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

    public void set(String name, String val){
        subNodes.put(name, new JsonNode("string", val));
    }
    public void set(String name, JsonNode val){
        subNodes.put(name, val);
    }
    public void set(String val){
        type = "string";
        value = val;
    }
    public void set(int val){
        type = "number";
        value = String.valueOf(val);
    }
    public void set(double val){
        type = "number";
        value = String.valueOf(val);
    }
    public void set(short val){
        type = "number";
        value = String.valueOf(val);
    }
    public void set(long val){
        type = "number";
        value = String.valueOf(val);
    }
    public void set(float val){
        type = "number";
        value = String.valueOf(val);
    }
    public void set(Boolean val){
        if(val == null){
            type = "null";
            value = "null";
        } else if(val == Boolean.TRUE){
            type = "true";
            value = "true";
        } else if(val == Boolean.FALSE){
            type = "false";
            value = "false";
        }
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
    public void getMap(Map<String, JsonNode> subNodes){
        this.subNodes = subNodes;
    }
    public JsonNode getIndex(int i) throws IllegalStateException{
        if(!type.equals("array"))
            throw new IllegalStateException("JsonNode is not of type array.");
        return array.get(i);
    }
    public void setIndex(int i, JsonNode node){
        array.set(i, node);
    }
    public void addIndex(JsonNode node){
        array.add(node);
    }
    public void addIndex(String str){
        array.add(new JsonNode("string", str));
    }
    public void addIndex(int num){
        array.add(new JsonNode("number", String.valueOf(num)));
    }
    public void addIndex(double num){
        array.add(new JsonNode("number", String.valueOf(num)));
    }
    public void addIndex(long num){
        array.add(new JsonNode("number", String.valueOf(num)));
    }
    public void addIndexLiteral(Boolean val){
        if(val == null){
            array.add(new JsonNode("null", "null"));
        } else if(val == Boolean.TRUE){
            array.add(new JsonNode("true", "true"));
        } else if(val == Boolean.FALSE){
            array.add(new JsonNode("false", "false"));
        }        
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
    public boolean getAsBoolean() throws IllegalStateException{
        if(type.equals("true")) return true;
        else if(type.equals("false")) return false;
        throw new IllegalStateException("JsonNode is not of type boolean.");
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

    @Override
    public String toString(){
        switch (type) {
            case "string": return "\"" + value + "\"";
            case "true": return "true";
            case "false": return "false";
            case "null": return "null";
            case "number": return value;
            case "array": return arrayToString();
            case "object": return objectToString();
        }

        throw new IllegalStateException("Unexpected type: " + type);
    }
    private String arrayToString(){
        String out = "[";
        for(int i = 0; i<array.size(); i++){
            out += array.get(i).toString();
            if(i + 1 < array.size()) out += ",";
        }
        out += "]";
        return out;
    }
    private String objectToString(){
        String out = "{";
        for(Map.Entry<String, JsonNode> i : subNodes.entrySet()){
            out += "\"" + i.getKey() + "\":" + i.getValue().toString() + ",";
        }
        out = out.substring(0, out.length()-1) + "}";
        return out;
    }
}
