import java.util.Scanner;

public class App {

    private static Scanner userInput = new Scanner(System.in);

    public static void main(String[] args){
        MainMenu();
    }

    private static void MainMenu(){

        System.out.println("Select Your Application\n" +
                "_______________________" +
                "1) task list\n" +
                "2) contact list\n" +
                "3) quit\n");

        TaskApp userTask = new TaskApp();
        ContactApp userContact = new ContactApp();


        int choice = appChoice(1,3);

        switch(choice) {
            case 1 :
                userTask.taskAppStart();
                break;
            case 2 :
                userContact.contactAppStart();
                break;
            case 3 :
                break;
        }

    }


    private static int appChoice(int low, int high){
        int userChoice=0;
        userInput = new Scanner(System.in);
        try {
            while (userChoice < low || userChoice > high) {
                System.out.print("Please enter a valid option.\n");
                userChoice = userInput.nextInt();
            }
        } catch (Exception e) {
            userChoice = appChoice(low, high);
        }
        return userChoice;
    }
}