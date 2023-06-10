package globaloutbreak.model.voyage;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
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
    public Map<String, Map<Pair<Integer, Integer>, Integer>> extractMeans(final List<Region> regions,
            final Map<String, Float> pot) {

        final Map<String, Map<Pair<Integer, Integer>, Integer>> extractedMeans = new HashMap<>();
        sizeAndNameOfMeans.forEach((means, size) -> {
            final Map<Pair<Integer, Integer>, Integer> oneMeans = new HashMap<>();
            final List<Region> newRegions = regions.stream()
                    .filter(k -> checkIfMeansAreOpen(k.getTrasmissionMeans(), means)).toList();
            for (int i = 0; i < size.getX(); i++) {
                final Pair<Integer, Integer> partDest = extractRegion(newRegions, means);
                final Region part = newRegions
                        .stream()
                        .filter(k -> k.getColor() == partDest.getX()).toList().get(0);
                final float prob = part.calcPercInfected() + pot.get(means);
                oneMeans.put(partDest, numInfected(prob, size.getY()));
            }
            extractedMeans.put(means, oneMeans);
        });
        return extractedMeans;
    }

    private Pair<Integer, Integer> extractRegion(final List<Region> newRegions, final String type) {
        final Region region = newRegions.get(rand.nextInt(0, newRegions.size()));
        List<Region> efectieRegions = new LinkedList<>(newRegions);
        switch (type) {
            case "terra":
                efectieRegions = findRegionsByName(newRegions, region.getTrasmissionMeans()
                        .stream()
                        .filter(k -> k.getType().equals(type))
                        .findFirst().get()
                        .getReachableStates().get());
                break;
            default:
                break;
        }
        Region dest = efectieRegions.get(rand.nextInt(0, efectieRegions.size()));
        while (dest.getColor() == region.getColor()) {
            dest = efectieRegions.get(rand.nextInt(0, efectieRegions.size()));
            efectieRegions.remove(dest);
        }
        return new Pair<Integer, Integer>(region.getColor(), dest.getColor());
    }

    private List<Region> findRegionsByName(final List<Region> regions, final List<String> nameRegions) {
        final List<Region> rs = new LinkedList<>();
        regions.forEach(k -> {
            nameRegions.forEach(s -> {
                if (k.getName().equals(s)) {
                    rs.add(k);
                }
            });
        });
        return rs;
    }

    private boolean checkIfMeansAreOpen(final List<TransmissionMean> list, final String means) {
        final Long open = list.stream().filter(k -> k.getType().equals(means) && k.getState().equals(MeansState.OPEN))
                .count();
        return open > 0;
    }

    private Integer numInfected(final float prob, final Integer size) {
        final int prod = Math.round(size * prob);
        if (prod > size) {
            logger.warn("too many seatsI'll fill the plane");
            return size;
        } else if (rand.nextInt(0, 100) >= (prob * 100)) {
            return prod;
        }
        return 0;
    }

    @Override
    public List<String> getMeans() {
        return new LinkedList<>(sizeAndNameOfMeans.keySet());
    }

}
