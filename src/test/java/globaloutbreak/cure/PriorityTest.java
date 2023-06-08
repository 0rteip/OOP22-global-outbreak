package globaloutbreak.cure;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import globaloutbreak.model.cure.prioriry.CurePriority;
import globaloutbreak.model.cure.prioriry.Priority;

/**
 * Test for Priority.
 */
final class PriorityTest {

    /**
     * Test if Priority BUilder work correctly.
     */
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

    /**
     * Test if the priority are build in the correct order.
     */
    @Test
    void testOrderedPriority() {
        final int priority = 0;
        final CurePriority.Builder curePrioBuilder = new CurePriority.Builder().setPriority(priority);

        assertDoesNotThrow(() -> curePrioBuilder.build());
        assertThrows(IllegalStateException.class, () -> curePrioBuilder.build());
        assertDoesNotThrow(() -> curePrioBuilder.setPriority(priority + 1).build());
    }
}
