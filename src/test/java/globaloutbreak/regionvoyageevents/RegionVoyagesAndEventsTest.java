package globaloutbreak.regionvoyageevents;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import globaloutbreak.controller.event.EventController;
import globaloutbreak.controller.event.EventControllerImpl;
import globaloutbreak.controller.region.RegionController;
import globaloutbreak.controller.region.RegionControllerImpl;
import globaloutbreak.controller.voyage.VoyageController;
import globaloutbreak.controller.voyage.VoyageControllerImpl;
import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.events.CauseEvent;
import globaloutbreak.model.events.CauseEventsImpl;
import globaloutbreak.model.events.Event;
import globaloutbreak.model.events.ExtractedEvent;
import globaloutbreak.model.region.MeansState;
import globaloutbreak.model.region.Region;
import globaloutbreak.model.voyage.Voyages;

/**
 * Test for Region and Voyages.
 */
final class RegionVoyagesAndEventsTest {
    private final RegionController contr = new RegionControllerImpl();
    private final VoyageController vC = new VoyageControllerImpl();
    private final EventController controller = new EventControllerImpl();
    private final List<Region> regions = contr.getRegions();
    private long popTot;

    @Test
    void testRegion() {
        contr.getRegions().forEach(k -> popTot += k.getPopTot());
        Region r = regions.get(0);
        r.incDeathPeople(r.getPopTot(), false);
        assertEquals(r.getPopTot(), r.getNumDeath());
        assertEquals(RegionCureStatus.FINISHED, r.getCureStatus());
        r.getTrasmissionMeans().stream().forEach(k -> {
            assertEquals(MeansState.CLOSE, k.getState());
            // System.out.println(k.getType());
        });
        final int extra = 2000;
        r = regions.get(0);
        r.incDeathPeople(r.getPopTot() + extra, false);
        assertEquals(r.getPopTot(), r.getNumDeath());
        assertEquals(RegionCureStatus.FINISHED, r.getCureStatus());
        r.getTrasmissionMeans().stream().forEach(k -> {
            assertEquals(MeansState.CLOSE, k.getState());
        });
        final int nR = 30;
        r = regions.get(nR);
        r.incOrDecInfectedPeople(r.getPopTot() + extra);
        assertEquals(r.getPopTot(), r.getNumInfected());
        r = regions.get(nR);
        r.incOrDecInfectedPeople(r.getPopTot());
        assertEquals(r.getPopTot(), r.getNumInfected());
    }

    @Test
    void testVoyages() {
        final Voyages means = vC.createVoyage();
        // System.out.println(means.getMeans());
        final Map<String, Float> pot = new HashMap<>();
        final float v = 0;
        final float a = (float) 0;
        pot.put("terra", v);
        pot.put("aereoporti", a);
        pot.put("porti", v);
        pot.forEach((s, f) -> {
            assertTrue(means.getMeans().contains(s));
        });
        means.extractMeans(contr.getRegions(), pot);
        regions.forEach(k -> {
            k.getTrasmissionMeans().forEach(t -> {
                assertTrue(means.getMeans().contains(t.getType()));
            });
            k.incOrDecInfectedPeople((int) Math.floor(k.getPopTot() * 0.5));
        });
        regions.forEach(i -> {
            i.getTrasmissionMeans().forEach(t -> {
                assertTrue(means.getMeans().contains(t.getType()));
            });
            i.incDeathPeople(i.getPopTot(), false);
        });
        means.extractMeans(regions, pot);
    }

    @Test
    void eventTest() {
        final List<Event> events = controller.createEvents();
        final CauseEvent causeEvent = new CauseEventsImpl(events);
        // System.out.println(events);
        // events.forEach(k -> System.out.println(k.getName() + " " + k.getProbOfHapp()));
        Optional<ExtractedEvent> cat = causeEvent.causeEvent(regions);
        while (cat.isEmpty()) {
            cat = causeEvent.causeEvent(regions);
        }
        /*
         * System.out.println("Name " + cat.get().getX() + " Region " +
         * cat.get().getY().getX().getName()
         * + " Morti " + cat.get().getY().getY());
         */
    }
}
