import java.util.*;

class Ticket {
    String title;
    String description;
    String status;
    String priority;
    int id;
    List<String> messages = new ArrayList<>();
    static List<Ticket> tickets = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);
    private static int nextId = 1;

    public void addMessage(String sender, String message) {
        messages.add(sender + ": " + message);
        if (sender.equalsIgnoreCase("Employee")) {
            this.status = "answered";
        }
    }

    public Ticket(String title, String description, String status, String priority) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.id = nextId++;
        messages.add("Customer: " + description);
    }

    //let's create a method that creates an ID for the ticket

    public int getId() {
        return this.id;
    }


    public static String getTitle() {
        String title;

        System.out.println("What would you like to call this ticket?");
        title = scanner.nextLine();
        return title;
    }

    public static String getDescription() {
        String description;

        System.out.println("What seems to be the issue?");
        description = scanner.nextLine();
        return description;
    }

    public String getStatus() {
        return this.status;
    }

    public static String getPriority() {
        String priority;

        System.out.println("How urgent is this ticket?");
        priority = scanner.nextLine();
        return priority;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id)
                .append("\nTitle: ").append(title)
                .append("\nDescription: ").append(description)
                .append("\nStatus: ").append(status)
                .append("\nPriority: ").append(priority)
                .append("\nConversation: \n");

            for (String msg : messages) {
                sb.append("  ").append(msg).append("\n");
            }
            return sb.toString();

    }

    public static void respondToTicketCustomer() {
        if (tickets.isEmpty()) {
            System.out.println("No tickets listed.");
            return;
        }
        System.out.println("Here are all open tickets: \n");
        for (Ticket t : tickets) {
            System.out.println("ID: " + t.id + "\nTitle: " + t.title + "\nDescription: " + t.description + "\nStatus: " + t.getStatus());
        }

        System.out.println("Enter the ID of the ticket you would like to respond to: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Ticket t : tickets) {
            if (t.getId() == id) {
                System.out.println("Enter your response: ");
                String response = scanner.nextLine();
                t.addMessage("Customer", response);
                System.out.println("Response added");
                return;

            }
        }
        System.out.println("Ticket not found");
    }

    public static void respondToTicketEmployee() {

        //lets display the tickets so the employee knows what to choose from
        if (tickets.isEmpty()) {
            System.out.println("No tickets listed");
            return;
        }
        System.out.println("Here are all open tickets: \n");
        for (Ticket t : tickets) {
            System.out.println("ID: " + t.getId() + "\nTitle: " + t.title + "\nDescription: " + t.description + "\nStatus: " + t.getStatus());
        }

        System.out.println("Enter the ID of the ticket that you would like to respond to: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Ticket t : tickets) {
            if (t.getId() == id) {
                System.out.println("Enter your response: ");
                String response = scanner.nextLine();
                t.addMessage("Employee", response);
                System.out.println("Response added" + t);
                return;
            }
        }
        System.out.println("Ticket not found");
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n Welcome to my ticketing system!!");
            System.out.println("1. Open a ticket (Customer Usage)");
            System.out.println("2. View all tickets");
            System.out.println("3. Respond to a ticket (as a customer)");
            System.out.println("4. Respond to a ticket (as a helpdesk associate)");
            System.out.println("5. Exit Ticketing system");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    Ticket y = new Ticket(getTitle(), getDescription(), "open", getPriority());
                    tickets.add(y);
                    System.out.println("Ticket Created: " + y + "\nID: " + y.getId());
                    break;
                case 2:
                    for (Ticket t : tickets) {
                        System.out.println(t + "\n---");
                    }
                    break;
                case 3:
                    respondToTicketCustomer();
                    break;
                case 4:
                    respondToTicketEmployee();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice");
            }

        }
    }
}
