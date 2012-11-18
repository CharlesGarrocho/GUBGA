package com.gubga.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public abstract class Dialogo extends JDialog {

	private static final long serialVersionUID = 1L;

	public Dialogo() {
		super();
	}
	
	protected abstract void criarElementos();
	
	protected abstract void adicionarElementos();
	
	protected abstract void customizarElementos();
	
	protected abstract void configurarEventos();
	
	public void definirPropriedades(JFrame janelaPai, String titulo, Dimension dimensao) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle(titulo);
		if (dimensao == null)
			pack();
		else
			setSize(dimensao);
		setIconImage(Toolkit.getDefaultToolkit().getImage(getResource("garena_logo.png")));
		setLocationRelativeTo(janelaPai);
		setResizable(false);
		setVisible(true);
	}
	
	/** Encontra o recurso no diretorio de recursos do jar
	 * @param enderecoArquivo <code>String</code> caminho do recurso
	 * @return <code>URL</code> com o endereco do recurso
	 */
	public static URL getResource(String enderecoArquivo){
		return Janela.class.getResource("/icones/" + enderecoArquivo);
	}
	
}