import java.io.FileNotFoundException;
import java.util.*;


public class TaskList {

    private static final Scanner input = new Scanner(System.in);

    ArrayList<TaskItem> comp;
    ArrayList<TaskItem> task;

    public TaskList() {
        task = new ArrayList<>();
        comp = new ArrayList<>();
    }

    public void add(TaskItem data) {
        task.add(data);
    }

    public TaskItem get(int number){
        return task.get(number);
    }



    public void edit(TaskItem data, int edit){
        if (indexValid(edit)) {
            System.out.println("Input task number.\n");
            String whatToEdit = input.nextLine();
            if (whatToEdit.toLowerCase().equalsIgnoreCase("title")) {
                String tempTitle = getTitle();
                editTitle(data, tempTitle);
            }
            if (whatToEdit.toLowerCase().equalsIgnoreCase("description")) {
                String tempDescription = getDescription();
                editDescription(data, tempDescription);
            }
            if (whatToEdit.toLowerCase().contains("date")) {
                String tempDate = getDueDate();
                editDate(data, tempDate);
            }

            task.set(edit, data);
        }
        else{
            throw new IndexOutOfBoundsException("Pleas re-enter info.");
        }
    }

    public void editTitle(TaskItem data, String change){
        data.setTitle(change); }

    public void editDescription(TaskItem data, String change){
        data.setDescription(change); }

    public void editDate(TaskItem data, String change){
        data.setDueDate(change);
    }

    public void remove(int taskID) {
        if(indexValid(taskID)) {
            task.remove(taskID);
        }
        else{
            throw new IndexOutOfBoundsException("Invalid input.\n");
        }
    }

    public void load(TaskItem data, int number){
        if (indexValid(number)){
            System.out.println( number + ") ["+loadDate(data)+"] "+ "Title " +
                    displayTitle(data)+ ". Description: "+ loadDescription(data));
        }
        else{
            throw new IndexOutOfBoundsException("Invald input.\n");
        }

        loadDate(data);
        displayTitle(data);
        loadDescription(data);
    }

    public void viewTask(){
        int i = 0;
        if (task.size()==0){
            throw new IndexOutOfBoundsException("No info found.\n");
        }
        for(TaskItem task : task){
            System.out.println( i + ") ["+task.getDueDate()+"] "+ "Title: " +
                    task.getTitle()+ ". Description: "+ task.getDescription()+ " Status: "
                    + task.getStatus());
            i++;
        }
    }


    public String displayTitle(TaskItem data){
        return data.getTitle();
    }

    public String loadDescription(TaskItem data){
        return data.getDescription();
    }
    public String loadDate(TaskItem data){
        return data.getDueDate();
    }

    public void setStatusComplete(TaskItem data, int index){
        if(indexValid(index)){
            data.setToComplete();
        }
        else{
            throw new IndexOutOfBoundsException("Invalid input.\n");
        }
    }

    public void setStatusIncomplete(TaskItem data, int index){
        if(indexValid(index)){
            data.setToIncomplete();
        }
        else{
            throw new IndexOutOfBoundsException("Invalid input\n");
        }
    }


    public void write(String filename) {
        try(Formatter output = new Formatter(filename)) {
            for (TaskItem data : task) {
                output.format("%s;%s;%s%n", data.getTitle(), data.getDescription(),
                        data.getDueDate());
            }

        } catch (FileNotFoundException ex) {
            System.out.println("No file found.\n");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getTitle() {
        System.out.println("Enter task title:\n");
        return input.nextLine();
    }

    private String getDescription() {
        System.out.println("Enter task description:\n");
        return input.nextLine();
    }

    private String getDueDate() {
        System.out.println("Enter task date (YYYY-MM-DD):\n");
        return input.nextLine();
    }

    private boolean indexValid(int index){
        return (index <= task.size());
    }

}

