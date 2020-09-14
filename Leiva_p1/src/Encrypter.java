import java.util.Scanner;

public class Encrypter {
    public static void main(String[] args) {

        int encryptCode;
        int digit1;
        int digit2;
        int digit3;
        int digit4;

        System.out.println("Input 4 digit code to encrypt");
        Scanner input = new Scanner(System.in);
        encryptCode = input.nextInt();

        digit1 = ((encryptCode / 1000));
        digit2 = ((encryptCode / 100) - digit1 * 10);
        digit3 = (((encryptCode / 10) - digit2 * 10) - digit1 * 100);
        digit4 = ((((encryptCode) - digit3 * 10) - digit2 * 100) - digit1 * 1000);



        System.out.print(encryptCode + " -> " + (digit3+7)%10);
        System.out.print((digit4+7)%10);
        System.out.print((digit1+7)%10);
        System.out.print((digit2+7)%10);
    }
}
