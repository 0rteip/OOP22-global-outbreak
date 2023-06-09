package globaloutbreak.dataanalyzer;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.charset.StandardCharsets;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.model.dataanalyzer.DataAnalyzer;
import globaloutbreak.model.dataanalyzer.DeathNumberAnalyzer;

class DataAnalyzerTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String filePath = "diseases/deaths.csv";

    /**
     * Test if the values used by {@code DeathNumberAnalyzer()} are used in the
     * correct order : from the lower to the higher.
     */
    @Test
    void testOrderedDeathValues() {
        logger.info("Starting testOrderedDeathValues()");
        final List<Integer> numberOfDeaths = new LinkedList<>(this.getOrderedNumberOfDeaths());
        final List<Integer> results = new LinkedList<>();
        final DataAnalyzer<Integer> analyzer = new DeathNumberAnalyzer(new BiConsumer<String, Integer>() {
            @Override
            public void accept(final String key, final Integer value) {
                results.add(value);
            }
        });
        numberOfDeaths.stream()
                .sorted()
                .forEach(deaths -> analyzer.analyze(deaths));
        assertIterableEquals(numberOfDeaths, results, "Death values are not returned in order");
        logger.info("testOrderedDeathValues() gone well");
    }

    private List<Integer> getOrderedNumberOfDeaths() {
        try (var dataFile = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(filePath), StandardCharsets.UTF_8))) {
            return dataFile.lines()
                    .skip(1)
                    .map(line -> Arrays.asList(line.split(",")))
                    .flatMap(el -> el.stream()
                            .map(Integer::parseInt))
                    .sorted()
                    .toList();
        } catch (IOException e) {
            logger.warn("Error trying to read {}", filePath, e);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            logger.warn("Configuration file {} is malformed", filePath, e);
        }
        return new LinkedList<>();
    }
}
