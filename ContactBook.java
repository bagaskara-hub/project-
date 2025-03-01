import java.util.Scanner;

public class ContactBook {
    // Acts as nodes for the Linked List
    static class Contact {
        String name;
        String phoneNum;
        String email;

        Contact next;

        // Constructor for the Contact class
        public Contact(String name, String phoneNum, String email) {
            this.name = name;
            this.phoneNum = phoneNum;
            this.email = email;
            this.next = null;
        }

        // toString method for Contact objects
        public String toString() {
            return "Name: " + this.name + "\nPhone Number: " + this.phoneNum + "\nEmail: " + this.email;
        }
    }
    // Linked List Properties
    public Contact head = null;
    public Contact tail = null;

    public void addContact(String name, String phoneNum, String email) {
        // Creates new instance of Contact
        Contact newContact = new Contact(name, phoneNum, email);

        if (head == null) {
            // If there are no contacts, set the new contact as both head and tail of the Linked List.
            head = newContact;
            tail = newContact;
        } else {
            /* If a contact already exists, set the tail's next variable
            as the new contact and set the new contact as  the new tail or
            end of the list */
            tail.next = newContact;
            tail = newContact;
        }
        System.out.println("Successfully added new Entry for " + newContact.name);
    }

    public void deleteContact(String name) {
        if (head == null) {
            // Does nothing if list is empty
            System.out.println("There are no contacts to delete");
            return;
        }
        // Create a pointer for iterating purposes
        Contact current = head;
        if(current.name.equals(name)) {
            // Delete the data if the node is on the head
            head = current.next;
        } else {
            // Create a temp variable to hold element before the pointer
            Contact temp = null;
            while(current != null && !current.name.equals(name)){
                // Iterates through the Linked List as long as a match has not been found
                temp = current;
                current = current.next;
            }
            if(current != null) {
                /* If a match is found, and it is not a null variable,
                   Set the previous contact's next as the matched contact's
                   next, which effectively prevents the current/matched
                   element to be read again, and therefore deleting it. */
                temp.next = current.next;
                if (current == tail) {
                    /* If the matched contact is the last item in the list,
                        set the element preceding the matched one as the tail of the list */
                    tail = temp;
                    tail.next = null;
                }
            }else {
                // If no matches are found, do nothing.
                System.out.println("No such contact found in book");
            }
        }
    }

    public String emailSearch(String email) {
        if (head == null){
            // Error Handling for empty list
            return "List is empty";
        }
        else if(head.email.equals(email)) {
            // Return the information of the head if it matches the searched email.
            return "Name: " + head.name + "\nPhone Number: " + head.phoneNum;
        }
        else {
            // Create a pointer for iterating purposes
            Contact current = head;
            while (current != null && !current.email.equals(email)) {
                /* While loop to iterate through the list as long as a match is
                   not found and current is not a null variable. */
                current = current.next;
            }
            if(current != null) {
                /* If a match is found, and it is not a null variable,
                   return the contact information of the matching contact */
                return "Name: " + current.name + "\nPhone Number: " + current.phoneNum;
            }else {
                // If no matches are found, do nothing.
                return "No such contact found in book";
            }
        }
    }

    public void printContacts() {
        // Create a pointer to iterate through the list
        Contact current = head;

        if (head == null) {
            // Do nothing if the list is empty
            System.out.println("Contact Book is Empty");
            return;
        }
        System.out.println("**********************************************************");
        System.out.println("Contact Book: ");
        while (current != null) {
            //Print each node by incrementing pointer
            System.out.println(current.name + " ");
            current = current.next;
        }
        // Line Break
        System.out.println();
    }

    public String searchContact(String name) {
        if (head == null){
            // Error Handling for empty list
            return "List is empty";
        }
        else if(head.name.equals(name)) {
            // Returns the contact information of the head if it matches the prompt
            System.out.println(head.name);
            return head.toString();
        }
        else {
            // Create a pointer for iterating purposes
            Contact current = head;
            while (current != null && !current.name.equals(name)) {
                /* While loop to iterate through the list as long as a match is
                   not found and current is not a null variable. */
                current = current.next;
            }
            if(current != null) {
                /* If a match is found, and it is not a null variable,
                   return the contact information of the matching contact */
                return current.toString();
            }else {
                // If no matches are found, do nothing.
                return "Contact not Found";
            }
        }
    }

    public static void main(String[] args) {
        boolean running = true;
        ContactBook contactBook = new ContactBook();
        Scanner input = new Scanner(System.in);

        while (running) {
            System.out.print(
                    """
                    (A)dd
                    (D)elete
                    (E)mail Search
                    (P)rint List
                    (S)earch
                    (Q)uit
                    Please Enter a Command:\s
                    """

            );
            String operation = input.next();

            switch (operation.toUpperCase()) {
                case "A":
                    // Name
                    System.out.println("Add an entry:\n" +
                            "Name: ");
                    String addName = input.next();


                    // Phone Number
                    System.out.println("Phone Number: ");
                    String phoneNum = input.next();

                    // Email
                    System.out.println("Email: ");
                    String email = input.next();

                    contactBook.addContact(addName, phoneNum, email);
                    System.out.println();
                    break;

                case "D":
                    System.out.println("Delete an entry: \n" +
                            "Name of contact to delete: ");
                    String delName = input.next();

                    contactBook.deleteContact(delName);
                    System.out.println();
                    break;

                case  "E":
                    System.out.println("Search for Email: \n" +
                            "Name: ");
                    String searchEmailName = input.next();
                    System.out.println(contactBook.emailSearch(searchEmailName));
                    System.out.println();
                    break;

                case "P":
                    contactBook.printContacts();
                    break;

                case "S":
                    System.out.println("Name of Contact: ");
                    String contactName = input.next();
                    System.out.println(contactBook.searchContact(contactName));
                    System.out.println();
                    break;

                case "Q":
                    System.out.println("Quitting...");
                    running = false;
                    break;

                default:
                    System.out.println("Unknown Entry");
                    System.out.println();
                    break;
            }
        }
    }
}