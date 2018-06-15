package implementacao;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FuncionarioImplementacao extends UnicastRemoteObject implements FuncionarioInterface{

	private static final long serialVersionUID = -2416711816154574051L;

	public FuncionarioImplementacao() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

}
