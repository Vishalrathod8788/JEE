import java.util.Scanner;

public class Output_Formating {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        
        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt();

        String[][] data = new String[rows][columns];
        
        System.out.println("Enter the data for the matrix:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                data[i][j] = scanner.next();
            }
        }

        System.out.print("Enter the row and column to print (space-separated): ");
        int targetRow = scanner.nextInt();
        int targetColumn = scanner.nextInt();
        
        if (targetRow >= 0 && targetRow < rows && targetColumn >= 0 && targetColumn < columns) {
            System.out.println("Data at row " + targetRow + ", column " + targetColumn + ": " +
                    data[targetRow][targetColumn]);
        } else {
            System.out.println("Invalid row or column.");
        }

        scanner.close();
    }
}
