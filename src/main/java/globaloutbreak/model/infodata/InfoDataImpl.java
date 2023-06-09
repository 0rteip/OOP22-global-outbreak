package globaloutbreak.model.infodata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import globaloutbreak.model.cure.CureData;

/**
 * Class to manage Dna Points.
 */
public class InfoDataImpl implements InfoData {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private int dnaPoints;
    private int totalDeaths;
    private int totalInfected;
    private CureData cureData;

    /**
     * Constructor.
     */
    public InfoDataImpl() {
        this.dnaPoints = 1;
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
    public int getTotalDeaths() {
        return this.totalDeaths;
    }

    @Override
    public int getTotalInfected() {
        return this.totalInfected;
    }

    @Override
    public CureData getCureData() {
        return this.cureData;
    }

    @Override
    public void updateTotalDeathsAndInfected(final int totalDeaths, final int totalInfected) {
        this.totalDeaths = totalDeaths;
    }

    @Override
    public void updateCureStatus(final CureData cureData) {
        this.cureData = cureData;
    }
}
