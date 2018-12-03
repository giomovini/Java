package atividadeCapitais;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Principal {

	static int[] visitas = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };

	ArrayList<Integer> perc = new ArrayList<>();

	public static void main(String[] args) {

		Principal p = new Principal();

		Cidade matriz[][] = p.montarMatriz();

		String CidadesInicio[] = { "Porto Alegre", "Florianopolis", "Curitiba", "Sao Paulo", "Campo Grande",
				"Rio De Janeiro", "Vitoria", "Belo Horizonte", "Cuiaba", "Goiania" };

		int inicio = JOptionPane.showOptionDialog(null, "De onde deseja partir?", "Inicio", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, CidadesInicio, CidadesInicio[0]);

		String CidadesFim[] = new String[CidadesInicio.length - 1];
		for (int i = 0, j = 0; i < CidadesInicio.length; i++) {
			if (i != inicio) {
				CidadesFim[j] = CidadesInicio[i];
				j++;
			}

		}

		int fim = JOptionPane.showOptionDialog(null, "Ate onde deseja ir?", "Fim", JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, CidadesFim, CidadesFim[0]);

		System.out.println("De: " + CidadesInicio[inicio] + " ate: " + CidadesFim[fim]);

		fim = p.buscaIDcidade(CidadesFim[fim]);

		System.out.println("-----BUSCA EM LARGURA-----");
		p.buscaMatrizLargura(inicio, fim, matriz, -1, -1);
		visitas[0] = 0;
		visitas[1] = 0;
		visitas[2] = 0;
		visitas[3] = 0;
		visitas[4] = 0;
		visitas[5] = 0;
		visitas[6] = 0;
		visitas[7] = 0;
		visitas[8] = 0;
		visitas[9] = 0;

		System.out.println("\n-----BUSCA EM PROFUNDIDADE-----");
		p.buscaProfundidade(matriz, inicio, fim);
		p.buscaProfundidade2(matriz,inicio, fim);
		
		matriz[2][3].setDistanciaAteCidade(matriz[2][3].getDistanciaAteCidade()*3);
		matriz[3][2].setDistanciaAteCidade(matriz[3][2].getDistanciaAteCidade()*3);
		matriz[3][5].setDistanciaAteCidade(matriz[3][2].getDistanciaAteCidade()*4);
		matriz[3][5].setDistanciaAteCidade(matriz[3][2].getDistanciaAteCidade()*4);
		
		visitas[0] = 0;
		visitas[1] = 0;
		visitas[2] = 0;
		visitas[3] = 0;
		visitas[4] = 0;
		visitas[5] = 0;
		visitas[6] = 0;
		visitas[7] = 0;
		visitas[8] = 0;
		visitas[9] = 0;
		
		System.out.println("-----BUSCA EM AStar-----");
		p.buscaMatrizLargura(inicio, fim, matriz, -1, -1);
		

	}

	public void buscaMatrizLargura(int inicio, int fim, Cidade matriz[][], int k, int pai) {

		visitas[inicio] = 1;

		int vizinhos[] = buscaVizinhos(matriz, inicio);

		boolean b = verificaFim(fim, matriz, vizinhos);

		// 1
		if (b) {
			System.out.println(buscaNomecidade(inicio) + " -(" + matriz[inicio][fim].distanciaAteCidade + ")- "
					+ buscaNomecidade(fim));
		}

		if (!b) {

			// 2
			for (int i = 0; i < vizinhos.length; i++) {
				if (vizinhos[i] != -1) {
					b = verificaFim(fim, matriz, buscaVizinhos(matriz, vizinhos[i]));

					visitas[i] = 1;
					if (b) {
						System.out.print(buscaNomecidade(inicio) + " -("
								+ matriz[inicio][vizinhos[i]].distanciaAteCidade + ")- ");
						System.out.println(buscaNomecidade(vizinhos[i]) + " -("
								+ matriz[vizinhos[i]][fim].distanciaAteCidade + ")- " + buscaNomecidade(fim));
						i = vizinhos.length;
					}
				}

			}
		}

		if (!b) {

			// 3
			for (int i = 0; i < vizinhos.length; i++) {
				if (vizinhos[i] != -1) {

					int vizinhos2[] = buscaVizinhos(matriz, vizinhos[i]);

					for (int j = 0; j < vizinhos2.length; j++) {
						if (vizinhos2[j] != -1) {
							b = verificaFim(fim, matriz, buscaVizinhos(matriz, vizinhos2[j]));
							// System.out.print("[" + buscaNomecidade(vizinhos2[j]) + "]");
							if (b) {
								System.out.print(buscaNomecidade(inicio) + " -("
										+ matriz[inicio][vizinhos[i]].distanciaAteCidade + ")- ");

								System.out.print(buscaNomecidade(vizinhos[i]) + " -("
										+ matriz[vizinhos[i]][vizinhos2[j]].distanciaAteCidade + ")- ");
								System.out.println(buscaNomecidade(vizinhos2[j]) + " -("
										+ matriz[vizinhos2[j]][fim].distanciaAteCidade + ")- " + buscaNomecidade(fim));
								i = vizinhos.length;
								j = vizinhos2.length;
							}
						}

					}
				}

			}
		}

	}

	public int[] buscaVizinhos(Cidade[][] matriz, int inicio) {
		// System.out.println();

		int vizinhos[] = new int[matriz.length];

		for (int i = 0; i < matriz.length; i++) {
			vizinhos[i] = -1;

			if (matriz[inicio][i] != null) {
				// System.out.print("[" + buscaNomecidade(matriz[inicio][i].id) + "] ");
				vizinhos[i] = matriz[inicio][i].id;
			}

		}

		return vizinhos;
	}

	public void buscaProfundidade2(Cidade matriz[][], int inicio, int fim) {

		for (int i = 0; i < perc.size(); i++) {
			if (perc.get(i) == fim) {
				System.out.println(buscaNomecidade(perc.get(i)));
				i = perc.size();
			} else {
				System.out.printf(buscaNomecidade(perc.get(i)) +" -("+matriz[perc.get(i)][perc.get(i+1)].getDistanciaAteCidade()+")- " );
				
			}
		}

	}

	public void buscaProfundidade(Cidade matriz[][], int inicio, int fim) {
		visitas[inicio] = 1;
		// System.out.println(buscaNomecidade(inicio));
		perc.add(inicio);

		int i;
		int v[] = buscaVizinhos(matriz, inicio);
		for (i = 0; i < v.length; i++) {

			if (v[i] != -1) {
				if (visitas[i] == 0) {
					buscaProfundidade(matriz, i, fim);
				}
			}
		}

		// percArv.add(raiz); // qnd n tem + pra onde ir guarda a raiz anterior
	}

	public boolean verificaFim(int fim, Cidade matriz[][], int vizinhos[]) {

		for (int i = 0; i < vizinhos.length; i++) {
			if (vizinhos[i] != -1 && vizinhos[i] == fim) {
				// System.out.println("\nAchou fim");
				return true;
			}
		}

		return false;

	}

	public Cidade[][] montarMatriz() {

		int RS = 0;
		int SC = 1;
		int PR = 2;
		int SP = 3;
		int MS = 4;
		int RJ = 5;
		int ES = 6;
		int MG = 7;
		int MT = 8;
		int GO = 9;

		Cidade NULL_ = null;
		Cidade RS_SC = new Cidade(SC, 458);
		Cidade RS_MS = new Cidade(MS, 1421);

		Cidade SC_RS = new Cidade(RS, 458);
		Cidade SC_PR = new Cidade(PR, 305);

		Cidade PR_SC = new Cidade(SC, 305);
		Cidade PR_SP = new Cidade(SP, 424);
		Cidade PR_MS = new Cidade(MS, 1000);

		Cidade SP_PR = new Cidade(PR, 424);
		Cidade SP_MS = new Cidade(MS, 994);
		Cidade SP_RJ = new Cidade(RJ, 442);
		Cidade SP_MG = new Cidade(MG, 589);
		Cidade SP_MT = new Cidade(MT, 1666);
		Cidade SP_GO = new Cidade(GO, 914);

		Cidade MS_RS = new Cidade(RS, 1421);
		Cidade MS_PR = new Cidade(PR, 1000);
		Cidade MS_SP = new Cidade(SP, 994);
		Cidade MS_MG = new Cidade(MG, 1275);
		Cidade MS_MT = new Cidade(MT, 847);
		Cidade MS_GO = new Cidade(GO, 844);

		Cidade RJ_SP = new Cidade(SP, 442);
		Cidade RJ_ES = new Cidade(ES, 521);
		Cidade RJ_MG = new Cidade(MG, 441);

		Cidade ES_RJ = new Cidade(RJ, 521);
		Cidade ES_MG = new Cidade(MG, 567);
		Cidade ES_MT = new Cidade(MT, 2265);
		Cidade ES_GO = new Cidade(GO, 1420);

		Cidade MG_SP = new Cidade(SP, 589);
		Cidade MG_MS = new Cidade(MS, 1275);
		Cidade MG_RJ = new Cidade(RJ, 441);
		Cidade MG_ES = new Cidade(ES, 567);
		Cidade MG_GO = new Cidade(GO, 888);

		Cidade MT_SP = new Cidade(SP, 1666);
		Cidade MT_MS = new Cidade(MS, 847);
		Cidade MT_MG = new Cidade(MG, 2265);
		Cidade MT_GO = new Cidade(GO, 905);

		Cidade GO_SP = new Cidade(SP, 914);
		Cidade GO_MS = new Cidade(MS, 844);
		Cidade GO_ES = new Cidade(ES, 1420);
		Cidade GO_MG = new Cidade(MG, 888);
		Cidade GO_MT = new Cidade(MT, 905);

		// vizinhos de porto alegre
		Cidade PortoAlegre[] = { NULL_, RS_SC, NULL_, NULL_, RS_MS, NULL_, NULL_, NULL_, NULL_, NULL_ };
		Cidade Florianopolis[] = { SC_RS, NULL_, SC_PR, NULL_, NULL_, NULL_, NULL_, NULL_, NULL_, NULL_ };
		Cidade Curitiba[] = { NULL_, PR_SC, NULL_, PR_SP, PR_MS, NULL_, NULL_, NULL_, NULL_, NULL_ };
		Cidade SaoPaulo[] = { NULL_, NULL_, SP_PR, NULL_, SP_MS, SP_RJ, NULL_, SP_MG, SP_MT, SP_GO };
		Cidade CampoGrande[] = { MS_RS, NULL_, MS_PR, MS_SP, NULL_, NULL_, NULL_, MS_MG, MS_MT, MS_GO };
		Cidade RioDeJaneiro[] = { NULL_, NULL_, NULL_, RJ_SP, NULL_, NULL_, RJ_ES, RJ_MG, NULL_, NULL_ };
		Cidade Vitoria[] = { NULL_, NULL_, NULL_, NULL_, NULL_, ES_RJ, NULL_, ES_MG, ES_MT, ES_GO };
		Cidade BeloHorizonte[] = { NULL_, NULL_, NULL_, MG_SP, MG_MS, MG_RJ, MG_ES, NULL_, NULL_, MG_GO };
		Cidade Cuiaba[] = { NULL_, NULL_, NULL_, MT_SP, MT_MS, NULL_, NULL_, MT_MG, NULL_, MT_GO };
		Cidade Goiania[] = { NULL_, NULL_, NULL_, GO_SP, GO_MS, NULL_, GO_ES, GO_MG, GO_MT, NULL_ };

		Cidade matriz[][] = { PortoAlegre, Florianopolis, Curitiba, SaoPaulo, CampoGrande, RioDeJaneiro, Vitoria,
				BeloHorizonte, Cuiaba, Goiania };
		return matriz;
	}

	public String buscaNomecidade(int id) {

		switch (id) {
		case 0:
			return "Porto Alegre";
		case 1:
			return "Florianopolis";
		case 2:
			return "Curitiba";
		case 3:
			return "Sao Paulo";
		case 4:
			return "Campo Grande";
		case 5:
			return "Rio De Janeiro";
		case 6:
			return "Vitoria";
		case 7:
			return "Belo Horizonte";
		case 8:
			return "Cuiaba";
		case 9:
			return "Goiania";

		default:
			return "N";
		}

	}

	public int buscaIDcidade(String Cidade) {

		switch (Cidade) {
		case "Porto Alegre":
			return 0;
		case "Florianopolis":
			return 1;
		case "Curitiba":
			return 2;
		case "Sao Paulo":
			return 3;
		case "Campo Grande":
			return 4;
		case "Rio De Janeiro":
			return 5;
		case "Vitoria":
			return 6;
		case "Belo Horizonte":
			return 7;
		case "Cuiaba":
			return 8;
		case "Goiania":
			return 9;
		default:
			return -1;
		}

	}

}
