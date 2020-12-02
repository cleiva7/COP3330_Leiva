import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskItemTest {

    @Test
    public void constructorFailsWithInvalidDueDate(){
        assertThrows(DateTimeException.class, () -> {
            TaskItem t = new TaskItem("Title", "Desc", "2020-07-07");
        });
    }
    @Test
    public void constructorFailsWithInvalidTitle(){
        assertThrows(IllegalArgumentException.class, () -> {
            TaskItem t = new TaskItem("", "Desc", "2020-07-07");
        });
    }
    @Test
    public void constructorSucceedsWithValidDueDate(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-07-07");
        assertEquals("2020-07-07", t.getDueDate());
    }
    @Test
    public void constructorSucceedsWithValidTitle(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-07-07");
        assertEquals("Title", t.getTitle());
    }
    @Test
    public void editingDescriptionSucceedsWithExpectedValue(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-07-07");
        assertEquals("Desc", t.getDescription());
        t.updateTask("eltiT","Description","2020-07-07");
        assertEquals("Description", t.getDescription());
    }
    @Test
    public void editingDueDateFailsWithInvalidDateFormat(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-07-07");
        assertThrows(DateTimeException.class, () -> {
            t.updateTask("Title","", "20-12-12");
        });
    }
    @Test
    public void editingDueDateFailsWithInvalidYYYYMMDD(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-07-07");
        assertThrows(DateTimeException.class, () -> {
            t.updateTask("Title","", "2020-12-12");
        });
    }
    @Test
    public void editingDueDateSucceedsWithExpectedValue(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-07-07");
        assertEquals("2020-07-07", t.getDueDate());
        t.updateTask("eltiT","","2020-12-12");
        assertEquals("2020-12-12", t.getDueDate());
    }
    @Test
    public void editingTitleFailsWithEmptyString(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-07-07");
        assertThrows(IllegalArgumentException.class, () -> {
            t.updateTask("","", "2020-12-12");
        });
    }
    @Test
    public void editingTitleSucceedsWithExpectedValue(){
        TaskItem t = new TaskItem("Title", "Desc", "2020-07-07");
        assertEquals("Title", t.getTitle());
        t.updateTask("eltiT","","2020-12-12");
        assertEquals("eltiT", t.getTitle());
    }
}