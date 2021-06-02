package meetingrooms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeetingRoomsController {

    public static final String MENU =
            "0. Tárgyaló rögzítése\n" +
            "1. Tárgyalók névsorrendben\n" +
            "2. Tárgyalók név alapján visszafelé sorrendben\n" +
            "3. Minden második tárgyaló\n" +
            "4. Területek\n" +
            "5. Keresés pontos név alapján\n" +
            "6. Keresés névtöredék alapján\n" +
            "7. Keresés terület alapján\n" +
            "8. Kilépés";

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        new MeetingRoomsController().start();
    }

    private void start() {
        printMenu();
        runMenu();
    }

    public void printMenu() {
        System.out.println("\n\t\t>>  Menü  <<");
        System.out.println(MENU);
    }

    public void runMenu() {
        String menuNumberStr;
        do {
            System.out.print("Menüpont száma: ");
            menuNumberStr= scanner.nextLine();
            if (List.of("0","1","2","3","4","5","6","7","8").contains(menuNumberStr)) {
                implementMenuPoint(menuNumberStr);
                if (!menuNumberStr.equals("8")) {
                    printMenu();
                }
            }
            else {
                System.out.println("Nincs ilyen menüpont!");
            }
        } while (!menuNumberStr.equals("8"));
    }

    private void implementMenuPoint(String menuNumberStr) {

    }


}
