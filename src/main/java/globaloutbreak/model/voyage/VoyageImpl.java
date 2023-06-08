package globaloutbreak.model.voyage;

import java.util.List;
import java.util.Map;
import java.util.Random;

import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.MeansState;
import globaloutbreak.model.region.RegionImpl;
import globaloutbreak.model.region.TransmissionMeansImpl;

import java.util.HashMap;

/**
 * 
 */
public final class VoyageImpl implements Voyage {
    private final Map<String, Pair<Integer, Integer>> sizeAndNameOfMeans;

    /**
     * 
     * @param sizeAndNameOfMeans
     */
    public VoyageImpl(final Map<String, Pair<Integer, Integer>> sizeAndNameOfMeans) {
        this.sizeAndNameOfMeans = sizeAndNameOfMeans;
    }

    @Override
    public Map<String, Map<Integer, Pair<Integer, Integer>>> extractMeans(final List<RegionImpl> regions) {
        Map<String, Map<Integer, Pair<Integer, Integer>>> extractedMeans = new HashMap<>();
        sizeAndNameOfMeans.forEach( (means, size) -> {
            Map<Integer, Pair<Integer, Integer>> oneMeans = new HashMap<>();
            List<RegionImpl> newRegions = regions.stream()
                    .filter(k -> checkIfMeansAreOpen(k.getTrasmissionMeans(), means)).toList();
            for ( int i = 0; i < size.getX(); i++) {
                Pair<Integer, Integer> partDest = extractRegion(newRegions);
                int prob = newRegions
                        .stream()
                        .filter( k -> k.getColor() == partDest.getX())
                        .toList()
                        .get(0).calcPercInfected();
                oneMeans.put(numInfected(prob, size.getY()), partDest);
            }
            extractedMeans.put(means, oneMeans);
        });
        return extractedMeans;
    }

    private Pair<Integer, Integer> extractRegion(final List<RegionImpl> newRegions) {
        Random random = new Random();
        int region = newRegions.get(random.nextInt(0, newRegions.size())).getColor();
        int dest = random.nextInt(0, newRegions.size());
        while (dest == region) {
            dest = random.nextInt(0, newRegions.size());
        }
        return new Pair<Integer, Integer>(region, dest);
    }

    private boolean checkIfMeansAreOpen(final List<TransmissionMeansImpl> list, final String means) {
        Long open = list.stream().filter(k -> k.getType().equals(means) && k.getState().equals(MeansState.OPEN))
                .count();
        if (open > 0) {
            return true;
        }
        return false;
    }

    private Integer numInfected(final Integer prob, final Integer size) {
        Random rand = new Random();
        if (rand.nextInt(0, 100) >= prob) {
            return (size * 100 / prob);
        }
        return 0;
    }
}
