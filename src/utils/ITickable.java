package utils;

/**
 * Rozhranie pre objekty, ktoré potrebujú byť aktualizované každý frame.
 */
public interface ITickable {
    /**
     * Metóda volaná každý frame (tick) pre aktualizáciu stavu objektu.
     */
    void tick();
}
