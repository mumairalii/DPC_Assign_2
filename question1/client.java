// MultiplesOfTwoClient.java (Client Implementation)
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MultiplesOfTwoClient {
    public static void main(String[] args) {
        try {
            // Get the RMI registry and look up the MultiplesOfTwoService
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MultiplesOfTwo stub = (MultiplesOfTwo) registry.lookup("MultiplesOfTwoService");

            // Call the remote method and display results
            int limit = 20; // You can change the limit to test different cases
            int[] multiples = stub.generateMultiples(limit);

            System.out.println("Multiples of 2 up to " + limit + ":");
            for (int multiple : multiples) {
                System.out.print(multiple + " ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
