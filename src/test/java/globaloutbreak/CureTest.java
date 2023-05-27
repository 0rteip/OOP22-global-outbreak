package globaloutbreak;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import globaloutbreak.model.api.Cure;
import globaloutbreak.model.api.Priority;
import globaloutbreak.model.api.Region;
import globaloutbreak.model.impl.CurePriority;
import globaloutbreak.model.impl.RegionCureStatus;
import globaloutbreak.model.impl.SimpleCure;

class CureTest {

    @Test
    void testPriorityBuilder() {
        final int priority = 0;
        final String description = "None";
        final float resourcesPercentage = 0.2f;
        final float detectionRate = 0.3f;
        final Priority prio = new CurePriority.Builder()
                .setPriority(priority)
                .setDescription(description)
                .setResourcesPercentage(resourcesPercentage)
                .setDetectionRate(detectionRate)
                .build();

        assertEquals(description, prio.getDescription());
        assertEquals(detectionRate, prio.getDetectionRate());
        assertEquals(priority, prio.getPriority());
        assertEquals(resourcesPercentage, prio.getResourcesPercentage());
    }

    @Test
    void testOrderedPriority() {
        final int priority = 0;
        final CurePriority.Builder curePrioBuilder = new CurePriority.Builder().setPriority(priority);

        assertDoesNotThrow(() -> curePrioBuilder.build());
        assertThrows(IllegalStateException.class, () -> curePrioBuilder.build());
        assertDoesNotThrow(() -> curePrioBuilder.setPriority(priority + 1).build());
    }

    @Nested
    class SimpleCureTest {

        SimpleCure.Builder cureBuilder;

        @BeforeEach
        public void init() {
            final int numberOfRegions = 10;
            final List<Priority> prios = new ArrayList<>();
            prios.add(new CurePriority.Builder()
                    .setResourcesPercentage(0.3f)
                    .build());
            final List<Region> regions = getRegions(numberOfRegions);
            this.cureBuilder = new SimpleCure.Builder(regions, prios);
        }

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
            assertTrue(cure.getGlobalStatus().getRemainingDays() == -1);

            IntStream.range(0, daysBeforeStartResearch + 1).forEach(w -> {
                cure.research();
            });
            assertTrue(cure.getGlobalStatus().getMajorContributors().size() == numberOfMajorContributors);
            assertTrue(cure.getGlobalStatus().getRemainingDays() != -1);
        }

        @Test
        void testSingleUseCureBuilder() {
            assertTrue(this.cureBuilder.build().isConsistent());
            assertThrows(IllegalStateException.class, () -> this.cureBuilder.build());
        }

        @Test
        void testIncosistentWithEmptyList() {
            // Regions and Prioritys can't be empty
            assertFalse(new SimpleCure.Builder(new ArrayList<>(), new ArrayList<>())
                    .build()
                    .isConsistent());
        }

        @Test
        void testIncosistentWithInvalidPriority() {
            final int invalidPriority = 1;
            // Priority must be contained in the prioritys of the cure
            assertFalse(this.cureBuilder
                    .setCurrentPriority(invalidPriority)
                    .build()
                    .isConsistent());
        }

        @Test
        void testIncosistentWithNegativiValue() {
            final float invalidDailyBudget = -12_000f;
            // All value must be positive (one test is sufficient)
            assertFalse(this.cureBuilder
                    .setDailyBudget(invalidDailyBudget)
                    .build()
                    .isConsistent());
        }

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
                    public int getTotalPopulation() {
                        return pop;
                    }

                    @Override
                    public int getDeath() {
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

                };
                r.add(reg);
            });
            return List.copyOf(r);
        }
    }
}
