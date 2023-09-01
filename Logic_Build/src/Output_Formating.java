import java.util.Scanner;

public class Output_Formating {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("================================");
        System.out.println("Hello");
        while (scanner.hasNext()) {
            String str = scanner.next();
            int num = scanner.nextInt();
            
            // Format and print the output
            System.out.printf("%-15s%03d%n", str, num);
        }
        System.out.println("Hello");
        System.out.println("================================");
        scanner.close();
    }
}
