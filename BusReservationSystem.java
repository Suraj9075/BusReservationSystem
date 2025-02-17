package busreservation;

import java.util.*;

public class BusReservationSystem {
    
    private static final int MAX_SEATS = 5;  // Seat limit for each bus
    private HashMap<Integer, List<String>> busSeats = new HashMap<>();
    private HashMap<Integer, Queue<String>> waitingList = new HashMap<>();
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BusReservationSystem system = new BusReservationSystem();

        while (true) {
            System.out.println("\n=== 🚌 Bus Reservation System ===");
            System.out.println("1️⃣  Book Ticket");
            System.out.println("2️⃣  Cancel Ticket");
            System.out.println("3️⃣  Check Availability");
            System.out.println("4️⃣  Show Waiting List");
            System.out.println("5️⃣  Exit");
            System.out.print("🔹 Choose an option: ");

            int choice = scanner.nextInt();
            if (choice == 5) {
                System.out.println("🔴 Exiting Bus Reservation System. Thank you!");
                break;
            }

            System.out.print("🔹 Enter Bus Number: ");
            int busNumber = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            system.busSeats.putIfAbsent(busNumber, new ArrayList<>());
            system.waitingList.putIfAbsent(busNumber, new LinkedList<>());
            List<String> seats = system.busSeats.get(busNumber);
            Queue<String> waitQueue = system.waitingList.get(busNumber);

            switch (choice) {
                case 1:  // Book ticket
                    System.out.print("🔹 Enter Passenger Name: ");
                    String passenger = scanner.nextLine();
                    if (seats.size() < MAX_SEATS) {
                        seats.add(passenger);
                        System.out.println("✅ Ticket booked for " + passenger + " on Bus " + busNumber);
                    } else {
                        waitQueue.add(passenger);
                        System.out.println("⚠️ Bus full! " + passenger + " added to waiting list.");
                    }
                    break;

                case 2:  // Cancel ticket
                    System.out.print("🔹 Enter Passenger Name to Cancel: ");
                    String cancelPassenger = scanner.nextLine();
                    if (seats.remove(cancelPassenger)) {
                        System.out.println("❌ Ticket canceled for " + cancelPassenger);
                        if (!waitQueue.isEmpty()) {
                            String nextPassenger = waitQueue.poll();
                            seats.add(nextPassenger);
                            System.out.println("✅ " + nextPassenger + " moved from waiting list to booked seat.");
                        }
                    } else {
                        System.out.println("❌ No booking found for " + cancelPassenger);
                    }
                    break;

                case 3:  // Check seat availability
                    System.out.println("🚍 Bus " + busNumber + " - Available seats: " + (MAX_SEATS - seats.size()));
                    break;

                case 4:  // Show waiting list
                    System.out.println("⏳ Waiting list for Bus " + busNumber + ": " + (waitQueue.isEmpty() ? "No waiting passengers" : waitQueue));
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }
       
    }
}
