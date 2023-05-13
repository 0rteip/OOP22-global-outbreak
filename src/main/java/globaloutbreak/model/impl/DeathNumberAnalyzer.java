package globaloutbreak.model.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.model.api.DataAnalyzer;

/**
 * A DataAnalyzer based on the mainly cause of death.
 */
public final class DeathNumberAnalyzer implements DataAnalyzer<Integer> {

    private static String filePath = "diseases/deaths.csv";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Optional<Map<String, Integer>> causeOfDeahs = Optional.empty();

    /**
     * Create an analyzer based on data from deaths.csv.
     */
    public DeathNumberAnalyzer() {
        readCsv(filePath);
    }

    private void readCsv(final String confFile) {
        try (var dataFile = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(confFile), StandardCharsets.UTF_8))) {
            final List<List<String>> l = dataFile.lines().map(line -> Arrays.asList(line.split(","))).toList();
            final Map<String, Integer> cause = new HashMap<>();

            IntStream.range(0, l.get(0).size() - 1)
                    .forEach(i -> cause.put(l.get(0).get(i), Integer.parseInt(l.get(1).get(i))));

            this.causeOfDeahs = Optional.of(cause.entrySet().stream()
                    .collect(Collectors.toMap(
                            Entry::getKey,
                            Entry::getValue,
                            (oldV, newV) -> oldV, LinkedHashMap::new)));
        } catch (IOException e) {
            this.logger.warn("Error trying to read {}", filePath, e);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            this.logger.warn("Configuration file {} is malformed", filePath, e);
        }
    }

    @Override
    public void analyze(final Integer data) {
        this.causeOfDeahs.ifPresent(e -> e.entrySet().stream()
                .filter(el -> el.getValue() < data)
                .findFirst()
                .ifPresent(el -> {
                    action(el);
                    this.causeOfDeahs.get().remove(el.getKey());
                }));
    }

    @SuppressWarnings("PMD.SystemPrintln")
    private void action(final Entry<String, Integer> entry) {
        System.out.println("New statics show that " + "'malattia'" + " Killed more than " + entry.getValue()
                + " people world wide - more than '" + entry.getKey() + "'");
    }
}
