package net.whynotjava.javaJson;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class JsonParserTest {
    @Test
    void simpleTest1(){
        String jsonString = """
{
    "name":"value"
}
                """;
        
        try {
            JsonNode node = Parser.parse(jsonString);
            assertEquals("value", node.get("name").getAsString());
        } catch (Exception e) {
            assertEquals(true, e.getMessage());
        }
    }
    @Test
    void simpleTest2(){
        String jsonString = """
{
    "foo":-42
}
                """;
        
        try {
            JsonNode node = Parser.parse(jsonString);
            assertEquals(-42, node.get("foo").getAsInt());
        } catch (Exception e) {
            assertEquals(true, e.getMessage());
        }
    }
    @Test
    void simpleTest3(){
        String jsonString = """
{
    "quoted":"test\\"123"
}
                """;
        
        try {
            JsonNode node = Parser.parse(jsonString);
            assertEquals("test\\\"123", node.get("quoted").getAsString());
        } catch (Exception e) {
            assertEquals(true, e.getMessage());
        }
    }
    @Test
    void simpleWhiteSpaceTest1(){
        String jsonString = """
{"jank":"defualt"}
        """;
        
        try {
            JsonNode node = Parser.parse(jsonString);
            assertEquals("defualt", node.get("jank").getAsString());
        } catch (Exception e) {
            assertEquals(true, e.getMessage());
        }
    }
    @Test
    void simpleWhiteSpaceTest2(){
        String jsonString = """
{       "jank"   
                    :         "   defualt"            
       }
        """;
        
        try {
            JsonNode node = Parser.parse(jsonString);
            assertEquals("   defualt", node.get("jank").getAsString());
        } catch (Exception e) {
            assertEquals(true, e.getMessage());
        }
    }
    @Test
    void simpleWhiteSpaceTest3(){
        String jsonString = """
{
        


" jank "



:




"defualt"




}
        """;
        
        try {
            JsonNode node = Parser.parse(jsonString);
            assertEquals("defualt", node.get(" jank ").getAsString());
        } catch (Exception e) {
            assertEquals(true, e.getMessage());
        }
    }
    @Test
    void simpleArrayTest1(){
        String jsonString = """
            [0,1,2,3,4,5,6,7,8,9]
        """;
        
        try {
            JsonNode node = Parser.parse(jsonString);
            
            for(int i = 0; i<10;i++){
                assertEquals(i, node.getIndex(i).getAsInt());
            }
        } catch (Exception e) {
            assertEquals(true, e.getMessage());
        }
    }    
    @Test
    void simpleArrayTest2(){
        String jsonString = """
    [{"foo":"bar"}]
        """;
        
        try {
            JsonNode node = Parser.parse(jsonString);
            assertEquals("bar", node.getIndex(0).get("foo").getAsString());
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(true, e.getMessage());
        }
    }    
    @Test
    void simpleArrayTest3(){
        String jsonString = """
    [{"hi":1},{"hi":2},{"hi":3}]
        """;
        
        try {
            JsonNode node = Parser.parse(jsonString);
            
            for(int i = 0; i<3;i++){
                assertEquals(i+1, node.getIndex(i).get("hi").getAsInt());
            }
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(false, e.getMessage());
        }
    }    
    @Test 
    void complexTest1(){
        String jsonString = """
    {
        "goob":{
            "gob":[
                1,
                2,
                {
                    "null": [
                        true
                    ]
                },
                "bar"
            ]
        }    
    }
                """;
        try {
            JsonNode node = Parser.parse(jsonString);

            assertEquals(true, node.get("goob").get("gob").getIndex(2).get("null").getIndex(0).getAsLiteral());

        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(false, e.getMessage());
        }
    }
    @Test
    void randomTest(){
        List<String[]> arr = new ArrayList<>();
        String jsonString = "{";
        for(int i = 0; i < 100; i++){
            String k ,v;
            k = randomString(10);
            v = randomString(10);
            arr.add(new String[]{k,v});
            jsonString += "\""+k+"\":\""+v+"\",";
        }
        jsonString += "\"foo\":\"bar\"}";
        JsonNode node = Parser.parse(jsonString);
        assertEquals("bar", node.get("foo").getAsString());
        for(int i = 0; i < arr.size(); i++){
            assertEquals(arr.get(i)[1], node.get(arr.get(i)[0]).getAsString());
        }
    }
    String randomString(int len){
        final String chars = "ABCDEFGHIJKLMNOPQRUTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String out = "";
        for(int i = 0; i <len; i++){
            out += String.valueOf(chars.charAt((int) Math.floor(Math.random() * chars.length())));
        }
        return out;
    }
}
