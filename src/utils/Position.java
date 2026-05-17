package utils;

public class Position {
    private int row;
    private int col;

    /**
     * Vytvorí novú pozíciu v mriežke (riadok, stĺpec).
     *
     * @param row index riadku
     * @param col index stĺpca
     */
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Vracia index riadku pozície.
     *
     * @return index riadku
     */
    public int getRow() {
        return this.row;
    }

    /**
     * Vracia index stĺpca pozície.
     *
     * @return index stĺpca
     */
    public int getCol() {
        return this.col;
    }

}
