import java.util.Scanner;
import java.io.FileNotFoundException;


public class ContactApp {
    private Scanner scanner = new Scanner(System.in);
    public void contactAppStart(){
        main(new String[1]);
    }

    public void main(String[] args){
        this.mainMenu();
    }

    public void mainMenu(){
        printMainMenu();
        ContactList contactlist;
        int choice = getUserSelection(1,3);

        switch(choice) {
            case 1 :
                try{
                    contactlist = new ContactList();
                    System.out.println("New contact list created\n");
                    listOperationsMenu(contactlist);
                }catch(Exception e) {
                    System.out.println("Invalid selection.\n");
                }
                break;
            case 2 :
                contactlist = new ContactList();
                String filename = getFileNameFromUser();
                try{
                    contactlist.loadList(filename);
                    System.out.println("Contact List loaded.\n");
                    listOperationsMenu(contactlist);

                }catch(FileNotFoundException f){
                    System.out.println("file does not exist\n");
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                    System.out.println("Invalid selection\n");
                }
                break;
            case 3 :
                printMainMenu();
                choice = getUserSelection(1,3);
                break;
        }

    }

    private void listOperationsMenu(ContactList contactlist) {

        System.out.println("\nList Operation Menu\n" +
                "---------\n" +
                "\n" +
                "1) view the list\n" +
                "2) add an item\n" +
                "3) edit an item\n" +
                "4) remove an item\n" +
                "5) save the current list\n" +
                "6) quit to the main menu\n");

        int choice = getUserSelection(1,6);

        switch(choice) {
            case 1:
                contactlist.printList();
                break;
            case 2:
                addContact(contactlist);
                break;
            case 3:
                editContactinList(contactlist);
                break;
            case 4:
                removeContactfromList(contactlist);
                break;
            case 5:
                saveContactList(contactlist);
                break;
            case 6:
                choice = getUserSelection(1,6);
                break;
        }
    }

    private void saveContactList(ContactList contactlist) {
        System.out.println("Enter contact list name: ");
        scanner.nextLine();
        String filename = scanner.nextLine();
        try{
            if(contactlist.savetoFile(filename)){
                System.out.println("List saved\n");
            }else{
                throw new Exception();
            }
        }catch(Exception e){
            System.out.println("Could not save list.\n");
        }
    }

    private void removeContactfromList(ContactList contactlist) {
        if(contactlist.size() == 0){
            return;
        }
        int chosenindex = 0;
        scanner.nextLine();
        contactlist.printList();
        System.out.println("Enter contact index: ");
        try{
            chosenindex = scanner.nextInt();
            if(chosenindex < 0 || chosenindex >= contactlist.size()){
                throw new IndexOutOfBoundsException();
            }
            contactlist.deleteContact(chosenindex);
            System.out.println("Contact deleted.\n");
        }catch(IndexOutOfBoundsException e){
            System.out.println("Invalid index.\n");
        }catch(Exception e){
            System.out.println("Please provide a valid integer indicating the contact to remove. No contact deleted.\n");
        }
    }

    private void editContactinList(ContactList contactlist) {
        if(contactlist.size() == 0){
            return;
        }
        int chosenindex = 0;
        scanner.nextLine();
        contactlist.printList();
        System.out.print("Please enter the index of the contact you wish to update: ");
        try{
            chosenindex = scanner.nextInt();
            if(chosenindex < 0 || chosenindex >= contactlist.size()){
                throw new IndexOutOfBoundsException();
            }
            String[] newcontactinfo = getUpdatedContactInfofromUser(chosenindex);
            contactlist.updateContact(chosenindex, newcontactinfo[0],newcontactinfo[1],newcontactinfo[2],newcontactinfo[3]);

            System.out.println("Contact for " + contactlist.getContactFullName(chosenindex) + " updated.\n");
        }catch(IndexOutOfBoundsException ind){
            System.out.println(chosenindex + " is not a valid index. No contacts updated.\n");
        }catch(IllegalArgumentException i){
            System.out.println("All blank values is not valid info for the contact.\nContact " + chosenindex + " will not be updated.\n");
        }catch(Exception e){
            System.out.println("Please provide a valid integer to update it. No contact updated.\n");
        }
    }

    private String[] getUpdatedContactInfofromUser(int index) {
        String[] choice = new String[4];
        System.out.println("Please enter a new first name:");
        scanner.nextLine();
        choice[0] = scanner.nextLine();
        scanner.reset();
        System.out.println("Please enter a new last name:");
        choice[1] = scanner.nextLine();
        scanner.reset();
        System.out.println("Please enter a new phone number:");
        choice[2] = scanner.nextLine();
        scanner.reset();
        System.out.println("Please enter a new email address for contact: ");
        choice[3] = scanner.nextLine();
        scanner.reset();
        return choice;
    }

    private int getUserSelection(int low, int high) {
        int choice = -1;
        scanner = new Scanner(System.in);
        try {
            while (choice < low || choice > high) {
                System.out.print("Enter valid selection\n");
                choice = scanner.nextInt();
            }
        } catch (Exception e) {
            System.out.println("Your input needs to be a valid " +
                    "integer between " + low + " and " + high + "\n");
            choice = getUserSelection(low, high);
        }
        return choice;
    }

    private String getFileNameFromUser() {
        System.out.print("Enter the name of the file your list is saved in: ");
        scanner.nextLine();
        String choice = scanner.nextLine();
        return choice;
    }

    public static void printMainMenu() {
        System.out.println("Main Menu:\n" +
                "1) create a new list\n" +
                "2) load an existing list\n" +
                "3) quit\n");
    }
    public void addContact(ContactList contactlist){
        try{
            String[] contactinfo = getContactInfoFromUser();
            ContactItem contacttoadd = new ContactItem(contactinfo[0],contactinfo[1],contactinfo[2],contactinfo[3]);
            contactlist.addContact(contacttoadd);
            System.out.println("Contact for " + contactlist.getContactFullName(contactlist.size() - 1) + " added.\n");
        }catch(InstantiationError i){
            System.out.println("All values cannot be blank at least one contact field must have content.\n");
        }catch(Exception e){
            System.out.println("Returning to List Operations Menu.\n");
        }
    }
    public String[] getContactInfoFromUser(){
        String[] choice = new String[4];
        System.out.print("Please enter the contact's first name: ");
        scanner.nextLine();
        choice[0] = scanner.nextLine();
        scanner.reset();
        System.out.print("Please enter the contact's last name: ");
        choice[1] = scanner.nextLine();
        scanner.reset();
        System.out.print("Please enter the contact's phone number(xxx-xxx-xxx): ");
        choice[2] = scanner.nextLine();
        scanner.reset();
        System.out.print("Please enter the contact's email address (x@y.z): ");
        choice[3] = scanner.nextLine();
        scanner.reset();
        return choice;
    }
}