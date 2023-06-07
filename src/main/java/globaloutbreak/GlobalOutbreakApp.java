package globaloutbreak;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.random.RandomGenerator;
import java.util.stream.IntStream;

import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import globaloutbreak.model.region.Region;

import globaloutbreak.model.cure.Cure;
import globaloutbreak.model.cure.RegionCureStatus;
import globaloutbreak.model.cure.SimpleCure;
import globaloutbreak.model.cure.prioriry.CurePriority;
import globaloutbreak.model.cure.prioriry.Priority;

// import globaloutbreak.controller.api.Controller;

/**
 * Application.
 */
@SuppressWarnings("PMD.SystemPrintln")
public final class GlobalOutbreakApp {

    private GlobalOutbreakApp() {
    }

    /**
     * Main.
     * 
     * @param args
     */
    public static void main(final String[] args) {
        // final Controller controller = ControllerImpl;
        // controller.startGame();
        // System.out.println("fine");
        final int days = 20;
        final int regions = 20;
        final String prioFilePath = "cure/priorities.json";
        final String cureFilePath = "cure/cure.json";
        final List<Priority> p = new ArrayList<>();
        final List<Region> r = new ArrayList<>();

        try {
            final CurePriority.Builder priorityBuilder = new CurePriority.Builder();
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode node = mapper.readTree(new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemResourceAsStream(prioFilePath), StandardCharsets.UTF_8)));

            node.forEach(n -> {
                final Iterator<Entry<String, JsonNode>> iter = n.fields();

                while (iter.hasNext()) {
                    final Entry<String, JsonNode> value = iter.next();
                    switch (value.getKey()) {
                        case "priority":
                            priorityBuilder.setPriority(value.getValue().asInt());
                            break;
                        case "description":
                            priorityBuilder.setDescription(value.getValue().textValue());
                            break;
                        case "resourcesPercentage":
                            priorityBuilder.setResourcesPercentage(value.getValue().floatValue());
                            break;
                        case "detectionRate":
                            priorityBuilder.setDetectionRate(value.getValue().floatValue());
                            break;
                        default:
                            System.out.println(value.toString() + " non riconosciuto");
                            break;
                    }
                }
                p.add(priorityBuilder.build());
            });
        } catch (IOException e) {
            System.out.println(e);
        }

        IntStream.range(1, regions).forEach(c -> {
            final Region reg = new Region() {

                private static final int MAX = 6;
                private final int pop = Math.abs(RandomGenerator.getDefault().nextInt(10, 100));
                private final int deat = pop / Math.abs(RandomGenerator.getDefault().nextInt(1, 10));
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
                public String toString() {
                    return "Region [id=" + c + ", population=" + pop + ", death=" + deat + ", status: " + status + "]";
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

        final SimpleCure.Builder cureBuilder = new SimpleCure.Builder(r, p);
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final JsonNode node = mapper.readTree(new BufferedReader(new InputStreamReader(
                    ClassLoader.getSystemResourceAsStream(cureFilePath), StandardCharsets.UTF_8)));

            final Iterator<Entry<String, JsonNode>> it = node.fields();

            while (it.hasNext()) {
                final Entry<String, JsonNode> value = it.next();
                switch (value.getKey()) {
                    case "dailyBudget":
                        cureBuilder.setDailyBudget(value.getValue().floatValue());
                        break;
                    case "numberOfMajorContributors":
                        cureBuilder.setNumberOfMajorContributors(value.getValue().intValue());
                        break;
                    case "researchersEfficiency":
                        cureBuilder.setResearchersEfficiency(value.getValue().floatValue());
                        break;
                    case "necessaryBudget":
                        cureBuilder.setNecessaryBudget(value.getValue().floatValue());
                        break;
                    case "researchBudget":
                        cureBuilder.setResearchBudget(value.getValue().floatValue());
                        break;
                    case "currentPriority":
                        cureBuilder.setCurrentPriority(value.getValue().intValue());
                        break;
                    case "daysBeforeStartResearch":
                        cureBuilder.setDaysBeforeStartResearch(value.getValue().intValue());
                        break;
                    default:
                        System.out.println(value.toString() + " non riconosciuto");
                        break;
                }
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        final Cure c = cureBuilder.build();

        // if (c.isConsistent()) {
        // System.out.println("cons");
        // } else {
        // // Quitta, almeno region e prioriti devono essere corretti
        // }

        if (!c.isConsistent()) {
            LoggerFactory.getLogger(GlobalOutbreakApp.class)
                    .warn("Cure not consistent");
        }

        IntStream.range(0, days).forEach(w -> {
            c.research();
            System.out.println(w + " " + c.getGlobalStatus());
        });

        r.forEach(System.out::println);
    }
}
