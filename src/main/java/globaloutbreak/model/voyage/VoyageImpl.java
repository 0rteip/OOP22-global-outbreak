package globaloutbreak.model.voyage;

import java.util.List;
import java.util.Map;
import java.util.Random;
import globaloutbreak.model.pair.Pair;
import globaloutbreak.model.region.MeansState;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.region.TransmissionMean;

import java.util.HashMap;

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
    public Map<String, Map<Integer, Pair<Integer, Integer>>> extractMeans(final List<Region> regions) {
        final Map<String, Map<Integer, Pair<Integer, Integer>>> extractedMeans = new HashMap<>();
        sizeAndNameOfMeans.forEach((means, size) -> {
            final Map<Integer, Pair<Integer, Integer>> oneMeans = new HashMap<>();
            final List<Region> newRegions = regions.stream()
                    .filter(k -> checkIfMeansAreOpen(k.getTrasmissionMeans(), means)).toList();
            for (int i = 0; i < size.getX(); i++) {
                final Pair<Integer, Integer> partDest = extractRegion(newRegions);
                final int prob = newRegions
                        .stream()
                        .filter(k -> k.getColor() == partDest.getX())
                        .toList()
                        .get(0).calcPercInfected();
                oneMeans.put(numInfected(prob, size.getY()), partDest);
            }
            extractedMeans.put(means, oneMeans);
        });
        return extractedMeans;
    }

    private Pair<Integer, Integer> extractRegion(final List<Region> newRegions) {

        final int region = newRegions.get(rand.nextInt(0, newRegions.size())).getColor();
        int dest = rand.nextInt(0, newRegions.size());
        while (dest == region) {
            dest = rand.nextInt(0, newRegions.size());
        }
        return new Pair<Integer, Integer>(region, dest);
    }

    private boolean checkIfMeansAreOpen(final List<TransmissionMean> list, final String means) {
        final Long open = list.stream().filter(k -> k.getType().equals(means) && k.getState().equals(MeansState.OPEN))
                .count(); 
        return open > 0;
    }

    private Integer numInfected(final Integer prob, final Integer size) {
        if (rand.nextInt(0, 100) >= prob) {
            return (size * 100) / prob;
        }
        return 0;
    }
}
