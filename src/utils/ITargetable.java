package utils;

import projectile.Projectile;

/**
 * Rozhranie pre objekty, ktoré môžu byť cieľmi projektilov (napr. balóniky).
 */
public interface ITargetable {
    /**
     * Aplikuje priame poškodenie.
     *
     * @param damage množstvo poškodenia
     */
    void takeDamage(int damage);

    /**
     * Aplikuje poškodenie vyplývajúce z projektilu.
     *
     * @param projectile zdroj poškodenia
     */
    void takeDamage(Projectile projectile);

    /**
     * Aplikuje vedľajší efekt z projektilu (napr. jed alebo ľad).
     *
     * @param projectile projektil so efektom
     */
    void absorbEffect(Projectile projectile);

    /**
     * Vracia aktuálnu pozíciu cieľa.
     *
     * @return pozícia cieľa
     */
    Vector2D getPosition();

    /**
     * Indikuje, či je objekt stále nažive.
     *
     * @return true ak je nažive, false ak nie
     */
    boolean isAlive();

    /**
     * Vracia hitbox objektu vo formáte [left, top, right, bottom].
     *
     * @return pole súradníc hitboxu
     */
    int[] getHitbox();
}
