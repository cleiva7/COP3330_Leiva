import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ContactList extends TypeList{
    public ContactList(){
        this.list = new ArrayList<TypeItem>();
    }

    public void printList(){
        System.out.println("Current Contacts\n" +
                "---------------\n");
        int len = size();
        for(int i = 0; i < len; i++){
            System.out.println(i + ") Name: " + getContact(i).getFullName() + "\n" +
                    "Phone: " + getContact(i).getPhoneNumber() + "\n" +
                    "Email: " + getContact(i).getEmail());
        }
        System.out.print("\n");
    }

    public ContactItem getContact(int index){
        return (ContactItem)this.list.get(index);
    }
    public String getContactFirstName(int index){
        return this.getContact(index).getFirstName();
    }
    public String getContactLastName(int index){
        return this.getContact(index).getLastName();
    }
    public String getContactFullName(int index){
        return getContact(index).getFullName();
    }
    public String getContactPhoneNumber(int index){
        return this.getContact(index).getPhoneNumber();
    }
    public String getContactEmail(int index){
        return this.getContact(index).getEmail();
    }
    public void addContact(ContactItem c){
        list.add(c);
    }
    public void updateContact(int index, String first, String last, String phone, String email) {
        try{
            this.getContact(index).update(first,last, phone, email);
        }catch(IndexOutOfBoundsException i){
            System.out.println("Invalid index.\n");
            throw i;
        }catch(IllegalArgumentException e){
            System.out.println("Invalid parameters, contact not saved\n");
            throw e;
        }
    }
    public void deleteContact(int index){
        this.list.remove(index);
    }
    public String contactToString(int index){
        return getContact(index).toString();
    }
    public void loadList(String filename) throws FileNotFoundException, Exception {
        if(filename.length() >= 4 && !filename.substring(filename.length() - 4).equals(".txt")){
            filename += ".txt";
        }
        try {
            File file = new File("src/" + filename);
            int failedload = 0;
            if(!file.exists()){
                throw new FileNotFoundException("File does not exist\n");
            }
            Scanner s = new Scanner(file.getAbsoluteFile());
            while (s.hasNextLine()) {
                String[] row = s.nextLine().split("::");
                try{
                    ContactItem c = new ContactItem(row[0],row[1],row[2],row[3]);
                    addContact(c);
                }catch(InstantiationError e){
                    failedload++;
                }
            }
            if(failedload != 0){
                System.out.println("Could not load contacts.\n");
            }
        } catch (FileNotFoundException e) {
            throw e;
        }
    }

    protected void writeText(PrintWriter printWrite) throws Exception{
        try{
            int length = this.size();
            for(int i = 0; i < length; i++){
                printWrite.println(this.getContact(i).toWriteFormat());
            }
            printWrite.close();
        }catch(Exception e){
            throw new Exception("append failed");
        }
    }
}