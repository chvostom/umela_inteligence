package main.java.puzzle;

/**
 * Třída pro uložení jednotlivých tabulek sudoku
 */
public class SudokuDatabase {

    /**
     * Kontruktor třídy - pouze pro inicializaci třídy
     */
    public SudokuDatabase( ) {

    }

    /**
     * Metoda vrátí tabulku sudoku podle jména
     * @param name - název tabulky sudoku
     * @return tabulkaSudoku
     * Aktuálně dostupná jména tabulek sudoku: ExampleSudoku, EmptySudoku, EasySudoku, HardSudoku
     */
    public int[][] getSudokuByName( String name ){
        return switch ( name ){
            case "EmptySudoku" -> this.getSudokuById( 0 );
            case "EasySudoku" -> this.getSudokuById( 1 );
            case "HardSudoku" -> this.getSudokuById( 2 );
            default -> this.getSudokuById( -1 );
        };
    }

    /**
     * Metoda vrátí tabulku sudoku podle id
     * @param id - id tabulky sudoku
     * @return tabulkaSudoku
     * Aktuálně dostupná id tabulek sudoku: 0,1,2,3
     */
    public int[][] getSudokuById( int id ) {
        return switch ( id ) {
            case 0 -> new int[][]{
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 0, 0, 0, 0, 0, 0, 0 }
                };
            case 1 -> new int[][]{
                        { 9, 4, 7, 2, 6, 8, 1, 5, 3 },
                        { 0, 0, 5, 4, 0, 7, 6, 0, 9 },
                        { 0, 6, 3, 9, 5, 1, 0, 8, 7 },
                        { 1, 3, 2, 0, 0, 0, 0, 9, 6 },
                        { 0, 0, 0, 0, 0, 0, 8, 0, 1 },
                        { 7, 8, 9, 0, 0, 0, 0, 3, 0 },
                        { 0, 9, 1, 5, 2, 6, 0, 4, 8 },
                        { 0, 0, 8, 1, 0, 0, 9, 0, 2 },
                        { 4, 2, 0, 8, 7, 9, 0, 1, 5 }
                };
            case 2 -> new int[][]{
                        { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
                        { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
                        { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
                        { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
                        { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
                        { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
                        { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
                        { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
                        { 0, 9, 0, 0, 0, 0, 4, 0, 0 }
                };
            default -> new int[][]{
                        { 5, 3, 0, 0, 7, 0, 0, 0, 0 },
                        { 6, 0, 0, 1, 9, 5, 0 ,0 ,0 },
                        { 0, 9, 8, 0, 0, 0, 0, 6, 0 },
                        { 8, 0, 0, 0, 6, 0, 0, 0, 3 },
                        { 4, 0, 0, 8, 0, 3, 0, 0, 1 },
                        { 7, 0, 0, 0, 2, 0, 0, 0, 6 },
                        { 0, 6, 0, 0, 0, 0, 2, 8, 0 },
                        { 0, 0, 0, 4, 1, 9, 0, 0, 5 },
                        { 0, 0, 0, 0, 8, 0, 0, 7, 9 }
                };
        };
    }

}
