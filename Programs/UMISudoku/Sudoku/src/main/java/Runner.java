package main.java;

import main.java.puzzle.SudokuDatabase;
import main.java.solvers.SolvingProcess;

import java.util.Arrays;
import java.util.List;

/**
 * Třída s metodou main
 */
public class Runner {

    /**
     * Metoda main, která spustí všechny potřebné výpočty
     * @param args - vstupní argumenty
     */
    public static void main(String[] args){
        SudokuDatabase sudokuDatabase = new SudokuDatabase();
        SolvingProcess solvingProcess = new SolvingProcess();
        List<String> sudokuNames = Arrays.asList("InitialRun", "ExampleSudoku", "EmptySudoku", "EasySudoku", "HardSudoku" );
        for ( String name : sudokuNames ){
            solvingProcess.runAllSolvingMethods(sudokuDatabase.getSudokuByName( name ), name );
        }
        System.out.println("=======================================================================================================");
    }

}
