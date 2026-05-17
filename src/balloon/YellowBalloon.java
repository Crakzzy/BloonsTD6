package balloon;

public class YellowBalloon extends Balloon {
    /**
     * Vytvorí žltý balón s prednastavenými štatistikami (rýchlosť, HP, obrázok, odmena, damage hráčovi pri dosiahnutí cieľa).
     */
    public YellowBalloon() {
        super((short)10, 50, "yellow", 20, 5);
    }

}
