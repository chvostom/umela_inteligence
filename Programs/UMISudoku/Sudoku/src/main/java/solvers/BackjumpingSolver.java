package main.java.solvers;

import main.java.puzzle.Sudoku;
import main.java.puzzle.TablePosition;

import java.util.Deque;
import java.util.HashSet;

/**
 * Třída rozšiřující abstraktní třídu Solver
 * Jedná se o řešení sudoku pomocí algoritmu Backjumping
 */
public class BackjumpingSolver extends Solver {

    /**
     * Konstruktor třídy používá kontruktor od děděné třídy Solver
     * @param sudoku - instance třídy Sudoku
     */
    public BackjumpingSolver(Sudoku sudoku) {
        super(sudoku);
    }

    /**
     * Implementace třídy solve
     * Zavolá pouze metodu solve s parametrem fronty navíc
     * @param sudoku - instance třídy Sudoku, která má být vyřešena
     */
    @Override
    public void solve( Sudoku sudoku ){
        solve( sudoku, sudoku.getRemainingValues( ) );
    }

    /**
     * Metoda řeší zadanou instanci třídy Sudoku pomocí Backjumpingu
     * @param sudoku - aktuální instance třídy Sudoku, která má být vyřešena
     * @param remainingPositions - fronta s dosud nevyplněnými pozicemi v sudoku tabulce
     * @return conflicts - konfliktní množina
     */
    public HashSet<Integer> solve(Sudoku sudoku, Deque<TablePosition> remainingPositions){
        this.solveCalls++;
        /*Výpočet pokračuje pouze pokud jsou nějaké nevyplněné pozice a dosud nebylo nalezeno řešení*/
        if ( remainingPositions.size( ) > 0 && this.solution == null ){
            /*Z fronty je vyjmuta další pozice v sudoku tabulce, kterou je potřeba vyplnit*/
            TablePosition tablePosition = remainingPositions.pop( );
            /*Je vytvořena prázdná hash kolekce pro konfliktní množinu*/
            HashSet<Integer> conflicts = new HashSet<>();
            /*Iterace pro jednotlivé hodnoty 1-9*/
            for ( int newValue = 1 ; newValue <= 9 ; newValue++ ){
                this.isValidCalls++;
                /*Je vytvořena prázdná hash kolekce pro nové přírůstky konfliktní množiny*/
                HashSet<Integer> newConflicts = new HashSet<>();
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
                        /*Do kolekce newConflicts jsou uloženy konfliktní prvky*/
                        newConflicts = solve( sudokuCopy, sudokuCopy.getRemainingValues() );
                    }
                }
                else {
                    /*Pokud vložení nové hodnoty poruší pravidla sudoku, uložíme konfliktní prvky pouze pro aktuální hodnotu*/
                    newConflicts = sudoku.getConflicts(tablePosition.getRowIndex(), tablePosition.getColumnIndex(), newValue );
                }
                /*Pokud kolekce obsahuje index aktuální pozice, pak přidáme všechny její prvky (kromě aktuální pozice) do kolekce conflicts*/
                if ( newConflicts.contains( 10 * tablePosition.getRowIndex( ) + tablePosition.getColumnIndex( ) ) ){
                    newConflicts.remove( 10 * tablePosition.getRowIndex( ) + tablePosition.getColumnIndex( ) );
                    conflicts.addAll( newConflicts );
                }
                else {
                    /*Pokud ne, pak vrátíme kolekci newConflicts a výpočet v aktuální větvi rekurze končí*/
                    return newConflicts;
                }
            }
            /*Po poslední iteraci je vrácena kolekce conflicts a výpočet v aktuální větvi rekurze končí*/
            return conflicts;
        }
        return new HashSet<>();
    }

}
