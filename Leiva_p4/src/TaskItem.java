
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TaskItem {

    private String title;
    private final String description;
    private String dueDate;
    private String completionStatus;

    public TaskItem(String description, String dueDate, String completionStatus) {
        if (dueDateValid(dueDate)) {
            this.dueDate = dueDate;
        }
        else {
            throw new InvalidDateException("Please input a correct date (YYYY-MM-DD)\n");
        }
        if (descriptionValid(description)) {
            this.description = description;
        }
        else {
            throw new InvalidDescriptionException("Description is not valid; must be at least 1 character long\n");
        }
        this.completionStatus = completionStatus;
    }

    private boolean dueDateValid(String dueDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dueDate.trim());
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
    private boolean descriptionValid(String description) {
        return description.length() > 0;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDueDate() {
        return this.dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus(){
        return completionStatus;
    }

    public void setToComplete(){
        this.completionStatus = "complete";
    }

    public void setToIncomplete(){
        this.completionStatus = "incomplete";
    }

    public void setDescription(String description) {

    }
}

class InvalidDescriptionException extends IllegalArgumentException {
    public InvalidDescriptionException(String msg) {
        super(msg);
    }
}

class InvalidDateException extends IllegalArgumentException {
    public InvalidDateException(String msg) {
        super(msg);
    }
}

