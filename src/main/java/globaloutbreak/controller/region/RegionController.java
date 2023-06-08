package globaloutbreak.controller.region;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import globaloutbreak.model.Model;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;

public final class RegionController implements RegionControllerInt {
    private final Model model;
    /**
     * 
     * @param model
     *              model
     * @throws IOException
     */
    public RegionController(final Model model) throws IOException {
        //readMeans();
        String path = "resources\\region\\ConfigRegion.json";
        JsonNode node = getJsonNode(path);
        this.model = model;
        node.forEach(k -> {
            int popTot = 0;
            String name = "";
            float urban = 0;
            float poor = 0;
            float humid = 0;
            float hot = 0;
            List<String> reachableState;
            Map<String,Pair<Integer, Optional<List<String>>>> means = new HashMap<>();
            int color = 0;
            Integer facilities = 0;
            final Iterator<Entry<String, JsonNode>> iterator = k.fields();
            while (iterator.hasNext()) {
                final Entry<String, JsonNode> e = iterator.next();
                switch (e.getKey()) {
                    case "nome" : name = e.getValue().textValue(); 
                        break;
                    case "colore" : color = e.getValue().intValue();
                        break;
                    case "porti" : means.put(e.getKey(), new Pair<>(e.getValue().intValue(), Optional.empty()));
                        break;
                    case "areoporti" : means.put(e.getKey(), new Pair<>(e.getValue().intValue(), Optional.empty()));
                        break;
                    case "humid" : humid = e.getValue().floatValue();
                        break;
                    case "confini" : reachableState = getTypeOfMeans(k);
                        means.put("terra",  new Pair<>(1, Optional.of(reachableState)));
                        break;
                    case "hot" : hot = e.getValue().floatValue();
                        break;
                    case "facilities" : facilities = e.getValue().intValue();
                        break;  
                    case "popTot" : popTot = e.getValue().intValue();
                        break;
                    case "urban" : urban = e.getValue().floatValue();
                        break;
                    default :
                        break;
                }
            }
            model.addRegion(popTot, name, means, urban, poor, color, facilities, hot, humid);
        });

    }

    @Override
    public void incDeathPeople(final Integer newdeath, final Region region) {

    }

    @Override
    public void incOrDecInfectedPeople(final Integer newInfect, final Region region) {

    }

    @Override
    public Integer getDeath(final Integer color) {
        model.getRegions();
        return 0;
    }

    @Override
    public Integer getInfect(final Integer color) {
        return 0;
    }

    @Override
    public String getName(final Integer color) {
        return "";
    }

    /*private void readMeans() throws IOException {
        JsonNode node = getJsonNode("resources\\region\\ConfigMeans.json");
        means = new LinkedList<>();
        node.forEach( k -> {
            final Iterator<Entry<String, JsonNode>> iterator = k.fields();
            while(iterator.hasNext()) {
                String type;
                List<String> reach;
                final Entry<String, JsonNode> e = iterator.next();
                switch(e.getKey()) {
                    case "type" : type = e.getValue().textValue();
                }  
                if(!means.contains(type)) {
                    means.add(type);
                }     
            }
        });
    }*/
    private List<String> getTypeOfMeans(JsonNode node) {
        List<String> reach = new LinkedList<>();
        final Iterator<Entry<String, JsonNode>> iterator = node.fields();
            while (iterator.hasNext()) {
                final Entry<String, JsonNode> e = iterator.next();
                if(!reach.contains(e.getValue().textValue())) {
                    reach.add(e.getValue().textValue());
                }
            }
        return reach;
    }
    private JsonNode getJsonNode(String path) throws IOException {
        ObjectMapper map = new ObjectMapper();
        return map.readTree(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path), 
                StandardCharsets.UTF_8)));
    }
}
