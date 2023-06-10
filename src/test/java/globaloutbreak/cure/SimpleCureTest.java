package globaloutbreak.cure;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.model.region.Region;

import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.cure.SimpleCure;
import globaloutbreak.model.cure.prioriry.CurePriority;
import globaloutbreak.model.cure.prioriry.Priority;
import globaloutbreak.model.region.Climate;
import globaloutbreak.model.region.TransmissionMean;

/**
 * Test for SimpleCure.
 */
final class SimpleCureTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private SimpleCure.Builder cureBuilder;

    /**
     * Initialize before tests.
     */
    @BeforeEach
    public void init() {
        final int numberOfRegions = 10;
        final float resPerc = 0.3f;
        final List<Priority> prios = new ArrayList<>();
        prios.add(new CurePriority.Builder()
                .setResourcesPercentage(resPerc)
                .build());
        final List<Region> regions = getRegions(numberOfRegions);
        this.cureBuilder = new SimpleCure.Builder(regions, prios);
    }

    /**
     * Test if The cure returned data are correct: number of contributors and
     * remaining days.
     */
    @Test
    void testCureData() {
        final int currentPriority = 0;
        final float dailyBudget = 1_000.5f;
        final int daysBeforeStartResearch = 5;
        final float necessaryBudget = 20_000_000;
        final int numberOfMajorContributors = 3;
        final float researchBudget = 10;
        final float researchersEfficiency = 0.4f;
        final Cure cure = this.cureBuilder
                .setCurrentPriority(currentPriority)
                .setDailyBudget(dailyBudget)
                .setDaysBeforeStartResearch(daysBeforeStartResearch)
                .setNecessaryBudget(necessaryBudget)
                .setNumberOfMajorContributors(numberOfMajorContributors)
                .setResearchBudget(researchBudget)
                .setResearchersEfficiency(researchersEfficiency)
                .build();

        assertTrue(cure.getGlobalStatus().getMajorContributors().isEmpty());
        assertEquals(-1, cure.getGlobalStatus().getRemainingDays());

        IntStream.range(0, daysBeforeStartResearch + 1).forEach(w -> {
            cure.research();
        });
        assertEquals(numberOfMajorContributors, cure.getGlobalStatus().getMajorContributors().size());
        assertNotEquals(-1, cure.getGlobalStatus().getRemainingDays());
    }

    /**
     * Test if Builder can only be used once.
     */
    @Test
    void testSingleUseCureBuilder() {
        assertTrue(this.cureBuilder.build().isConsistent());
        assertThrows(IllegalStateException.class, () -> this.cureBuilder.build());
    }

    /**
     * Test if Simple CUre can be initialized with empty list.
     */
    @Test
    void testIncosistentWithEmptyList() {
        // Regions and Prioritys can't be empty
        assertFalse(new SimpleCure.Builder(new ArrayList<>(), new ArrayList<>())
                .build()
                .isConsistent());
    }

    /**
     * Test if Cure can be initialized with a priority value not present in the
     * priority of the cure.
     */
    @Test
    void testIncosistentWithInvalidPriority() {
        final int invalidPriority = 1;
        // Priority must be contained in the prioritys of the cure
        assertFalse(this.cureBuilder
                .setCurrentPriority(invalidPriority)
                .build()
                .isConsistent());
    }

    /**
     * Test if Cure can be initialized with a negative value, one for all.
     */
    @Test
    void testIncosistentWithNegativiValue() {
        final float invalidDailyBudget = -12_000f;
        // All value must be positive (one test is sufficient)
        assertFalse(this.cureBuilder
                .setDailyBudget(invalidDailyBudget)
                .build()
                .isConsistent());
    }

    /**
     * Test if the CUre can be initialized with HIgher value on reserach budget than
     * on necessary budget.
     */
    @Test
    void testIncosistentWithHIgherResearchBudget() {
        final float higherValue = 100_000;
        final float lowerValue = 50_000;
        // Necessary budget mjst be higher than research budget
        assertFalse(this.cureBuilder
                .setNecessaryBudget(lowerValue)
                .setResearchBudget(higherValue)
                .build()
                .isConsistent());
    }

    private List<Region> getRegions(final int numberOfRegions) {
        final List<Region> r = new ArrayList<>();

        final String logString = "Unused method called";
        final String errString = "Uninplemented method";

        IntStream.range(1, numberOfRegions).forEach(c -> {
            final Region reg = new Region() {

                private static final int MAX = 6;
                private final int pop = Math.abs(RandomGenerator.getDefault().nextInt(10, 100));
                private final int deat = pop / Math.abs(RandomGenerator.getDefault().nextInt(2, 4));
                private RegionCureStatus status = RegionCureStatus.NONE;

                @Override
                public int getFacilities() {
                    return c % MAX;
                }

                @Override
                public int getPopTot() {
                    return pop;
                }

                @Override
                public int getNumDeath() {
                    return deat;

                }

                @Override
                public void setCureStatus(final RegionCureStatus status) {
                    this.status = status;
                }

                @Override
                public RegionCureStatus getCureStatus() {
                    return this.status;
                }

                @Override
                public void incDeathPeople(final int dead) {
                    throw new UnsupportedOperationException("Unimplemented method 'incDeathPeople'");
                }

                @Override
                public void incOrDecInfectedPeople(final int infected) {
                    // TODO Auto-generated method stub
                    throw new UnsupportedOperationException("Unimplemented method 'incOrDecInfectedPeople'");
                }

                @Override
                public float calcPercInfected() {
                    logger.warn(logString);
                    throw new UnsupportedOperationException(errString);
                }

                @Override
                public int getNumInfected() {
                    logger.warn(logString);
                    throw new UnsupportedOperationException(errString);
                }

                @Override
                public String getName() {
                    logger.warn(logString);
                    throw new UnsupportedOperationException(errString);
                }

                @Override
                public float getUrban() {
                    logger.warn(logString);
                    throw new UnsupportedOperationException(errString);
                }

                @Override
                public float getPoor() {
                    logger.warn(logString);
                    throw new UnsupportedOperationException(errString);
                }

                @Override
                public int getColor() {
                    logger.warn(logString);
                    throw new UnsupportedOperationException(errString);
                }

                @Override
                public Climate getClimate() {
                    logger.warn(logString);
                    throw new UnsupportedOperationException(errString);
                }

                @Override
                public List<TransmissionMean> getTrasmissionMeans() {
                    logger.warn(logString);
                    throw new UnsupportedOperationException(errString);
                }

                @Override
                public void initializeObserver(final PropertyChangeListener listener) {
                    logger.warn(logString);
                    throw new UnsupportedOperationException(errString);
                }

            };
            r.add(reg);
        });
        return List.copyOf(r);
    }
}
