import java.text.DecimalFormat;
import java.util.Scanner;

public class BodyMassIndex {

    public BodyMassIndex(double height, double weight) {
        double bmiData = 703 * weight / Math.pow(height, 2);
    }

    public static void main(String[] args) {
        double[] arrayList = new double[100];
        int arrayInit;
        boolean userMoreInput = moreInput();
        int inches;
        int pounds;

        for (arrayInit = 0; userMoreInput; arrayInit++) {

            inches = getUserHeight();
            pounds = getUserWeight();

            arrayList[arrayInit] = displayBmiInfo(inches, pounds);

            userMoreInput = moreInput();
        }

        displayBmiStatistics(arrayList);
    }

    public static int getUserHeight() {
        System.out.println("Input height in inches.");
        Scanner userInput = new Scanner(System.in);
        int inches = userInput.nextInt();

        while (inches <= 0) {
            System.out.println("Please input valid a number");
            inches = userInput.nextInt();
        }
        return inches;
    }

    public static int getUserWeight() {
        System.out.println("Input weight in pounds");
        Scanner userInput = new Scanner(System.in);
        int pounds = userInput.nextInt();

        while (pounds <= 0) {
            System.out.println("Please input valid a number.");
            pounds = userInput.nextInt();
        }
        return pounds;
    }

    public static boolean moreInput() {
        boolean status;

        System.out.println("Would you like to add another user? (Y/N)");
        Scanner userInput = new Scanner(System.in);
        char userChoice = userInput.next().charAt(0);

        while (userChoice != 'Y' && userChoice != 'N') {
            System.out.println("Please input valid option.");
            userChoice = userInput.next().charAt(0);
        }

        status = userChoice == 'Y';
        return status;
    }

    public static double displayBmiInfo(int inches, int pounds) {
        double bmi = 703 * pounds / Math.pow(inches, 2);

        if (bmi < 18.5) {
            System.out.println("You are underweight");
        } else if (bmi >= 18.5 && bmi < 25) {
            System.out.println("You are normal weight");
        } else if (bmi >= 25 && bmi < 30) {
            System.out.println("You are overweight");
        } else if (bmi >= 30) {
            System.out.println("You are obese");
        }
        return bmi;
    }

    public static void displayBmiStatistics(double[] arrayList) {
        int arraySize = 0;
        int arrayIndex = 0;
        while (arrayList[arrayIndex] > 0) {
            arraySize++;
            arrayIndex++;
        }

        double arraySum = 0;

        for (arrayIndex = 0; arrayIndex < arraySize; arrayIndex++) {
            arraySum += arrayList[arrayIndex];
        }

        double average = arraySum / arraySize;
        DecimalFormat averageFormat = new DecimalFormat("#.##");

        System.out.println(averageFormat.format(average));
    }
}