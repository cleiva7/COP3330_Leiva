import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }

    private static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData) {
        int arraySize = bmiData.size();
        int arrayIndex;
        int arraySum = 0;

        for(arrayIndex = 0 ; arrayIndex < arraySize ; arrayIndex++) {
            bmiData.get(arrayIndex);
        }
        System.out.println("Average BMI = "+ arraySum/arraySize);
    }

    public static double getUserHeight() {
        System.out.println("Input height in inches.");
        Scanner userInput = new Scanner(System.in);
        double inches = userInput.nextInt();

        while (inches <= 0) {
            System.out.println("Please input valid a number");
            inches = userInput.nextInt();
        }
        return inches;
    }

    public static double getUserWeight() {
        System.out.println("Input weight in pounds");
        Scanner userInput = new Scanner(System.in);
        double pounds = userInput.nextInt();

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

        while(userChoice != 'Y' && userChoice != 'N') {
            System.out.println("Please input valid option.");
            userChoice = userInput.next().charAt(0);
        }

        status = userChoice == 'Y';
        return status;
    }

    public static void displayBmiInfo(BodyMassIndex bmi) {
        System.out.println(bmi);
    }
}
