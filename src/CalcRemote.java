import java.rmi.*;

public interface CalcRemote extends Remote{
    public int add(String a, String b ) throws RemoteException;
}
