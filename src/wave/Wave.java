package wave;

import java.util.ArrayList;

/**
 * Reprezentuje jednu vlnu nepriateľov.
 *
 * @param wave číslo vlny
 * @param description krátky popis vlny
 * @param groups zoznam skupín nepriateľov, ktoré sa v tejto vlne objavia
 * @param goldForWave množstvo zlata, ktoré hráč získa po dokončení vlny
 */
public record Wave(int wave, String description, ArrayList<EnemyGroup> groups, int goldForWave) { }