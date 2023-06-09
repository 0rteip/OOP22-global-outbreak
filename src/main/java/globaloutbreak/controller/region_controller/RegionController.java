package globaloutbreak.controller.region_controller;

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

import globaloutbreak.controller.api.Controller;
import globaloutbreak.model.Model;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.Region;
/**
 * Implement. of RegionControllerInt.
 */
public final class RegionController implements RegionControllerInt {
    private final Controller controller;
    /**
     * Constructor.
     * @param controller
     *              controller
     * @throws IOException
     */
    public RegionController(final Controller controller) throws IOException {
        //readMeans();
        this.controller = controller;
        final String path = "region/ConfigRegion.json";
        final JsonNode node = getJsonNode(path);
        
        node.forEach(k -> {
            int popTot = 0;
            String name = "";
            float urban = 0;
            float poor = 0;
            float humid = 0;
            float hot = 0;
            List<String> reachableState;
            final Map<String, Pair<Integer, Optional<List<String>>>> means = new HashMap<>();
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
                        means.put("terra", new Pair<>(1, Optional.of(reachableState)));
                        break;
                    case "hot" : hot = e.getValue().floatValue();
                        break;
                    case "facilities" : facilities = e.getValue().intValue();
                        break;
                    case "popTot" : popTot = e.getValue().intValue();
                        break;
                    case "poor" : poor = e.getValue().floatValue();
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
        this.model.incDeathPeople(newdeath, region);
    }

    @Override
    public void incOrDecInfectedPeople(final Integer newInfect, final Region region) {
        this.model.incOrDecInfectedPeople(newInfect, region);
    }

    @Override
    public Integer getDeath(final int color) {
        return findRegionByColor(color).getNumDeath();
    }

    @Override
    public Integer getInfect(final int color) {
        return findRegionByColor(color).getNumInfected();
    }

    @Override
    public String getName(final int color) {
        return findRegionByColor(color).getName();
    }

    private Region findRegionByColor(final int color) {
        return this.model.getRegions().stream().filter(k -> k.getColor() == color).findFirst().get();
    }

    private List<String> getTypeOfMeans(final JsonNode node) {
        final List<String> reach = new LinkedList<>();
        final Iterator<Entry<String, JsonNode>> iterator = node.fields();
            while (iterator.hasNext()) {
                final Entry<String, JsonNode> e = iterator.next();
                if (!reach.contains(e.getValue().textValue())) {
                    reach.add(e.getValue().textValue());
                }
            }
        return reach;
    }

    private JsonNode getJsonNode(final String path) throws IOException {
        final ObjectMapper map = new ObjectMapper();
        return map.readTree(new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path), 
                StandardCharsets.UTF_8)));
    }

    @Override
    public void selectRegion(final int region) {
        final Region r = this.model.getRegions().stream().filter(k -> k.getColor() == region).toList().get(0);
        this.model.selectedRegion(r);
    }
}
