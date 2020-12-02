import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class TaskApp {
    private Scanner scan = new Scanner(System.in);
    public void taskAppStart(){
        main(new String[1]);
    }
    public void main(String[] args) {
        this.mainMenu();
    }
    private void mainMenu() {
        printMainMenu();
        TaskList tasklist;
        int input = this.getInput(1, 3);
            switch(input) {
                case 1 :
                    tasklist = new TaskList();
                    try {
                        System.out.println("New task list created.\n");
                        listOperationMenu(tasklist);
                    } catch (Exception e) {
                        System.out.println("Invalid selection.\n");
                    }
                    break;
                case 2 :
                    String filename;
                    try {
                        tasklist = new TaskList();
                        filename = getFileName();
                        tasklist.loadList(filename);
                        System.out.println("Task list has been loaded.");
                        listOperationMenu(tasklist);
                    } catch (FileNotFoundException e) {
                        System.out.println("Task list does not exist. Failed to load list.\n");
                    } catch (Exception e) {
                        System.out.println("Task list not found\n");
                    }
                    break;
                case 3 :
                    break;
            }

            printMainMenu();
            input = getInput(1, 3);
        }


    private void listOperationMenu(TaskList tasklist) throws Exception {
        printListOperationMenu();
        int input = getInput(1, 8);

        switch(input) {
            case 1:
                tasklist.printList();
                break;
            case 2:
                checkAddTask(tasklist);
                break;
            case 3:
                checkNewTask(tasklist);
                break;
            case 4:
                checkRemoveTask(tasklist);
                break;
            case 5:
                checkAddCompletion(tasklist);
                break;
            case 6:
                checkRemoveCompletion(tasklist);
                break;
            case 7:
                if (tasklist.savetoFile(getUserListFile())) {
                    System.out.println("Task list saved.\n");
                } else {
                    System.out.println("Could not save task list.\n");
                }
                break;
            case 8:
                printListOperationMenu();
                input = getInput(1, 8);
                break;
        }
    }

    public static void printListOperationMenu() {
        System.out.println("List Operation Menu\n" +
                "---------------");
        System.out.println("1) view the list\n" +
                "2) add an item\n" +
                "3) edit an item\n" +
                "4) remove an item\n" +
                "5) mark an item as completed\n" +
                "6) unmark an item as completed\n" +
                "7) save the current list\n" +
                "8) quit to the main menu\n");
    }

    private int getInput(int low, int high) {
        int userInput = 0;
        try {
            while (userInput < low || userInput > high) {
                System.out.print("Please input a valid option");
                userInput = scan.nextInt();
                scan.nextLine();
            }
        } catch (Exception e) {
            scan.nextLine();
            System.out.println("Please input a valid option");
            userInput = getInput(low, high);
        }
        return userInput;

    }
    private String getUserListFile(){
        System.out.print("Enter filename: ");
        String userInput = scan.nextLine();
        scan.reset();
        return userInput;
    }
    private String getFileName() {
        System.out.print("Enter filename: ");
        String userInput = scan.nextLine();
        scan.reset();
        return userInput;
    }

    public static void printMainMenu() {
        System.out.println("Main Menu:\n" +
                "_________");
        System.out.println("1) create a new list");
        System.out.println("2) load an existing list");
        System.out.println("3) quit");
    }

    private boolean addTask(TaskList tasklist) throws IllegalArgumentException, DateTimeException {
        String[] taskInfo = getTaskData();

        if (taskInfo[0].length() == 0) {
            throw new IllegalArgumentException();
        }
        if (!checkDate(taskInfo[2])) {
            throw new DateTimeException("Invalid format");
        }
        TaskItem t = new TaskItem(taskInfo[0], taskInfo[1], taskInfo[2]);
        tasklist.addTask(t);
        return true;
    }

    private String[] getTaskData() {
        String[] userInput = new String[3];
        System.out.print("Task Title: ");
        userInput[0] = scan.nextLine();
        scan.reset();
        System.out.print("Task Description: ");
        userInput[1] = scan.nextLine();
        scan.reset();
        System.out.print("Task Due Date (YYYY-MM-DD): ");
        userInput[2] = scan.nextLine();
        scan.reset();
        return userInput;
    }

    private void removeCompletion(TaskList tasklist) throws IndexOutOfBoundsException {
        ArrayList<Integer> al = tasklist.printCompletedTasks();
        if (al.size() != 0) {
            System.out.print("Enter the index of the task you wish to mark as incomplete: ");
            int index = scan.nextInt();
            scan.reset();
            if (index < 0 || index > al.size()) {
                throw new IndexOutOfBoundsException();
            }
            int indexinlist = al.get(index);
            tasklist.getItem(indexinlist).incomplete();
            System.out.println("\nTask marked as incomplete.\n");
        }
    }

    private void addCompletion(TaskList tasklist) throws IndexOutOfBoundsException {
        ArrayList<Integer> al = tasklist.incompleteTaskList();
        if (al.size() != 0) {
            System.out.print("Enter the index of the task you wish to mark as complete: ");
            int index = scan.nextInt();
            scan.reset();
            if (index < 0 || index > al.size()) {
                throw new IndexOutOfBoundsException();
            }
            int indexinlist = al.get(index);
            tasklist.getItem(indexinlist).complete();
            System.out.println("\nTask marked as complete.\n");
        }
    }

    private void removeTask(TaskList tasklist) throws IndexOutOfBoundsException {
        tasklist.printList();
        if (tasklist.size() != 0) {
            try{
                System.out.print("Please enter the index of the task you wish to delete: ");
                int index = scan.nextInt();
                scan.reset();
                if (index < 0 || index >= tasklist.size()) {
                    throw new IndexOutOfBoundsException();
                }
                tasklist.removeTask(index);
                System.out.println("Task removed");
            }catch(IndexOutOfBoundsException i){
                scan.nextLine();
                System.out.println("Index out of bounds. Please provide an integer within range of list shown.");
            }catch(Exception e){
                scan.nextLine();
                System.out.println("Only valid integers accepted.");
            }

        }
    }

    private void updateTask(TaskList tasklist) throws IndexOutOfBoundsException, IllegalArgumentException, DateTimeException {
        tasklist.printList();
        if (tasklist.size() != 0) {
            System.out.print("Please enter the index of the task you wish to edit: ");
            int index = scan.nextInt();
            scan.reset();
            if (index < 0 || index >= tasklist.size()) {
                throw new IndexOutOfBoundsException();
            }
            String[] newtaskinfo = newTaskInfo();
            if (checkTaskInfo(newtaskinfo[0], newtaskinfo[2])) {
                tasklist.updateTask(index, newtaskinfo[0], newtaskinfo[1], newtaskinfo[2]);
                System.out.println("Task updated");
            }
        }
    }

    public static boolean checkTaskInfo(String title, String dueDate) throws IllegalArgumentException, DateTimeException {
        if (title.length() == 0) {
            throw new IllegalArgumentException();
        }
        if (!checkDate(dueDate)) {
            throw new DateTimeException("");
        }
        return true;
    }

    private static boolean checkDate(String Date) {
        if(Date.length() != 10){
            return false;
        }
        if(Date.charAt(4) != '-' ||Date.charAt(7) != '-'){
            return false;
        }
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(Date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    private String[] newTaskInfo(){
        String[] userInput = new String[3];
        scan.nextLine();
        System.out.println("Enter the new task title: ");
        userInput[0] = scan.nextLine();
        scan.reset();
        System.out.println("Enter the new task description:");
        userInput[1] = scan.nextLine();
        scan.reset();
        System.out.println("Enter a new task due date (YYYY-MM-DD):");
        userInput[2] = scan.nextLine();
        scan.reset();

        return userInput;
    }
    private void checkAddTask(TaskList tasklist){
        try {
            if (addTask(tasklist)) {
                System.out.println("Task created and added to the list.");
            }
        } catch (IllegalArgumentException i) {
            System.out.println("Title needs to be at least one character long.\nTask not created.");
        } catch (DateTimeException d) {
            System.out.println("Due Date needs to be a real date in YYYY-MM-DD format.\nTask not created.");
        }
    }
    private void checkNewTask(TaskList tasklist){
        try {
            updateTask(tasklist);
        } catch (IndexOutOfBoundsException iob) {
            scan.nextLine();
            System.out.println("Not a valid index for a task on the list.");
        } catch (IllegalArgumentException ill) {
            scan.reset();
            System.out.println("Title needs to be at least one character in length.\n" +
                    "Task not updated.");
        } catch (DateTimeException d) {
            scan.reset();
            System.out.println("Due date needs to be a real date in a valid YYYY-MM-DD format.\n" +
                    "Task not updated.");
        } catch (Exception e) {
            scan.nextLine();
            System.out.println("Need a valid integer as index of your list to edit a task.");
        }
    }
    private void checkRemoveTask(TaskList tasklist){
        try {
            removeTask(tasklist);
        } catch (IndexOutOfBoundsException iob) {
            scan.nextLine();
            System.out.println("Not a valid index for a task on the list.");
        }catch(Exception e){
            scan.nextLine();
            System.out.println("Only valid integers accepted.\n");
        }
    }
    private void checkAddCompletion(TaskList tasklist){
        try {
            addCompletion(tasklist);
        } catch (IndexOutOfBoundsException i) {
            scan.nextLine();
            System.out.println("Not a valid index for a task on the list.");
        } catch (Exception e) {
            scan.nextLine();
            System.out.println("Unable to find task with provided index. Please provide an integer corresponding to the correct index");
        }
    }

    private void checkRemoveCompletion(TaskList tasklist){
        try {
            removeCompletion(tasklist);
        } catch (IndexOutOfBoundsException i) {
            scan.nextLine();
            System.out.println("Not a valid index for a task on the list.");
        } catch (Exception e) {
            scan.nextLine();
            System.out.println("Unable to find task with provided index. Please provide an integer corresponding to the correct index");
        }
    }
}