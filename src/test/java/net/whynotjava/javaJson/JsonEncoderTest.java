package net.whynotjava.javaJson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class JsonEncoderTest {
    @Test
    void simpleTest1(){
        JsonNode node = new JsonNode();
        node.set("foo","bar");
        assertEquals("{\"foo\":\"bar\"}", node.toString());
    }
    @Test
    void simpleTest2(){
        JsonNode node = new JsonNode(true);
        node.addIndex(5);
        node.addIndex(4);
        node.addIndex(3);
        node.addIndex(2);
        node.addIndex(1);
        node.addIndex("boom!");
        assertEquals("[5,4,3,2,1,\"boom!\"]", node.toString());
    }
    @Test
    void simpleTest3(){
        JsonNode node = new JsonNode();
        JsonNode node2 = new JsonNode(true);
        node2.addIndex(5);
        node2.addIndexLiteral(null);
        node2.addIndex(-7);
        node.set("fool", node2);
        assertEquals("{\"fool\":[5,null,-7]}", node.toString());
    }
}
