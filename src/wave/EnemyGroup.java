package wave;

/**
 * Popisuje skupinu nepriateľov v rámci vlny.
 *
 * @param type typ nepriateľa (kód v JSON súbore)
 * @param count počet nepriateľov v skupine
 * @param spacing medzera (v tickoch) medzi spawnmi v skupine
 */
public record EnemyGroup(String type, int count, int spacing) { }