import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.*;

public class TaskList extends TypeList{

    public String getTaskTitle(int index){
        return getItem(index).getTitle();
    }
    public String getTaskDescription(int index){
        return getItem(index).getDescription();
    }
    public String getTaskDueDate(int index){
        return getItem(index).getDueDate();
    }
    public boolean taskCompletion(int index){
        return getItem(index).isComplete();
    }
    public TaskList() {
        this.list = new ArrayList<>();
    }
    public void addTask(TaskItem t) {
        this.list.add(t);
    }

    public void completeTask(int index){
        getItem(index).complete();
    }
    public void incompleteTask(int index){
        getItem(index).incomplete();
    }

    public void printList() {
        System.out.println("Current tasks\n" +
                "_____________");
        System.out.println(this.toString());
    }

    public ArrayList<Integer> printCompletedTasks() {
        int count = 0;
        ArrayList<Integer> al = new ArrayList<>();
        int length = this.size();
        System.out.println("Completed Tasks\n" +
                "_____________");
        for (int i = 0; i < length; i++) {
            TaskItem t = this.getItem(i);
            if (t.isComplete()) {
                al.add(i);
                System.out.println(count + ") " + t.ignoreCompletion());
                count++;
            }
        }
        System.out.println("");
        return al;
    }
    public ArrayList<Integer> incompleteTaskList() {
        int count = 0;
        ArrayList<Integer> al = new ArrayList<>();
        int length = this.size();
        System.out.println("Incomplete Tasks\n" +
                "_____________");
        for (int i = 0; i < length; i++) {
            TaskItem t = this.getItem(i);
            if (!t.isComplete()) {
                al.add(i);
                System.out.println(count + ") " + t.ignoreCompletion());
                count++;
            }
        }
        System.out.println("");
        return al;
    }
    public int indexof(TaskItem t){
        return this.list.indexOf(t);
    }
    public TaskItem getItem(int index) {
        return (TaskItem) this.list.get(index);
    }
    public void updateTask(int index, String title, String desc, String duedate){
        getItem(index).updateTask(title,desc,duedate);
    }

    public void removeTask(int index) {
        this.list.remove(index);
    }

    public void loadList(String filename) throws FileNotFoundException, Exception {
        if(filename.length() < 4 || !filename.substring(filename.length() - 4).equals(".txt")){
            filename +=".txt";
        }
        try {
            File file = new File("src/" + filename);
            if(!file.exists()){
                throw new FileNotFoundException("File does not exist");
            }
            Scanner s = new Scanner(file.getAbsoluteFile());
            while (s.hasNextLine()) {
                String[] row = s.nextLine().split("::");
                //title, description, date
                if(row[0].length() != 0 && isValidDate(row[2])){
                    this.list.add(new TaskItem(row[0], row[1], row[2]));
                    if(row.length == 4){
                        this.getItem(this.list.size()-1).complete();
                    }
                }

            }
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    protected void writeText(PrintWriter pw) throws Exception{
        try{
            int length = this.size();
            for(int i = 0; i < length; i++){
                pw.println(this.getItem(i).toWriteFormat());
            }
            pw.close();
        }catch(Exception e){
            throw new Exception("append failed");
        }
    }

    private static boolean isValidDate(String Date) {
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

}