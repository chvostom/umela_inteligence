package main.java.puzzle;

/**
 * Třída reprezentuje konkrétní pozici v sudoku tabulce
 */
public class TablePosition {

    /**
     * Konstruktor třídy
     * @param rowIndex - index řádku tabulky sudoku (0-8)
     * @param columnIndex - index sloupce tabulky sudoku (0-8)
     * @param missingCoef - koeficient představující počet chybějících hodnot v sloupcích, řádcích a polích 3x3
     * Konstruktor pouze uloží vstupní parametry
     */
    public TablePosition( int rowIndex, int columnIndex, int missingCoef ){
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.missingCoef = missingCoef;
    }

    /**
     * Getter pro index řádku
     * @return rowIndex - index řádku tabulky sudoku
     */
    public int getRowIndex() {
        return this.rowIndex;
    }

    /**
     * Getter pro index sloupce
     * @return columnIndex - index sloupce tabulky sudoku
     */
    public int getColumnIndex() {
        return this.columnIndex;
    }

    /**
     * Getter pro koeficient chybějících hodnot
     * @return missingCoef - koeficient představující počet chybějících hodnot v sloupcích, řádcích a polích 3x3
     */
    public int getMissingCoef() {
        return missingCoef;
    }

    private final int rowIndex;
    private final int columnIndex;
    private final int missingCoef;

}
