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
 * Implement. of Vogyages.
 */
public final class VoyagesImpl implements Voyages {
    private final Map<String, Pair<Integer, Integer>> sizeAndNameOfMeans;
    private final Random rand = new Random();

    /**
     * 
     * @param sizeAndNameOfMeans
     */
    public VoyagesImpl(final Map<String, Pair<Integer, Integer>> sizeAndNameOfMeans) {
        this.sizeAndNameOfMeans = new HashMap<>(sizeAndNameOfMeans);
    }

    @Override
    public List<Voyage> extractMeans(final List<Region> regions,
            final Map<String, Float> pot) {
        final List<Voyage> extractedMeans = new LinkedList<>();
        sizeAndNameOfMeans.forEach((means, size) -> {
            final List<Region> newRegions = regions.stream()
                    .filter(k -> checkIfMeansAreOpen(k.getTrasmissionMeans(), means)).toList();
            if (newRegions.size() > 2) {
                for (int i = 0; i < size.getX(); i++) {
                    final Pair<Region, Region> partDest = extractRegion(newRegions, means);
                    if (partDest.getX() != null) {
                        final Region part = newRegions
                                .stream()
                                .filter(k -> k.getColor() == partDest.getX().getColor()).toList().get(0);
                        float prob = 0;
                        if(part.calcPercInfected() > 0) {
                            prob = part.calcPercInfected() + pot.get(means);
                        }
                        final Voyage voyage = new VoyageImpl(means, partDest.getX().getColor(),
                                partDest.getY().getColor(),
                                numInfected(prob, size.getY()));
                        extractedMeans.add(voyage);
                    }
                }
            }
        });
        return extractedMeans;
    }

    private Pair<Region, Region> extractRegion(final List<Region> newRegions, final String type) {
        final Region region = newRegions.get(rand.nextInt(0, newRegions.size()));
        List<Region> efectieRegions = new LinkedList<>(newRegions);
        efectieRegions.remove(region);
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
        if (!efectieRegions.isEmpty()) {
            final Region dest = efectieRegions.get(rand.nextInt(0, efectieRegions.size()));
            return new Pair<>(region, dest);
        }
        return new Pair<>(null, null);
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

    private long numInfected(final float prob, final int size) {
        final long prod = Math.round(size * prob);
        if (prod > size) {
            // logger.warn("too many seatsI'll fill the plane");
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
