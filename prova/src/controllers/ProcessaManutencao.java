package controllers;

import java.util.ArrayList;

import DAO.ManutencaoDAO;
import models.Manutencao;

public class ProcessaManutencao {

	public static ArrayList<Manutencao> manutencoes = new ArrayList<>();
	private static ManutencaoDAO md = new ManutencaoDAO();

	public static void abrir() {
		manutencoes = md.ler();
	}

	public static void salvar() {
		md.escrever(manutencoes);
	}
}