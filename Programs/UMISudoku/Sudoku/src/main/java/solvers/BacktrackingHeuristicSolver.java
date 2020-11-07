package main.java.solvers;

import main.java.puzzle.Sudoku;
import main.java.puzzle.TablePosition;

import java.util.PriorityQueue;

/**
 * Třída rozšiřující abstraktní třídu Solver
 * Jedná se o řešení sudoku pomocí algoritmu Backtracking s jednoduchou heuristikou
 */
public class BacktrackingHeuristicSolver extends Solver {

    /**
     * Konstruktor třídy používá kontruktor od děděné třídy Solver
     * @param sudoku - instance třídy Sudoku
     */
    public BacktrackingHeuristicSolver(Sudoku sudoku) {
        super(sudoku);
    }

    /**
     * Implementace třídy solve
     * Zavolá pouze metodu solve s parametrem  prioritní fronty navíc
     * @param sudoku - instance třídy Sudoku, která má být vyřešena
     */
    @Override
    public void solve(Sudoku sudoku ){
        solve( sudoku, sudoku.getRemainingValuesWithPriority( ) );
    }

    /**
     * Metoda řeší zadanou instanci třídy Sudoku pomocí Backtrackingu s jednoduchou heuristikou
     * @param sudoku - aktuální instance třídy Sudoku, která má být vyřešena
     * @param remainingPositions - prioritní fronta s dosud nevyplněnými pozicemi v sudoku tabulce
     */
    public void solve( Sudoku sudoku, PriorityQueue<TablePosition> remainingPositions){
        this.solveCalls++;
        /*Výpočet pokračuje pouze pokud jsou nějaké nevyplněné pozice a dosud nebylo nalezeno řešení*/
        if ( remainingPositions.size( ) > 0 && this.solution == null ){
            /*Z prioritní fronty je vyjmuta další pozice v sudoku tabulce, kterou je potřeba vyplnit*/
            TablePosition tablePosition = remainingPositions.remove( );
            /*Iterace pro jednotlivé hodnoty 1-9*/
            for ( int newValue = 1 ; newValue <= 9 ; newValue++ ){
                this.isValidCalls++;
                /*Výpočet s aktuální hodnotou pokračuje, pouze pokud vložení nové hodnoty do tabulky neporuší pravidla sudoku*/
                if ( sudoku.isValidAdding( tablePosition.getRowIndex(), tablePosition.getColumnIndex(), newValue ) ){
                    /*Je vytvořena kopie aktuální instance sudoku*/
                    Sudoku sudokuCopy = sudoku.getSudokuCopy();
                    /*Nová hodnota je vložena do vytvořené kopie*/
                    sudokuCopy.addValue( tablePosition.getRowIndex(), tablePosition.getColumnIndex(), newValue );
                    if ( remainingPositions.size( ) == 0 ) {
                        /*Pokud je sudoku tabulka zcela vyplněná, pak se jedná o řešení*/
                        this.solution = sudokuCopy;
                    }
                    else {
                        /*Pokud ne, pak je rekurzivně zavolána metoda solve pro sudoku s novou hodnotou*/
                        solve( sudokuCopy, sudokuCopy.getRemainingValuesWithPriority() );
                    }
                }
            }
        }
    }

}
