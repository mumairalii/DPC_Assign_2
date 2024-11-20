import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MatrixMultiplicationServer implements MatrixMultiplication {

    // Implementation of submatrix multiplication
    @Override
    public int[][] multiplySubmatrices(int[][] A, int[][] B) throws RemoteException {
        int rowsA = A.length;
        int colsB = B[0].length;
        int colsA = A[0].length;  // Assumed to be equal to the rows of B
        int[][] result = new int[rowsA][colsB];

        // Performing matrix multiplication
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                result[i][j] = 0;
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        try {
            // Create and export the remote object
            MatrixMultiplicationServer server = new MatrixMultiplicationServer();
            MatrixMultiplication stub = (MatrixMultiplication) UnicastRemoteObject.exportObject(server, 0);

            // Bind the remote object in the registry
            Registry registry = LocateRegistry.createRegistry(1099); // Default RMI port
            registry.rebind("MatrixMultiplicationService", stub);

            System.out.println("Matrix Multiplication Server is ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
