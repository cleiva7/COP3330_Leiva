import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactItemTest {
    @Test
    public void creationFailsWithAllBlankValues(){
        assertThrows(InstantiationError.class, () -> {
            ContactItem contact = new ContactItem("","","","");
        });
    }
    @Test
    public void creationSucceedsbutwithBlankPhoneNumberandEmail(){
        ContactItem contact = new ContactItem("Christopher", "Leiva", "", "");
        assertEquals("Christopher Leiva: : ", contact.toString());
    }

    @Test
    public void creationSucceedswithBlankPhoneandEmail(){
        ContactItem contact  = new ContactItem("Christopher", "Last", "", "");
        assertEquals("Christopher::Last::null::null::", contact.toWriteFormat());
    }
    @Test
    public void creationSucceedswithBlankLastandPhoneandEmail(){
        ContactItem contact  = new ContactItem("Christopher", "", "", "");
        assertEquals("Christopher::null::null::null::", contact.toWriteFormat());
    }
    @Test
    public void creationSucceedsWithBlankEmail(){
        assertDoesNotThrow(()->{
            ContactItem contact = new ContactItem("Christopher","Leiva","123-456-7890","");
        });
    }
    @Test
    public void creationSucceedsWithBlankFirstName(){
        assertDoesNotThrow(()->{
            ContactItem contact = new ContactItem("","Leiva","123-456-7890","fakeemail@email.com");
        });
    }
    @Test
    public void creationSucceedsWithBlankLastName(){
        assertDoesNotThrow(()->{
            ContactItem contact = new ContactItem("Christopher","","123-456-7890","fakeemail@email.com");
        });
    }
    @Test
    public void creationSucceedsWithBlankPhone(){
        assertDoesNotThrow(()->{
            ContactItem contact = new ContactItem("Christopher","Leiva","","fakeemail@email.com");
        });
    }
    @Test
    public void creationSucceedsWithNonBlankValues(){
        assertDoesNotThrow(()->{
            ContactItem contact = new ContactItem("Christopher","Leiva","123-456-7890","fakeemail@email.com");
        });
    }
    @Test
    public void editingFailsWithAllBlankValues(){
        ContactItem contact = new ContactItem(" "," "," "," ");
        assertThrows(IllegalArgumentException.class, () -> {
            contact.update("","","","");
        });
    }
    @Test
    public void editingSucceedsWithBlankEmail(){
        ContactItem contact = new ContactItem("Christopher","Leiva","123-456-7890","");
        assertDoesNotThrow(()->{
            contact.update("Christopher","Last","987-654-3210","");
        });
    }
    @Test
    public void editingSucceedsWithBlankFirstName(){
        ContactItem contact = new ContactItem("","Leiva","123-456-7890","fakeemail@email.com");
        assertDoesNotThrow(()->{
            contact.update("","Last","987-654-3210","fakeemail@email.com");
        });
    }
    @Test
    public void editingSucceedsWithBlankLastName(){
        ContactItem contact = new ContactItem("Christopher","","123-456-7890","fakeemail@email.com");
        assertDoesNotThrow(()->{
            contact.update("Christopher","","987-654-3210","fakeemail@email.com");
        });
    }
    @Test
    public void editingSucceedsWithBlankPhone(){
        ContactItem contact = new ContactItem("Christopher","Leiva","","fakeemail@email.com");
        assertDoesNotThrow(()->{
            contact.update("Christopher","Leiva","","fakeemail@email.com");
        });
    }
    @Test
    public void editingSucceedsWithNonBlankValues(){
        ContactItem contact = new ContactItem("Christopher","Leiva","123-456-7890","fakeemail@email.com");
        assertDoesNotThrow(()->{
            contact.update("Christopher","Leiva","987-654-3210","fakeemail@email.com");
        });
    }
    @Test
    public void testToString(){
        ContactItem contact = new ContactItem("Christopher","Leiva","123-456-7890","fakeemail@email.com");
        assertEquals("Christopher Leiva: 123-456-7890: fakeemail@email.com", contact.toString());
    }

}