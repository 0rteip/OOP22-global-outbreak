package globaloutbreak.model.api;

/**
 * 
 */

public interface Region {

    int getNumInfected();

    void incDeathPeople(int calculateNewDeaths);

    void incOrDecNuminfected(int i);

    float getUrban();

    ClimateImpl getClimateImpl();

    float getPoor();

    int getPopTot();

}
