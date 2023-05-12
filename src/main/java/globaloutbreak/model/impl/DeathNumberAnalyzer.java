package globaloutbreak.model.impl;

import globaloutbreak.model.api.DataAnalyzer;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.LoggerFactory;

import de.siegmar.fastcsv.reader.CsvReader;

/**
 * A DataAnalyzer based on the mainly cause of death.
 */
public final class DeathNumberAnalyzer implements DataAnalyzer<Integer> {

    private Optional<Map<String, Integer>> causeOfDeahs = Optional.empty();

    /**
     * Create an analyzer based on data from deaths.csv
     */
    public DeathNumberAnalyzer() {
        readCsv(Path.of("src", "main", "resources", "diseases", "deaths.csv"));
    }

    private void readCsv(final Path pt) {

        try (CsvReader csvReader = CsvReader.builder().build(pt, StandardCharsets.UTF_8)) {

            final Map<String, Integer> cause = new HashMap<>();

            final List<List<String>> l = csvReader.stream()
                    .map(el -> el.getFields())
                    .toList();

            IntStream.range(0, l.get(0).size())
                    .forEach(i -> cause.put(l.get(0).get(i), Integer.parseInt(l.get(1).get(i))));

            this.causeOfDeahs = Optional.of(cause.entrySet().stream()
                    .collect(Collectors.toMap(
                            Entry::getKey,
                            Entry::getValue,
                            (oldV, newV) -> oldV, LinkedHashMap::new)));

        } catch (IOException e) {
            LoggerFactory.getLogger(getClass()).warn("Error trying to read deaths.csv", e);
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
