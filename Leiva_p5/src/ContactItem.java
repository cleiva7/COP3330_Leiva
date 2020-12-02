import java.util.InputMismatchException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ContactItem extends TypeItem{
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String email;

    public ContactItem(String firstname,String lastname, String phone, String email) throws IllegalArgumentException, InputMismatchException, InstantiationError {
        if(firstname.length()+lastname.length()+phone.length()+email.length() == 0){
            throw new InstantiationError();
        }

        if(!firstname.equals("null")){
            this.firstname = firstname;
        }else{
            this.firstname = "";
        }
        if(!lastname.equals("null")){
            this.lastname = lastname;
        }else{
            this.lastname = "";
        }

        if(isValidEmail(email)){
            this.email = email;
        }else if(email.length() != 0 && !email.equals("null")){
            this.email = "";
            System.out.println("Email portion for "+ this.getFullName() + " will be left blank. Email not in correct format.");
        }else{
            this.email = "";
        }
        if(isValidPhoneNumber(phone)){
            this.phonenumber = phone;
        }else if(phone.length() != 0 && !phone.equals("null")){
            this.phonenumber = "";
            System.out.println("Phone section for " + this.getFullName() + " will be left blank. Invalid phone format.");
        }else{
            this.phonenumber = "";
        }
    }
    public void update(String firstname,String lastname, String phone, String email) throws IllegalArgumentException{
        if(firstname.length()+lastname.length()+phone.length()+email.length() == 0){
            throw new IllegalArgumentException();
        }
        if(firstname.length() > 0){
            setFirstName(firstname);
        }
        if(lastname.length() > 0){
            setLastName(lastname);
        }
        if(isValidPhoneNumber(phone)){
            this.setPhoneNumber(phone);
        }else{
            System.out.println("invalid phone number.");
            if(this.phonenumber.equals("")){
                System.out.println("blank number.");
            }else{
                System.out.println(this.phonenumber + ".");
            }
        }
        if(isValidEmail(email)){
            this.setEmail(email);
        }else if(email.length() != 0){
            System.out.println(email + " is not in valid email format (a@b.c).\nEmail will not be updated so it will remain " + this.email + ".");
        }
    }
    private static boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile("^(.+)@(.+).(.+)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private static boolean isValidPhoneNumber(String phone){
        if(phone.length() == 12 && (phone.charAt(3) == '-' && phone.charAt(7) == '-')){
            try{
                int temp;
                for(int i = 0; i < 12; i++){
                    if(i != 3 && i != 7){
                        temp = Integer.parseInt(phone.substring(i, i+1));
                    }
                }
            }catch(Exception e){
                return false;
            }
            return true;
        }
        return false;
    }
    public String getPhoneNumber() {
        return phonenumber;
    }

    public void setPhoneNumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstname) {
        this.firstname = firstname;
    }
    public String getLastName() {
        return this.lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }
    public String getFullName(){
        if(firstname.length() == 0 && lastname.length() == 0){
            return "";
        }else if(firstname.length() == 0){
            return lastname;
        }else if(lastname.length() == 0){
            return firstname;
        }else{
            return firstname + " " + lastname;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString(){
        return this.firstname + " " +this.lastname + ": " + this.phonenumber + ": " + this.email;
    }
    public String toWriteFormat(){
        String ret = "";
        if(this.firstname.length() != 0){
            ret += this.firstname + "::";
        }else{
            ret += "null::";
        }
        if(this.lastname.length() != 0){
            ret += this.lastname + "::";
        }else{
            ret += "null::";
        }
        if(this.phonenumber.length() != 0){
            ret += this.phonenumber + "::";
        }else{
            ret += "null::";
        }
        if(this.email.length() != 0){
            ret += this.email + "::";
        }else{
            ret += "null::";
        }
        return ret;
    }
}