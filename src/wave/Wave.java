package wave;

import java.util.ArrayList;

public record Wave(int wave, String description, ArrayList<EnemyGroup> groups, int goldForWave) { }