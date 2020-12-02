import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;

public class TaskItem extends TypeItem{
    private String title;
    private String description;
    private String dueDate;
    private boolean Complete;

    public TaskItem(String title, String desc, String dueDate) {
        if(title.length() == 0){
            throw new IllegalArgumentException();
        }else if(!isValidDate(dueDate)){
            throw new DateTimeException("");
        }
        this.title = title;
        this.description = desc;
        this.dueDate = dueDate;
        this.Complete = false;
    }

    public void updateTask(String title, String desc, String dueDate) {
        if(title.length() == 0){
            throw new IllegalArgumentException();
        }else if(!isValidDate(dueDate)){
            throw new DateTimeException("");
        }
        this.setDueDate(dueDate);
        this.setDescription(desc);
        this.setTitle(title);
    }

    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDueDate(String duedate){
        this.dueDate = duedate;
    }
    public String getDueDate(){
        return this.dueDate;
    }

    public String ignoreCompletion(){
        return ("[" + this.dueDate + "] " +
                this.title + ": " + this.description);
    }
    public void complete(){
        this.Complete = true;
    }
    public void incomplete(){
        this.Complete = false;
    }
    public boolean isComplete(){
        return this.Complete;
    }
    public String toWriteFormat(){
        String ret = this.title + "::" + this.description + "::" + this.dueDate;
        if(this.Complete){
            ret += "::c";
        }
        return ret;
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