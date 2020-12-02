import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactListTest {

    @Test
    public void abstractionSucceeds(){
        TypeList contactList = new ContactList();
        assertEquals(true, contactList.isEmpty());
    }
    @Test
    public void addingItemsIncreasesSize(){
        ContactList contact = new ContactList();
        assertEquals(0, contact.size());
        contact.addContact(new ContactItem("Christopher","Leiva","123-456-7890","fakeemail@email.com"));
        assertEquals(1, contact.size());
    }
    @Test
    public void editingItemsFailsWithAllBlankValues(){
        ContactList contact = new ContactList();
        contact.addContact(new ContactItem("Christopher","Leiva","123-456-7890","fakeemail@email.com"));
        assertThrows(IllegalArgumentException.class,() -> {
            contact.updateContact(0,"","", "","");
        } );
    }
    @Test
    public void editingItemsFailsWithInvalidIndex(){
        ContactList contact = new ContactList();
        contact.addContact(new ContactItem("Christopher","Leiva","123-456-7890", "fakeemail@email.com"));
        assertThrows(IndexOutOfBoundsException.class,() -> {
            contact.updateContact(1,"Chris", "Layva", "123-456-7", "fakeemail@email.com");
        } );
    }
    @Test
    public void editingSucceedsWithBlankFirstName(){
        ContactList contact = new ContactList();
        contact.addContact(new ContactItem("Christopher","Leiva","123-456-7890", "fakeemail@email.com"));
        contact.updateContact(0,"","Layva", "123-456-7890", "fakeemail@email.com");
        assertEquals("Christopher", contact.getContactFirstName(0));
    }
    @Test
    public void editingSucceedsWithBlankLastName(){
        ContactList contact = new ContactList();
        contact.addContact(new ContactItem("Christopher","Leiva","123-456-7890", "fakeemail@email.com"));
        contact.updateContact(0,"Kristofer","", "000-000-0000", "fakeemail@email.com");
        assertEquals("Leiva", contact.getContactLastName(0));
    }
    @Test
    public void editingSucceedsWithBlankPhone(){
        ContactList contact = new ContactList();
        contact.addContact(new ContactItem("Christopher","Leiva","123-456-7890", "fakeemail@email.com"));
        contact.updateContact(0,"Kristofer","Layva", "", "fakeemail@email.com");
        assertEquals("Layva", contact.getContactLastName(0));
    }
    @Test
    public void editingSucceedsWithBlankPhoneandEmail(){
        ContactList contact = new ContactList();
        contact.addContact(new ContactItem("Christopher","Leiva","123-456-7890", "fakeemail@email.com"));
        contact.updateContact(0,"Kristofer", "Layva", "", "");
        assertEquals("Kristofer::Layva::123-456-7890::fakeemail@email.com::", contact.getContact(0).toWriteFormat());
    }
    @Test
    public void editingSucceedsWithBlankFirstandLastNames(){
        ContactList contact = new ContactList();
        contact.addContact(new ContactItem("Christopher","Leiva","123-456-7890", "fakeemail@email.com"));
        contact.updateContact(0,"", "", "000-000-0000", "new@email.net");
        assertEquals("Christopher::Leiva::000-000-0000::new@email.net::",contact.getContact(0).toWriteFormat());
    }
    @Test
    public void editingSucceedsWithNonBlankValues(){
        ContactList contact = new ContactList();
        contact.addContact(new ContactItem("Christopher","Leiva","123-456-7890", "fakeemail@email.com"));
        contact.updateContact(0,"Kristofer","Layva", "000-000-0000", "fakeemail@email.com");
        assertEquals("Kristofer Layva: 000-000-0000: fakeemail@email.com", contact.contactToString(0));
    }
    @Test
    public void newListIsEmpty(){
        ContactList contact = new ContactList();
        assertEquals(true, contact.isEmpty());
    }
    @Test
    public void removingItemsDecreasesSize(){
        ContactList contact = new ContactList();
        assertEquals(0, contact.size());
        contact.addContact(new ContactItem("Christopher","Leiva","123-456-7890","fakeemail@email.com"));
        assertEquals(1, contact.size());
        contact.deleteContact(0);
        assertEquals(0, contact.size());
    }
    @Test
    public void removingItemsFailsWithInvalidIndex(){
        ContactList contact = new ContactList();
        contact.addContact(new ContactItem("Christopher","Leiva","123-456-7890","fakeemail@email.com"));
        assertThrows(IndexOutOfBoundsException.class, () -> {
            contact.deleteContact(1);
        });
    }
    @Test
    public void savedContactListCanBeLoaded(){
        ContactList contact = new ContactList();
        assertDoesNotThrow(()->{
            contact.loadList("ChristopherSampleContactFile00.txt");
            assertEquals(2,contact.size());
        });
    }
}