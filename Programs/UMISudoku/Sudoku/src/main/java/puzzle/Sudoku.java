package main.java.puzzle;

import java.util.*;

/**
 * Třída pro veškeré funkcionality pro jednotlivé tabulky sudoku
 */
public class Sudoku {

    /**
     * Konstruktor třídy
     * @param table - tabulka sudoku
     * Konstruktor překopíruje zadané pole a spočte chybějící hodnoty pro jednotlivé řádky, sloupce a 3x3 tabulky
     */
    public Sudoku( int[][] table ){
        this.sudokuTable = new int[9][9];
        this.missingValues = new int[9][3];
        for ( int i = 0 ; i < 9 ; i++ ){
            missingValues[i][0] = 0;
            missingValues[i][1] = 0;
            missingValues[i][2] = 0;
        }
        for ( int rowIndex = 0 ; rowIndex < 9 ; rowIndex++ ){
            for ( int columnIndex = 0 ; columnIndex < 9 ; columnIndex++ ){
                this.sudokuTable[rowIndex][columnIndex] = table[rowIndex][columnIndex];
                if ( this.sudokuTable[rowIndex][columnIndex] == 0 ) {
                    int boxIndex = ( ( int )( rowIndex / 3 ) * 3 ) + ( int ) ( columnIndex / 3 );
                    missingValues[rowIndex][0] = missingValues[rowIndex][0] + 1;
                    missingValues[columnIndex][1] = missingValues[columnIndex][1] + 1;
                    missingValues[boxIndex][2] = missingValues[boxIndex][2] + 1;
                }
            }
        }
    }

    /**
     * Metoda přidá novou hodnotu do tabulky sudoku
     * @param rowIndex - index řádku tabulky sudoku
     * @param columnIndex - index sloupce tabulky sudoku
     * @param value - nově přidaná hodnota
     * Kromě přidání hodnoty metoda aktualizuje počty chybějících hodnot pro změněný řádek, sloupec a 3x3 čtverec
     */
    public void addValue( int rowIndex, int columnIndex, int value ){
        this.sudokuTable[rowIndex][columnIndex] = value;
        int boxIndex = ( ( int )( rowIndex / 3 ) * 3 ) + ( int ) ( columnIndex / 3 );
        missingValues[rowIndex][0] = missingValues[rowIndex][0] - 1;
        missingValues[columnIndex][1] = missingValues[columnIndex][1] - 1;
        missingValues[boxIndex][2] = missingValues[boxIndex][2] - 1;
    }

    /**
     * Metoda zkontroluje, zda přidání nové hodnoty neporušuje pravidla sudoku
     * @param rowIndex - index řádku tabulky sudoku
     * @param columnIndex - index sloupce tabulky sudoku
     * @param value - nově přidaná hodnota
     * @return boolean - true (přidání pravidla neporuší) nebo false(porušení pravidel)
     */
    public boolean isValidAdding( int rowIndex, int columnIndex, int value ) {
        if ( this.sudokuTable[rowIndex][columnIndex] != 0 ) {
            return false;
        }
        this.sudokuTable[rowIndex][columnIndex] = value;
        if ( !checkRow( rowIndex ) || !checkColumn( columnIndex ) || !checkBox( ( ( int )( rowIndex / 3 ) * 3 ) + ( int ) ( columnIndex / 3 ) ) ){
            this.sudokuTable[rowIndex][columnIndex] = 0;
            return false;
        }
        this.sudokuTable[rowIndex][columnIndex] = 0;
        return true;
    }

    /**
     * Metoda kontroluje zadanou hodnotu, zda náleží oboru hodnot (0-9)
     * @param number - kontrolovaná hodnota
     * @return true (0 <= number <= 9) nebo false
     */
    public boolean checkDomain( int number ) {
        return  ( ( number >= 0 ) && ( number <= 9 ) ) ;
    }

    /**
     * Metoda kontroluje, zda řádek neporušuje pravidla sudoku (tedy zda obsahuje unikátní hodnoty 1-9 případně 0)
     * @param rowIndex - index řádku tabulky sudoku
     * @return true (hodnoty spadají do oboru hodnot a jsou unikátní) nebo false
     */
    public boolean checkRow( int rowIndex ){
        List<Integer> items = new ArrayList<>();
        for ( int columnIndex = 0 ; columnIndex < 9 ; columnIndex++){
            if ( !checkDomain( this.sudokuTable[rowIndex][columnIndex] ) ) {
                return false;
            }
            if ( this.sudokuTable[rowIndex][columnIndex] != 0 ){
                items.add( this.sudokuTable[rowIndex][columnIndex] );
            }
        }
        return checkUniqueItems( items );
    }

    /**
     * Metoda kontroluje, zda sloupec neporušuje pravidla sudoku (tedy zda obsahuje unikátní hodnoty 1-9 případně 0)
     * @param columnIndex - index sloupce tabulky sudoku
     * @return true (hodnoty spadají do oboru hodnot a jsou unikátní) nebo false
     */
    public boolean checkColumn( int columnIndex ){
        List<Integer> items = new ArrayList<>();
        for ( int rowIndex = 0 ; rowIndex < 9 ; rowIndex++){
            if ( !checkDomain( this.sudokuTable[rowIndex][columnIndex] ) ) {
                return false;
            }
            if ( this.sudokuTable[rowIndex][columnIndex] != 0 ){
                items.add( this.sudokuTable[rowIndex][columnIndex] );
            }
        }
        return checkUniqueItems( items );
    }

    /**
     * Metoda kontroluje, zda 3x3 čtverec neporušuje pravidla sudoku (tedy zda obsahuje unikátní hodnoty 1-9 případně 0)
     * @param boxIndex - index 3x3 čtverce (0-8) tabulky sudoku
     * @return true (hodnoty spadají do oboru hodnot a jsou unikátní) nebo false
     */
    public boolean checkBox( int boxIndex ){
        List<Integer> items = new ArrayList<>();
        for (int rowIndex = ( int )( boxIndex / 3 ) * 3 ; rowIndex < ( ( int )( boxIndex / 3 ) * 3 ) + 3 ; rowIndex++ ){
            for ( int columnIndex = ( boxIndex % 3 ) * 3 ; columnIndex < ( ( boxIndex % 3 ) * 3 ) + 3 ; columnIndex++ ){
                if ( !checkDomain( this.sudokuTable[rowIndex][columnIndex] ) ) {
                    return false;
                }
                if ( this.sudokuTable[rowIndex][columnIndex] != 0 ){
                    items.add( this.sudokuTable[rowIndex][columnIndex] );
                }
            }
        }
        return checkUniqueItems( items );
    }

    /**
     * Metoda kontroluje, zda list neobsahuje nějakou hodnotu vícekrát
     * @param items - list hodnot
     * @return true (hodnoty jsou unikátní) nebo false
     */
    public boolean checkUniqueItems( List<Integer> items ){
        HashSet<Integer> itemsSet = new HashSet<>();
        for ( Integer item : items ){
            if ( itemsSet.contains( item ) ){
                return false;
            }
            else {
                itemsSet.add( item );
            }
        }
        return true;
    }

    /**
     * Metoda vypisuje tabulku sudoku po jednotlivých řádcích
     */
    public void printSudokuTable( ){
        for ( int rowIndex = 0 ; rowIndex < 9 ; rowIndex++ ){
            StringBuilder row = new StringBuilder();
            for ( int columnIndex = 0 ; columnIndex < 9 ; columnIndex++ ){
                row.append(this.sudokuTable[rowIndex][columnIndex]);
                if ( columnIndex < 8 ) {
                    row.append(" ");
                }
            }
            System.out.println(row);
        }
    }

    /**
     * Metoda vrací frontu s prázdnými pozicemi v tabulce
     * @return remainingPositions - fronta s jednotlivými pozicemi
     * Pozice nejsou nijak seřazeny
     */
    public Deque<TablePosition> getRemainingValues( ){
        Deque<TablePosition> remainingPositions = new LinkedList<>();
        for ( int rowIndex = 0 ; rowIndex < 9 ; rowIndex++ ){
            for ( int columnIndex = 0 ; columnIndex < 9 ; columnIndex++ ){
                if ( this.sudokuTable[rowIndex][columnIndex] == 0 ){
                    remainingPositions.push( new TablePosition( rowIndex, columnIndex, 0 ) );
                }
            }
        }
        return remainingPositions;
    }

    /**
     * Metoda vrací prioritní frontu s prázdnými pozicemi v tabulce
     * @return remainingPositions - fronta s jednotlivými pozicemi
     * Pozice jsou seřazeny podle počtu chybějících hodnot v daném řádku, sloupci a 3x3 čtverci
     */
    public PriorityQueue<TablePosition> getRemainingValuesWithPriority( ){
        Comparator<TablePosition> tablePositionLengthComparator = new Comparator<TablePosition>() {
            @Override
            public int compare(TablePosition t1, TablePosition t2) {
                return t1.getMissingCoef() - t2.getMissingCoef();
            }
        };
        PriorityQueue<TablePosition> remainingPositions = new PriorityQueue<TablePosition>(tablePositionLengthComparator);
        for ( int rowIndex = 0 ; rowIndex < 9 ; rowIndex++ ){
            for ( int columnIndex = 0 ; columnIndex < 9 ; columnIndex++ ){
                if ( this.sudokuTable[rowIndex][columnIndex] == 0 ){
                    remainingPositions.add( new TablePosition( rowIndex, columnIndex, this.getMissingCoef(rowIndex, columnIndex) ) );
                }
            }
        }
        return remainingPositions;
    }

    /**
     * Metoda vrací koeficient představující počet chybějících hodnot v sloupcích, řádcích a polích 3x3
     * @param rowIndex - index řádku tabulky sudoku
     * @param columnIndex - index sloupce tabulky sudoku
     * @return missingValuesCount - počet chybějících hodnot pro daný sloupec, řádek a pole 3x3
     */
    public int getMissingCoef( int rowIndex, int columnIndex ){
        int boxIndex = ( ( int )( rowIndex / 3 ) * 3 ) + ( int ) ( columnIndex / 3 );
        return missingValues[rowIndex][0] + missingValues[columnIndex][1] + missingValues[boxIndex][2] ;
    }

    /**
     * Metoda vrací hash kolekci s konflikty (index pozice, která již obsahuje danou hodnotu)
     * @param rowIndex - index řádku tabulky sudoku
     * @param columnIndex - index sloupce tabulky sudoku
     * @param value - hodnota, pro kterou jsou hledány konflikty
     * @return conflicts - hash kolekce s konflikty
     */
    public HashSet<Integer> getConflicts( int rowIndex, int columnIndex, int value ) {
        HashSet<Integer> conflicts = new HashSet<>();
        conflicts.add( 10 * rowIndex + columnIndex );
        for ( int cI = 0 ; cI < 9 ; cI++){
            if ( this.sudokuTable[rowIndex][cI] == value ){
                conflicts.add( 10 * rowIndex +  cI );
            }
        }
        for ( int rI = 0 ; rI < 9 ; rI++){
            if ( this.sudokuTable[rI][columnIndex] == value ){
                conflicts.add( 10 * rI + columnIndex );
            }
        }
        int boxIndex = ( ( int )( rowIndex / 3 ) * 3 ) + ( int ) ( columnIndex / 3 );
        for (int rI = ( int )( boxIndex / 3 ) * 3 ; rI < ( ( int )( boxIndex / 3 ) * 3 ) + 3 ; rI++ ){
            for ( int cI = ( boxIndex % 3 ) * 3 ; cI < ( ( boxIndex % 3 ) * 3 ) + 3 ; cI++ ){
                if ( this.sudokuTable[rI][cI] == value ){
                    conflicts.add( 10 * rI + cI );
                }
            }
        }
        return conflicts;
    }

    /**
     * Metoda zkopíruje a vrátí instanci třídy
     * @return Sudoku - instance třídy sudoku
     */
    public Sudoku getSudokuCopy( ){
        return new Sudoku( this.sudokuTable );
    }

    private final int[][] sudokuTable;
    private final int[][] missingValues;

}
