package globaloutbreak.model.infodata;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.model.cure.CureData;

/**
 * Class to manage Dna Points.
 */
public class InfoDataImpl implements InfoData {

    private static final int BASE_DEATHS_RANGE = 500_000;
    private static final int BASE_INFECTED_RANGE = 500_000;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private long deathsLimit;
    private long infectedLimit;
    private int dnaPoints;
    private long totalDeaths;
    private long totalInfected;
    private long totalPopulation;
    private CureData cureData;
    private Random random = new Random();

    /**
     * Constructor.
     */
    public InfoDataImpl(final int totalPopulation) {
        this.dnaPoints = 1;
        this.infectedLimit = BASE_INFECTED_RANGE;
        this.deathsLimit = BASE_DEATHS_RANGE;
        this.totalPopulation = totalPopulation;
    }

    /**
     * Increase Dna Points.
     * 
     * @param points
     *               points to be added.
     */
    @Override
    public void increasePoints(final int points) {
        if (checkIfPositive(points)) {
            this.dnaPoints += points;
        } else {
            throw new IllegalArgumentException("The value to be added must be positive.");
        }
    }

    /**
     * Decrease Dna Points.
     * 
     * @param points
     *               points to be decreased.
     */
    @Override
    public void decreasePoints(final int points) {
        if (checkIfPositive(points)) {
            this.dnaPoints -= points;
        } else {
            throw new IllegalArgumentException("The value to be decreased must be positive.");
        }
    }

    /**
     * @return
     *         the points owned.
     */
    public int getPoints() {
        return this.dnaPoints;
    }

    /**
     * check if a value is greater than 1.
     * 
     * @param value
     *              the value to be checked.
     * @return
     *         true if the value is greater than 1, false otherwise.
     */
    private boolean checkIfPositive(final int value) {
        if (value < 1) {
            logger.error("The value to be added or decreased must be positive.");
            return false;
        }
        return true;
    }

    @Override
    public long getTotalDeaths() {
        return this.totalDeaths;
    }

    @Override
    public long getTotalInfected() {
        return this.totalInfected;
    }

    @Override
    public long getTotalPopulation(){
        return this.totalPopulation;
    }

    @Override
    public CureData getCureData() {
        return this.cureData;
    }

    @Override
    public void updateTotalDeathsAndInfected(final long totalDeaths, final long totalInfected) {
        this.totalDeaths = totalDeaths;
        if(this.totalDeaths > this.deathsLimit){
            this.increasePoints(random.nextInt(3) + 1);
            this.deathsLimit += BASE_DEATHS_RANGE;
        }
        if(this.totalInfected > this.infectedLimit){
            this.increasePoints(random.nextInt(3) + 1);
            this.infectedLimit += BASE_INFECTED_RANGE;
        }
    }

    @Override
    public void updateCureData(final CureData cureData) {
        this.cureData = cureData;
    }

    @Override
    public void updateDeaths(final long deaths){
        this.totalDeaths += deaths;
    }
}
