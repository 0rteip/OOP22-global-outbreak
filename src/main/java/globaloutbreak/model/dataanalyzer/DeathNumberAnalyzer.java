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
public final class DeathNumberAnalyzer implements DataAnalyzer<Long> {

    private static final String FILE_PATH = "diseases/deaths.csv";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private Optional<Map<String, Long>> causeOfDeahs = Optional.empty();
    private final BiConsumer<String, Long> action;

    /**
     * Create an analyzer based on data from deaths.csv.
     * 
     * @param action
     *               The action to be performed when a Value passed to the
     *               {@code analyze} method is the lowest, not yet used, number of
     *               death
     */
    public DeathNumberAnalyzer(final BiConsumer<String, Long> action) {
        this.action = action;
        this.readCsv();
    }

    private void readCsv() {
        try (var dataFile = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream(FILE_PATH), StandardCharsets.UTF_8))) {
            final List<List<String>> l = dataFile.lines().map(line -> Arrays.asList(line.split(","))).toList();
            final Map<String, Long> cause = new HashMap<>();

            IntStream.range(0, l.get(0).size())
                    .forEach(i -> cause.put(l.get(0).get(i), Long.parseLong(l.get(1).get(i))));

            this.causeOfDeahs = Optional.of(cause.entrySet().stream()
                    .collect(Collectors.toMap(
                            Entry::getKey,
                            Entry::getValue,
                            (oldV, newV) -> oldV, LinkedHashMap::new)));
        } catch (IOException e) {
            this.logger.warn("Error trying to read {}", FILE_PATH, e);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            this.logger.warn("Configuration file {} is malformed", FILE_PATH, e);
        }
    }

    @Override
    public void analyze(final Long data) {
        this.causeOfDeahs.ifPresent(e -> e.entrySet().stream()
                .filter(el -> el.getValue() <= data)
                .min((e0, e1) -> Long.compare(e0.getValue(), e1.getValue()))
                .ifPresent(el -> {
                    performAction(el, this.action);
                    this.causeOfDeahs.get().remove(el.getKey());
                }));
    }

    private void performAction(final Entry<String, Long> entry, final BiConsumer<String, Long> c) {
        c.accept(entry.getKey(), entry.getValue());
    }
}
