import robot.RobotCleaner;

/**
 * Třída CleanerRunner obsahuje pouze metodu main

 */
public class CleanerRunner {
    /**
     * Metoda inicializuje robota a načte vstupní plánek
     * Robot následně provedete výpočet
     * Nakonec uklídí odpadky
     * @param args - vstupní pole parametrů
     */
    public static void main(String[] args) {
        RobotCleaner robotCleaner = new RobotCleaner("planError");
        robotCleaner.computeCleaningPlan();
        robotCleaner.cleanArea();
    }
}
