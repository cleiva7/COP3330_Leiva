import java.util.Scanner;

public class Decrypter {
    public static void main(String[] args) {

        int decryptCode;
        int digit1;
        int digit2;
        int digit3;
        int digit4;

        System.out.println("Input 4 digit code to decrypt");
        Scanner input = new Scanner(System.in);
        decryptCode = input.nextInt();

        digit1 = (decryptCode/1000);
        digit2 = ((decryptCode/100)-digit1*10);
        digit3 = (((decryptCode/10)-digit2*10)-digit1*100);
        digit4 = ((((decryptCode)-digit3*10)-digit2*100)-digit1*1000);

        System.out.print((digit3+3)%10);
        System.out.print((digit4+3)%10);
        System.out.print((digit1+3)%10);
        System.out.print((digit2+3)%10);


    }
}




