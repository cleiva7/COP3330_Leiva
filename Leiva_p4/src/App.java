import java.util.Scanner;
import static java.lang.System.exit;


public class App {
    private static final Scanner input = new Scanner(System.in);
    private static final Scanner input2 = new Scanner(System.in);
    private final TaskList task;

    public App() {
        task = new TaskList();
    }

    private void mainMenu() {

        int userChoice=0;
        while(userChoice!= 3) {
            System.out.println("Main Menu:\n---------\n" +
                    "1) create a new list\n" +
                    "2) load an existing list\n" +
                    "3) quit\n");
            userChoice = input2.nextInt();

            switch (userChoice) {
                case 1 -> listOperationsMenu();
                case 2 -> {
                    int load = loadTask();
                    loadTaskList(load);
                }
                case 3 -> exit(0);
                default -> System.out.println("Please input a valid choice.\n");
            }
        }
    }
    private void listOperationsMenu(){
        int choice=0;
        while(choice!=8){
            System.out.println("List Operation Menu:");
            System.out.println("---------\n" +
                    "\n" +
                    "1) view the list\n" +
                    "2) add an item\n" +
                    "3) edit an item\n" +
                    "4) remove an item\n" +
                    "5) mark an item as completed\n" +
                    "6) unmark an item as completed\n" +
                    "7) save the current list\n" +
                    "8) quit to the main menu\n");

            choice = input2.nextInt();
            switch (choice){
                case 1:
                    viewTaskList();
                    break;
                case 2:
                    saveTaskItem();
                    break;
                case 3:
                    int edit = editTask();
                    editTaskItem(edit);
                    break;
                case 4:
                    int delete = deleteTask();
                    deleteTaskItem(delete);
                    break;
                case 5:
                    int complete = markTask();
                    completionStatus(complete);
                    break;
                case 6:
                    int incomplete = markTask();
                    incompletionStatus(incomplete);
                case 7:
                    String file = getFileName();
                    writeTaskItem(file);
                case 8:
                    mainMenu();
                    break;
                default:
                    System.out.println("Please input a valid choice.\n");

            }
        }
    }

    public void saveTaskItem() {
        TaskItem taskItem = newTaskItem();

        recordTaskItem(taskItem);
    }

    private void viewTaskList(){
        task.viewTask();
    }

    private void loadTaskList(int load){
        try{
            TaskItem data = task.get(load);
            task.load(data,load);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("No task Found.\n");

        }
    }

    private void completionStatus(int number){
        try {
            TaskItem data = task.get(number);
            task.setStatusComplete(data, number);
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("No Task found.\n");

        }
    }

    private void incompletionStatus(int status){
        try {
            TaskItem data = task.get(status);
            task.setStatusIncomplete(data, status);
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("No Task Found.\n");

        }
    }

    private void writeTaskItem(String file) {
        task.write(file);
    }

    private void recordTaskItem(TaskItem data) {
        task.add(data);
    }

    public void editTaskItem(int edit) {
        try {
            TaskItem data = task.get(edit);
            task.edit(data, edit);
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Task not found.\n");

        }
    }
    private void deleteTaskItem(int delete) {
        try{
            task.remove(delete);
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Task not found.\n");

        }
    }


    public TaskItem newTaskItem(){
        TaskItem taskItem = null;

        try {
            String description = getDescription();
            String dueDate = getDueDate();
            String status = "incomplete";

            taskItem = new TaskItem(description, dueDate, status);

        }
        catch (InvalidDescriptionException e) {
            System.out.println("Please input a valid description.\n");
        }
        catch (InvalidDateException e) {
            System.out.println("Please input a valid date\n");
        }

        return taskItem;
    }

    private String getDescription() {
        System.out.println("Enter the description:\n");
        return input.nextLine();
    }

    private String getDueDate() {
        System.out.println("Enter date (YYYY-MM-DD):\n");
        return input.nextLine();
    }

    private String getFileName(){
        System.out.println("Input file name\n");
        return input.nextLine();
    }

    private static int loadTask() {
        System.out.println("Input task number\n");
        return input2.nextInt();
    }

    private static int deleteTask() {
        System.out.println("Input task number\n");
        return input2.nextInt();
    }

    private static int editTask() {
        System.out.println("Input task number\n");
        return input2.nextInt();
    }



    private static int markTask() {
        System.out.println("Input task number\n");
        return input2.nextInt();
    }


    public static void main(String[] args) {
        App app = new App();

        app.mainMenu();

    }
}