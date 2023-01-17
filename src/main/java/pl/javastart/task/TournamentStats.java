package pl.javastart.task;

import java.io.*;
import java.util.*;

public class TournamentStats {
    private static final String STOP = "STOP";
    private static final int FIRST_NAME_COMPARATOR = 1;
    private static final int LAST_NAME_COMPARATOR = 2;
    private static final int RESULT_COMPARATOR = 3;
    private static final int SORT_ASC = 1;
    private static final int SORT_DESC = 2;

    void run(Scanner scanner) {
        // tutaj dodaj swoje rozwiązanie
        // użyj przekazanego scannera do wczytywania wartości
        String fileName = "stats.csv";
        try {
            List<Person> playersList = getDataFromUser(scanner);
            sortPlayersList(scanner, fileName, playersList);
        } catch (IOException e) {
            System.out.println("Nie udało się zapisać danych do pliku " + fileName);
        } catch (InputMismatchException e) {
            System.out.println("Podałeś nieprawidłową wartość");
        }
        scanner.close();
    }

    private void sortPlayersList(Scanner scanner, String fileName, List<Person> playersList) throws IOException {
        if (!playersList.isEmpty()) {
            Comparator<Person> comparator = chooseComparator(scanner);
            boolean reverse = chooseOrSortIncreasingly(scanner);
            if (reverse) {
                comparator = comparator.reversed();
            }
            playersList.sort(comparator);
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
                SORT_ASC, SORT_DESC);
        int sortOption = scanner.nextInt();
        scanner.nextLine();

        return sortOption == SORT_DESC;
    }

    private Comparator<Person> chooseComparator(Scanner scanner) throws InputMismatchException {
        System.out.printf("Po jakim parametrze posortować? (%d - imię, %d - nazwisko, %d - wynik)\n",
                FIRST_NAME_COMPARATOR, LAST_NAME_COMPARATOR, RESULT_COMPARATOR);
        int sortOption = scanner.nextInt();
        scanner.nextLine();

        return switch (sortOption) {
            case FIRST_NAME_COMPARATOR -> new FirstNameComparator();
            case LAST_NAME_COMPARATOR -> new LastNameComparator();
            case RESULT_COMPARATOR -> new ResultsComparator();
            default -> {
                System.out.println("Podałeś nieprawidłową wartość");
                yield null;
            }
        };
    }

    private List<Person> getDataFromUser(Scanner scanner) {
        List<Person> players = new ArrayList<>();
        String player;
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
