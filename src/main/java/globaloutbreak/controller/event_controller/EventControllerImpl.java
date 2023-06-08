package globaloutbreak.controller.event_controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import globaloutbreak.model.Model;

/**
 * 
 */
public class EventControllerImpl implements EventController {
    final private Model model;
    public EventControllerImpl(Model model) throws IOException {
        this.model = model;
        ObjectMapper map = new ObjectMapper();
        JsonNode node = map.readTree(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream("resources\\region\\ConfigMeans.json"), StandardCharsets.UTF_8)));
        node.forEach( k -> {
            final Iterator<Entry<String, JsonNode>> iterator = k.fields();
            float morti = 0;
            String name = "";
            float prob = 0;
            while(iterator.hasNext()) {
                final Entry<String, JsonNode> e = iterator.next();
                switch(e.getKey()) {
                    case "name" : name = e.getValue().textValue();
                        break;
                    case "prob" : prob = e.getValue().floatValue();
                        break;
                    case "morti" : morti = e.getValue().floatValue();
                        break;
                }  
            }
            this.model.addEvent(morti, name, prob);
        });
    }

}
