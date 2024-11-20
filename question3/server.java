import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MatrixMultiplicationServer implements MatrixMultiplication {

    // Multiply submatrices A and B
    @Override
    public int[][] multiplySubmatrices(int[][] A, int[][] B) throws RemoteException {
        int rows = A.length;
        int cols = B[0].length;
        int sumLength = A[0].length;
        int[][] result = new int[rows][cols];
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = 0;
                for (int k = 0; k < sumLength; k++) {
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
            Registry registry = LocateRegistry.createRegistry(1099); // Default RMI registry port
            registry.rebind("MatrixMultiplicationService", stub);

            System.out.println("MatrixMultiplication Server ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
