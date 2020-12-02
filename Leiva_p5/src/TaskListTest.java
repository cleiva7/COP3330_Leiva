import org.junit.jupiter.api.Test;
import java.time.DateTimeException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskListTest {

    @Test
    public void addingItemsIncreasesSize(){
        TaskList tl =new TaskList();
        assertEquals(0,tl.size());
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertEquals(1,tl.size());
    }

    @Test
    public void completingTaskItemFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.completeTask(1);
        });
    }
    @Test
    public void editingItemDescriptionFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.updateTask(1, "eltiT", "", "2021-07-05");
        });
    }
    @Test
    public void editingItemDescriptionSucceedsWithExpectedValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertEquals("Desc", tl.getItem(0).getDescription());
        tl.updateTask(0, "eltiT", "Description", "2020-07-07");
        assertEquals("Description", tl.getItem(0).getDescription());
    }
    @Test
    public void editingItemDueDateSucceedsWithExpectedValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertEquals("2020-07-07", tl.getItem(0).getDueDate());
        tl.updateTask(0, "eltiT", "Description", "2019-02-17");
        assertEquals("2019-02-17", tl.getItem(0).getDueDate());
    }
    @Test
    public void editingItemTitleFailsWithEmptyTitle(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertThrows(IllegalArgumentException.class, () -> {
            tl.updateTask(0,"", "", "2020-07-07");
        });
    }
    @Test
    public void editingItemTitleFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.updateTask(1,"eltiT", "", "2020-07-24");
        });
    }
    @Test
    public void editingItemTitleSucceedsWithExpectedValue(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertEquals("Title", tl.getItem(0).getTitle());
        tl.updateTask(0, "eltiT", "Description", "2019-02-17");
        assertEquals("eltiT", tl.getItem(0).getTitle());
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidDateFormat(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertThrows(DateTimeException.class, () -> {
            tl.updateTask(0,"T", "", "20-07-07");
            tl.updateTask(0,"T", "", "20-07-07");
        });
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.updateTask(1,"Title", "", "");
        });
    }
    @Test
    public void editingTaskItemDueDateFailsWithInvalidYYYMMDD(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertThrows(DateTimeException.class, () -> {
            tl.updateTask(0,"T", "", "2020-02-30");
        });
    }
    @Test
    public void gettingItemDescriptionFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getTaskDescription(1);
        });
    }
    @Test
    public void gettingItemDescriptionSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2025-02-09"));
        assertEquals("Desc", tl.getTaskDescription(0));
    }
    @Test
    public void gettingItemDueDateFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getTaskDueDate(1);
        });
    }
    @Test
    public void gettingItemDueDateSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2025-02-09"));
        assertEquals("2025-02-09", tl.getTaskDueDate(0));
    }
    @Test
    public void gettingItemTitleFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.getTaskTitle(1);
        });
    }
    @Test
    public void gettingItemTitleSucceedsWithValidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertEquals("Title", tl.getTaskTitle(0));
    }
    @Test
    public void newListIsEmpty(){
        TaskList tl = new TaskList();
        assertEquals(true, tl.isEmpty());
    }
    @Test
    public void removingItemsDecreasesSize(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2025-02-09"));
        tl.addTask(new TaskItem("eltiT", "cseD", "2025-02-10"));
        assertEquals(2, tl.size());
        tl.removeTask(1);
        assertEquals(1, tl.size());
    }
    @Test
    public void removingItemsFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.removeTask(1);
        });
    }
    @Test
    public void savedTaskListCanBeLoaded(){
        TaskList tl = new TaskList();
        try{
            tl.loadList("LeivaTestTextFile00.txt");
            assertEquals(3,tl.size());
        }catch(Exception e){
            System.out.println(e.getMessage());
            assertEquals(0,1);
        }
    }
    @Test
    public void uncompletingTaskItemChangesStatus(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        tl.completeTask(0);
        assertEquals(true, tl.taskCompletion(0));
        tl.incompleteTask(0);
        assertEquals(false, tl.taskCompletion(0));
    }
    @Test
    public void uncompletingTaskItemFailsWithInvalidIndex(){
        TaskList tl = new TaskList();
        tl.addTask(new TaskItem("Title", "Desc", "2020-07-07"));
        tl.completeTask(0);
        assertEquals(true, tl.taskCompletion(0));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            tl.incompleteTask(1);
        });
    }
}