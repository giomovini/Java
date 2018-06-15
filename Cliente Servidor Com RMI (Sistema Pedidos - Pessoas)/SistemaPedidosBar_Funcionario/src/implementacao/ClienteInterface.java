package implementacao;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClienteInterface  extends Remote  {
	
	public void finalizarPrograma() throws RemoteException;

}
