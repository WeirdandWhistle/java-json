package net.whynotjava.javaJson;

import java.util.List;
import java.util.Map;

public class JsonNode {
    private String type;

    private String name;
    private String value;
    private Map<String, JsonNode> subNodes;
    private List<JsonNode> array;

    public JsonNode(String type){
        this.type = type;
    }
    public JsonNode(String type, String name){
        this.type = type;
        this.name = name;
    }
    public JsonNode(String type, String name, String value){
        this.type = type;
        this.name = name;
        this.value = value;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public Map<String, JsonNode> getSubNodes() {
        return subNodes;
    }
    public void setSubNodes(Map<String, JsonNode> subNodes) {
        this.subNodes = subNodes;
    }
    public List<JsonNode> getArray() {
        return array;
    }
    public void setArray(List<JsonNode> array) {
        this.array = array;
    }
}
