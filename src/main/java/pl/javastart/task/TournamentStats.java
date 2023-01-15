package pl.javastart.task;

import java.io.*;
import java.util.*;

public class TournamentStats {
    public static final String STOP = "STOP";
    public static final int FIRST_NAME_COMPARATOR = 1;
    public static final int LAST_NAME_COMPARATOR = 2;
    public static final int RESULT_COMPARATOR = 3;
    public static final int SORT_INCREASINGLY = 1;
    public static final int SORT_DECREASINGLY = 2;

    void run(Scanner scanner) {
        // tutaj dodaj swoje rozwiązanie
        // użyj przekazanego scannera do wczytywania wartości
        String fileName = "stats.csv";
            try {
                List<Person> playersList = getDataFromUser(scanner);
                sortPlayersList(scanner, fileName, playersList);
            } catch (IOException e) {
                System.out.println("Nie udało się zapisać danych do pliku " + fileName);
            } catch (InputMismatchException | ClassCastException e) {
                System.out.println("Podałeś nieprawidłową wartość");
            }
        scanner.close();
    }

    private void sortPlayersList(Scanner scanner, String fileName, List<Person> playersList) throws IOException {
        if (!playersList.isEmpty()) {
            Comparator<Person> comparator = chooseComparator(scanner);
            boolean orIncreasingly = chooseOrSortIncreasingly(scanner);
            playersList.sort(comparator);
            if (!orIncreasingly) {
                Collections.reverse(playersList);
            }
            saveToCsv(fileName, playersList);
        } else {
            System.out.println("Lista zawodników jest pusta");
        }
    }

    private void saveToCsv(String fileName, List<Person> list) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        try (FileWriter fileWriter = new FileWriter(fileName);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            for (Person player : list) {
                bufferedWriter.write(player.toString());
                bufferedWriter.newLine();
            }
            System.out.println("Dane posortowano i zapisano do pliku " + fileName);
        }
    }

    public static boolean chooseOrSortIncreasingly(Scanner scanner) {
        System.out.printf("Sortować rosnąco czy malejąco? (%d - rosnąco, %d - malejąco)\n",
                SORT_INCREASINGLY, SORT_DECREASINGLY);
        int sortOption = scanner.nextInt();
        scanner.nextLine();

        switch (sortOption) {
            case SORT_INCREASINGLY:
                return true;
            case SORT_DECREASINGLY:
                return false;
            default:
                System.out.println("Podałeś nieprawidłową wartość");
                return false;
        }
    }

    private Comparator<Person> chooseComparator(Scanner scanner) throws InputMismatchException {
        Comparator comparator = null;
        System.out.printf("Po jakim parametrze posortować? (%d - imię, %d - nazwisko, %d - wynik)\n",
                FIRST_NAME_COMPARATOR, LAST_NAME_COMPARATOR, RESULT_COMPARATOR);
        int sortOption = scanner.nextInt();
        scanner.nextLine();

        switch (sortOption) {
            case FIRST_NAME_COMPARATOR:
                comparator = new FirstNameComparator();
                break;
            case LAST_NAME_COMPARATOR:
                comparator = new LastNameComparator();
                break;
            case RESULT_COMPARATOR:
                comparator = new ResultsComparator();
                break;
            default:
                System.out.println("Podałeś nieprawidłową wartość");
        }
        return comparator;
    }

    private List<Person> getDataFromUser(Scanner scanner) {
        List<Person> players = new ArrayList<>();
        String player = null;
        do {
            System.out.println("Podaj wynik kolejnego gracza (lub stop)");
            player = scanner.nextLine();
            String[] split = player.split(" ");
            if (split.length > 1) {
                String firstName = split[0];
                String lastName = split[1];
                int result = Integer.parseInt(split[2]);
                Person playerPerson = new Person(firstName, lastName, result);
                players.add(playerPerson);
            }
        } while (!player.equalsIgnoreCase(STOP));
        return players;
    }
}
