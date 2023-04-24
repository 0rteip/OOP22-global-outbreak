package globaloutbreak.model.impl;

import globaloutbreak.model.api.DataComparator;

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

import de.siegmar.fastcsv.reader.CsvReader;

/**
 * 
 */
public final class DeathDataMessageComparator implements DataComparator<String, Integer> {

    private Optional<Map<String, Integer>> causeOfDeahs = Optional.empty();

    /**
     * A comparator based on the mainly cause of death.
     */
    public DeathDataMessageComparator() {
        readCsv(Path.of("src", "main", "resources", "diseases", "deaths.csv"));
    }

    @SuppressWarnings("PMD.SystemPrintln")
    private void readCsv(final Path pt) {

        try (CsvReader csvReader = CsvReader.builder().build(pt, StandardCharsets.UTF_8)) {

            final Map<String, Integer> cause = new HashMap<>();

            final List<List<String>> l = csvReader.stream()
                    .map(el -> el.getFields())
                    .toList();

            IntStream.range(0, l.get(0).size())
                    .forEach(i -> cause.put(l.get(0).get(i), Integer.parseInt(l.get(1).get(i))));

            this.causeOfDeahs = Optional.of(cause.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldV, newV) -> oldV, LinkedHashMap::new)));

        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    @SuppressWarnings("PMD.SystemPrintln")
    public void update(final Integer data) {
        this.causeOfDeahs.ifPresent(e -> e.entrySet().stream()
                .filter(el -> el.getValue() < data)
                .findFirst()
                .ifPresent(el -> {
                    action(el);
                    removeEntry(el);
                }));
    }

    @Override
    @SuppressWarnings("PMD.SystemPrintln")
    public void action(final Entry<String, Integer> entry) {
        System.out.println(entry);
    }

    private void removeEntry(final Entry<String, Integer> entry) {
        this.causeOfDeahs.get().remove(entry.getKey());
    }

}
