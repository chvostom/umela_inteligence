package main.java.solvers;

import main.java.puzzle.Sudoku;

/**
 * Abstraktní třída pro řešič sudoku
 */
abstract public class Solver {

    /**
     * Konstruktor třídy
     * @param sudoku - instance třídy Sudoku
     * Inicializuje všechny proměnné
     * Zavolá metodu solve, která vyřeší sudoku
     * Změří a uloží čas řešení
     */
    public Solver( Sudoku sudoku ){
        this.solution = null;
        this.solveCalls = 0;
        this.isValidCalls = 0;
        long start = System.currentTimeMillis();
        this.solve( sudoku );
        long finish = System.currentTimeMillis();
        this.timeElapsed = finish - start;
    }

    /**
     * Abstraktní metoda pro vyřešení sudoku
     * @param sudoku - instance třídy Sudoku, která má být vyřešena
     */
    public abstract void solve( Sudoku sudoku );

    /**
     * Getter pro počet rekurzivních volání funkce solve
     * @return solveCalls - očet rekurzivních volání funkce solve
     */
    public long getSolveCalls( ){
        return this.solveCalls;
    }

    /**
     * Getter pro počet vyzkoušených čísel
     * @return isValidCalls - počet vyzkoušených čísel
     */
    public long getIsValidCalls( ){
        return this.isValidCalls;
    }

    /**
     * Getter pro řešení sudoku
     * @return solution - řešení sudoku
     */
    public Sudoku getSolution( ){
        return this.solution;
    }

    /**
     * Getter pro čas řešení sudoku
     * @return timeElapsed - čas řešení sudoku
     */
    public long getTimeElapsed( ){
        return this.timeElapsed;
    }


    protected Sudoku solution;
    protected long solveCalls;
    protected long isValidCalls;
    protected final long timeElapsed;
}
