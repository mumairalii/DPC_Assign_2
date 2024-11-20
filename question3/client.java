import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MatrixMultiplicationClient {

    public static void main(String[] args) {
        try {
            // Connect to the RMI registry on localhost
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Lookup the MatrixMultiplication service
            MatrixMultiplication stub = (MatrixMultiplication) registry.lookup("MatrixMultiplicationService");

            // Define two matrices to multiply (for simplicity, square matrices)
            int[][] A = {
                {1, 2},
                {3, 4}
            };
            int[][] B = {
                {5, 6},
                {7, 8}
            };

            // Call the remote method to multiply the submatrices
            int[][] result = stub.multiplySubmatrices(A, B);

            // Print the result
            System.out.println("Result matrix:");
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
