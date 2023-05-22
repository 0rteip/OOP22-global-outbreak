package globaloutbreak.model.api;

/**
 * This class is 
 */
public interface Region {
    
    /**
     * This method increase the number of death people.
     * 
     * @param dead the number of new death people.
     * 
     */
    void incDeathPeople(Integer dead);

    /**
     * This method increase(or decrease) the number of infected people.
     * 
     * @param infected the number of new infected people.
     * 
     */
    void incOrDecInfectedPeople(Integer infected);

    /**
     * This method increase(or decrease) the number of cared people.
     * 
     * @param cared the number of new cared people
     *  
     */
    void incOrDecCuredPeople(Integer cared);
}
