package implementacao;

import javax.swing.JOptionPane;

import TipoDado.Pedido;

public class Tverificacao implements Runnable {
	


	boolean b = true;

	@Override
	public void run() {
		
		try {
			while (b) {
				Thread.sleep(1000);

				if (ClientePrincipal.ID_PEDIDO != 0) {
					Pedido p = Remota.getInstance().buscaPedido(String.valueOf(ClientePrincipal.ID_PEDIDO));
					if (p.getFinalizado() == 1) {
						JOptionPane.showMessageDialog(null, "Conta Finalizada");
						Remota.getInstance().desconectar();
						System.exit(0);
						b = false;
					}
				}
			}

		} catch (Exception exc) {
			exc.printStackTrace();
			System.out.println("MyThread interrupted.");
		}

	}

}
