package globaloutbreak.model.voyage;

import java.util.List;
import java.util.Map;
import java.util.Random;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.MeansState;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.region.TransmissionMean;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 
 */
public final class VoyageImpl implements Voyage {
    private final Map<String, Pair<Integer, Integer>> sizeAndNameOfMeans;
    private final Random rand = new Random();
    /**
     * 
     * @param sizeAndNameOfMeans
     */
    public VoyageImpl(final Map<String, Pair<Integer, Integer>> sizeAndNameOfMeans) {
        this.sizeAndNameOfMeans = new HashMap<>(sizeAndNameOfMeans);
    }

    @Override
    public Map<String, Map<Integer, Pair<Integer, Integer>>> extractMeans(final List<Region> regions, final Map<String, Float> pot) {
        final Map<String, Map<Integer, Pair<Integer, Integer>>> extractedMeans = new HashMap<>();
        sizeAndNameOfMeans.forEach((means, size) -> {
            final Map<Integer, Pair<Integer, Integer>> oneMeans = new HashMap<>();
            final List<Region> newRegions = regions.stream()
                    .filter(k -> checkIfMeansAreOpen(k.getTrasmissionMeans(), means)).toList();
                    
            for (int i = 0; i < size.getX(); i++) {
                final Pair<Integer, Integer> partDest = extractRegion(newRegions, means);
                final float prob = newRegions
                        .stream()
                        .filter(k -> k.getColor() == partDest.getX())
                        .toList()
                        .get(0).calcPercInfected() + pot.get(means);

                oneMeans.put(numInfected(prob, size.getY()), partDest);
            }
            extractedMeans.put(means, oneMeans);
        });
        return extractedMeans;
    }

    private Pair<Integer, Integer> extractRegion(final List<Region> newRegions, String type) {
        final Region region = newRegions.get(rand.nextInt(0, newRegions.size()));
        List<Region> efectieRegions = new LinkedList<>();
        switch (type) {
            case "terra" : efectieRegions = findRegionsByName(newRegions, region.getTrasmissionMeans()
                        .stream()
                        .filter(k -> k.getType().equals(type))
                        .findFirst().get()
                        .getReachableStates().get());
                break;
            case "porti" : 
            case "areoporti" :
                efectieRegions = newRegions.stream()
                        .filter(k -> k.getTrasmissionMeans()
                                .stream().filter(i -> i.getType()
                                .equals(type)).count() > 0)
                        .toList();
        }
        int dest = rand.nextInt(0, efectieRegions.size());
        while (dest == region.getColor()) {
            dest = rand.nextInt(0, efectieRegions.size());
        }
        return new Pair<Integer, Integer>(region.getColor(), efectieRegions.get(dest).getColor());
    }

    private List<Region> findRegionsByName(List<Region> regions, List<String> nameRegions) {
        List<Region> rs = new LinkedList<>();
        regions.forEach(k -> {
            nameRegions.forEach(s ->  {
                if(k.getName().equals(s)) {
                    rs.add(k);
                }
            }
            );
        });
        return rs;
    }

    private boolean checkIfMeansAreOpen(final List<TransmissionMean> list, final String means) {
        final Long open = list.stream().filter(k -> k.getType().equals(means) && k.getState().equals(MeansState.OPEN))
                .count(); 
        return open > 0;
    }

    private Integer numInfected(final float prob, final Integer size) {
        if (rand.nextInt(0, 100) >= prob) {
            return (int) Math.floor(size * prob);
        }
        return 0;
    }

    @Override
    public List<String> getMeans() {
        return new LinkedList<>(sizeAndNameOfMeans.keySet());
    }
}
