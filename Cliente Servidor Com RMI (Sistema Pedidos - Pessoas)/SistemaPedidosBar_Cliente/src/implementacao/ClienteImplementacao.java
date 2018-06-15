package implementacao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class ClienteImplementacao extends UnicastRemoteObject implements ClienteInterface{

	protected ClienteImplementacao() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 5287359587949472992L;


}
