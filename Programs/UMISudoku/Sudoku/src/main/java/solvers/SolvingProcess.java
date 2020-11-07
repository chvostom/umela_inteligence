package main.java.solvers;

import main.java.puzzle.Sudoku;

/**
 * Třída starající se o proces řešení sudoku
 */
public class SolvingProcess {

    /**
     * Konstruktor třídy - pouze pro inicializaci třídy
     */
    public SolvingProcess( ) {

    }

    /**
     * Metoda provede výpočet všemi dostupnými algoritmy
     * @param sudokuTable - tabulka sudoku
     * @param title - název řešení
     */
    public void runAllSolvingMethods( int[][] sudokuTable, String title ) {
        System.out.println("==============================================" + title +"===============================================");
        System.out.println("======================BACKTRACKING======================");
        this.sudokuSolvingProcess( sudokuTable, "BT" );
        System.out.println("======================BACKJUMPING=======================");
        this.sudokuSolvingProcess( sudokuTable, "BJ" );
        System.out.println("==============BACKTRACKING WITH HEURISTIC===============");
        this.sudokuSolvingProcess( sudokuTable, "BTH" );
        System.out.println("==============BACKJUMPING WITH HEURISTIC================");
        this.sudokuSolvingProcess( sudokuTable, "BJH" );
    }

    /**
     * Metoda provede výpočet pomocí vybrané metody a vypíše všechny výstupy
     * @param sudokuTable - tabulka sudoku
     * @param method - zkratka metody
     * Aktuálně jsou dostupné zkratky: BT pro Backtracking, BJ pro Backjumping, BTH pro BT s heuristikou, BJH pro BJ s heuristikou
     */
    public void sudokuSolvingProcess( int[][] sudokuTable, String method ){
        Sudoku sudoku = new Sudoku(sudokuTable);
        Solver solver = switch (method) {
            case "BJ" -> new BackjumpingSolver(sudoku);
            case "BTH" -> new BacktrackingHeuristicSolver(sudoku);
            case "BJH" -> new BackjumpingHeuristicSolver(sudoku);
            default -> new BacktrackingSolver(sudoku);
        };
        if ( solver.getSolution() == null ) {
            System.out.println("Zadané sudoku nemá řešení!");
        }
        else {
            System.out.println("Řešení:");
            solver.getSolution().printSudokuTable();
            System.out.println("Počet rekurzivních volání: " + solver.getSolveCalls());
            System.out.println("Počet zkoušených čísel: " + solver.getIsValidCalls());
            System.out.println("Čas řešení: " + solver.getTimeElapsed() + "ms");
        }
    }

}
