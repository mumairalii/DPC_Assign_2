// MultiplesOfTwoServer.java (Server Implementation)
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MultiplesOfTwoServer extends UnicastRemoteObject implements MultiplesOfTwo {

    protected MultiplesOfTwoServer() throws RemoteException {
        super();
    }

    @Override
    public int[] generateMultiples(int limit) throws RemoteException {
        int[] multiples = new int[limit / 2];
        for (int i = 1; i <= limit / 2; i++) {
            multiples[i - 1] = i * 2;
        }
        return multiples;
    }

    public static void main(String[] args) {
        try {
            // Set up and register the RPC service
            MultiplesOfTwoServer server = new MultiplesOfTwoServer();
            Registry registry = LocateRegistry.createRegistry(1099); // Default RMI registry port 1099
            registry.bind("MultiplesOfTwoService", server);
            System.out.println("Server is running and waiting for client requests...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
