package meetingrooms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MeetingRoomsController {

    private final static List<String> MENUS = new ArrayList<>(List.of(
            "0. Tárgyaló rögzítése",
            "1. Tárgyalók sorrendben",
            "2. Tárgyalók visszafelé sorrendben",
            "3. Minden második tárgyaló",
            "4. Területek",
            "5. Keresés pontos név alapján",
            "6. Keresés névtöredék alapján",
            "7. Keresés terület alapján",
            "8. Kilépés"));

    public static final int MIN_LENGTH_OF_MEETING_ROOM = 2;
    public static final int MIN_WIDTH_OF_MEETING_ROOM = 2;
    public static final int MIN_NUMBER_OF_MEETING_ROOMS = 1;

    private Scanner scanner = new Scanner(System.in);

//    private MeetingRoomsService meetingRoomsService =
//            new MeetingRoomsService(new InMemoryMeetingRoomsRepository());

    private MeetingRoomsService meetingRoomsService =
            new MeetingRoomsService(new MariadbMeetingRoomsRepository());

    public static void main(String[] args) {
        new MeetingRoomsController().start();
    }

    private void start() {
        printMenu();
        runMenu();
    }

    public void printMenu() {
        System.out.println("\n\t\t>>  Menü  <<");
        for (String s: MENUS) {
            System.out.println(s);
        }
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
        int menuNumber = Integer.parseInt(menuNumberStr);
        System.out.println("\t" + MENUS.get(menuNumber));
        switch (menuNumberStr) {
            case "0" : {
                addMeetingRooms();                
            }
            case "1" : {
                System.out.println(meetingRoomsService.meetingRoomsOrderByName());
//                List<String> meetingRooms = meetingRoomsService.meetingRoomsOrderByName();
//                for (String s: meetingRooms) {
//                    System.out.println(s);
//                }
                return;
            }
            case "2" : {
                System.out.println(meetingRoomsService.meetingRoomsOrderByNameReverse());
//                List<String> meetingRooms = meetingRoomsService.meetingRoomsOrderByNameReverse();
//                for (String s: meetingRooms) {
//                    System.out.println(s);
//                }
                return;
            }
            case "3" : {
                System.out.println(meetingRoomsService.meetingRooms());
                System.out.println(meetingRoomsService.printEvenNames());
                return;
            }

        }
        
        
//            case "3" : {
//                office.printEvenNames();
//                return;
//            }
//            case "4" : {
//                office.printAreas();
//                return;
//            }
//            case "5" : {
//                office.printMeetingRoomsWithName(getName());
//                return;
//            }
//            case "6" : {
//                office.printMeetingRoomsContains(getPart());
//                return;
//            }
//            case "7" : {
//                office.printAreasLargerThan(getArea());
//                return;
//            }

    }

    private void addMeetingRooms() {
        int number = getNumberOfMeetingRoom();
        for (int i = 0; i < number; i++) {
            String name = getNameOfMeetingRoom(i);
            int width = getWidthOfMeetingRoom(i);
            int length = getLengthOfMeetingRoom(i);
            meetingRoomsService.save(name, width, length);
        }
    }

    private int getNumberOfMeetingRoom() {
        Integer numberOfMeetingRoom;
        do {
            System.out.print("Kérem a tárgyalók számát: ");
            String numberStr = scanner.nextLine();
            numberOfMeetingRoom = getNumber(numberStr);
        } while (numberOfMeetingRoom == null || numberOfMeetingRoom < MIN_NUMBER_OF_MEETING_ROOMS);
        return numberOfMeetingRoom;
    }

    private Integer getNumber(String numberStr) {
        Integer numberOfMeetingRoom = null;
        try {
            numberOfMeetingRoom = Integer.parseInt(numberStr);
            if (numberOfMeetingRoom < MIN_NUMBER_OF_MEETING_ROOMS) {
                System.out.println("A tárgyalók száma nem lehet 1-nél kisebb: " + numberOfMeetingRoom);
            }
            return Integer.parseInt(numberStr);
        } catch (NumberFormatException nfe) {
            System.out.println("Hiba, ez nem szám! '" + numberStr + "'");
        }
        return numberOfMeetingRoom;
    }

    private String getNameOfMeetingRoom(int i) {
        String name;
        do {
            System.out.print((i + 1) + ". tárgyaló neve: ");
            name = scanner.nextLine();
            if (name.isBlank()) {
                System.out.println("A név nem lehet üres!");
            }
//            for (MeetingRoom mr: office.getMeetingRooms()) {
//                if (mr.getName().equals(name)) {
//                    System.out.println("Ez a név már létezik, válassz másikat: " + name);
//                    name = "";
//                }
//            }
        } while (name.isBlank());
        return name;
    }

    private int getLengthOfMeetingRoom(int i) {
        Integer lengthOfMeetingRoom;
        do {
            System.out.print((i + 1) + ". tárgyaló hossza: ");
            String lengthStr = scanner.nextLine();
            lengthOfMeetingRoom = getLength(lengthStr);
        } while (lengthOfMeetingRoom == null || lengthOfMeetingRoom < MIN_LENGTH_OF_MEETING_ROOM);
        return lengthOfMeetingRoom;
    }

    private Integer getLength(String lengthStr) {
        Integer lengthOfMeetingRoom = null;
        try {
            lengthOfMeetingRoom = Integer.parseInt(lengthStr);
            if (lengthOfMeetingRoom < MIN_LENGTH_OF_MEETING_ROOM) {
                System.out.println("A tárgyaló hossza nem lehet 2-nél kisebb: " + lengthOfMeetingRoom);
            }
            return Integer.parseInt(lengthStr);
        } catch (NumberFormatException nfe) {
            System.out.println("Hiba, ez nem szám! '" + lengthStr + "'");
        }
        return lengthOfMeetingRoom;
    }

    private int getWidthOfMeetingRoom(int i) {
        Integer widthOfMeetingRoom;
        do {
            System.out.print((i + 1) + ". tárgyaló szélessége: ");
            String widthStr = scanner.nextLine();
            widthOfMeetingRoom = getWidth(widthStr);
        } while (widthOfMeetingRoom == null || widthOfMeetingRoom < MIN_WIDTH_OF_MEETING_ROOM);
        return widthOfMeetingRoom;
    }

    private Integer getWidth(String widthStr) {
        Integer widthOfMeetingRoom = null;
        try {
            widthOfMeetingRoom = Integer.parseInt(widthStr);
            if (widthOfMeetingRoom < MIN_WIDTH_OF_MEETING_ROOM) {
                System.out.println("A tárgyaló szélesssége nem lehet 2-nél kisebb: " + widthOfMeetingRoom);
            }
            return Integer.parseInt(widthStr);
        } catch (NumberFormatException nfe) {
            System.out.println("Hiba, ez nem szám! '" + widthStr + "'");
        }
        return widthOfMeetingRoom;
    }




}
