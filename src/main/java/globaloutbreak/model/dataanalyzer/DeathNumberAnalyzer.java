package globaloutbreak.model.dataanalyzer;

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
import java.util.function.BiConsumer;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A DataAnalyzer based on the mainly cause of death.
 */
public final class DeathNumberAnalyzer implements DataAnalyzer<Integer> {

    private static String filePath = "diseases/deaths.csv";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Optional<Map<String, Integer>> causeOfDeahs = Optional.empty();
    private final BiConsumer<String, Integer> action;

    /**
     * Create an analyzer based on data from deaths.csv.
     * 
     * @param action
     *               The action to be performed when a Value passed to the
     *               {@code analyze} method is the lowest, not yet used, number of
     *               death
     */
    public DeathNumberAnalyzer(final BiConsumer<String, Integer> action) {
        this.action = action;
        readCsv(filePath);
    }

    private void readCsv(final String confFile) {
        try (var dataFile = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(confFile), StandardCharsets.UTF_8))) {
            final List<List<String>> l = dataFile.lines().map(line -> Arrays.asList(line.split(","))).toList();
            final Map<String, Integer> cause = new HashMap<>();

            IntStream.range(0, l.get(0).size())
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
                .filter(el -> el.getValue() <= data)
                .findFirst()
                .ifPresent(el -> {
                    performAction(el, this.action);
                    this.causeOfDeahs.get().remove(el.getKey());
                }));
    }

    private void performAction(final Entry<String, Integer> entry, final BiConsumer<String, Integer> c) {
        c.accept(entry.getKey(), entry.getValue());
    }
}
