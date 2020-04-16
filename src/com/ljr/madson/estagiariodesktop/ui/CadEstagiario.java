package com.ljr.madson.estagiariodesktop.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.ljr.madson.estagiariodesktop.persistence.dao.EstagiarioDao;
import com.ljr.madson.estagiariodesktop.persistence.vo.Estagiario;

@SuppressWarnings("serial")
public class CadEstagiario extends JDialog {

	private JTextField txtCpf;
	private JTextField txtNome;
	private JTextField txtTelefone;
	private JButton btnNovo;
	private JButton btnSalvar;
	private JButton btnCancelar;
	private JButton btnExcluir;
	private JButton btnFechar;
	private JButton btnAlterar;
	private JButton btnPrimeiro;
	private JButton btnAnterior;
	private JButton btnProximo;
	private JButton btnUltimo;

	private boolean novo;
	private int posicao = 0;
	private Estagiario estagiario;
	private List<Estagiario> estagiarios;

	public CadEstagiario() throws ClassNotFoundException, SQLException {

		setTitle("Estagiario");
		setModal(true);
		setBounds(100, 100, 540, 419);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel pnlSuperior = new JPanel();
			pnlSuperior.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
					null));
			getContentPane().add(pnlSuperior, BorderLayout.NORTH);
			{
				JLabel lblCadastroDeEstagiarios = new JLabel(
						"Cadastro de Estagiarios");
				lblCadastroDeEstagiarios.setFont(new Font("Tahoma", Font.PLAIN,
						13));
				pnlSuperior.add(lblCadastroDeEstagiarios);
			}
		}
		{
			JPanel pnlInferior = new JPanel();
			pnlInferior.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
					null));
			getContentPane().add(pnlInferior, BorderLayout.SOUTH);
			{
				btnPrimeiro = new JButton("<<");
				btnPrimeiro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						irPrimeiro();
					}
				});
				pnlInferior.add(btnPrimeiro);
			}
			{
				btnAnterior = new JButton("<");
				btnAnterior.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (posicao > 0) {
							posicao--;
							estagiario = estagiarios.get(posicao);
							preencheTela(estagiario);
						} else {
							JOptionPane.showMessageDialog(null, "Acabou");
						}
					}
				});
				pnlInferior.add(btnAnterior);
			}
			{
				btnProximo = new JButton(">");
				btnProximo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						if (posicao + 1 < estagiarios.size()) {
							posicao++;
							estagiario = estagiarios.get(posicao);
							preencheTela(estagiario);
						} else {
							JOptionPane.showMessageDialog(null, "Acabou");
						}
					}
				});
				pnlInferior.add(btnProximo);
			}
			{
				btnUltimo = new JButton(">>");
				btnUltimo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						estagiario = estagiarios.get(estagiarios.size() - 1);
						preencheTela(estagiario);
					}
				});
				pnlInferior.add(btnUltimo);
			}
		}
		{
			JPanel pnlBotoes = new JPanel();
			pnlBotoes.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
					null));
			FlowLayout flowLayout = (FlowLayout) pnlBotoes.getLayout();
			flowLayout.setVgap(10);
			flowLayout.setHgap(10);
			pnlBotoes.setPreferredSize(new Dimension(80, 10));
			getContentPane().add(pnlBotoes, BorderLayout.EAST);
			{
				btnNovo = new JButton("Novo");
				btnNovo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						novo();
					}

				});
				btnNovo.setPreferredSize(new Dimension(75, 23));
				pnlBotoes.add(btnNovo);
			}
			{
				btnAlterar = new JButton("Alterar");
				btnAlterar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						ativaControles(true);
						novo = false;
					}
				});
				btnAlterar.setPreferredSize(new Dimension(75, 23));
				pnlBotoes.add(btnAlterar);
			}
			{
				btnSalvar = new JButton("Salvar");
				btnSalvar.setEnabled(false);
				btnSalvar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						ativaControles(false);
						estagiario.setCpf(txtCpf.getText());
						estagiario.setNome(txtNome.getText());
						estagiario.setTel(Integer.parseInt(txtTelefone.getText()));

						EstagiarioDao dao = new EstagiarioDao();
						try {
							if ( novo ) {
								dao.adciona(estagiario);
								estagiarios.add(estagiario);
								posicao = estagiarios.size() - 1;
							} else {
								dao.atualizar(estagiario);								
							}
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				btnSalvar.setPreferredSize(new Dimension(75, 23));
				pnlBotoes.add(btnSalvar);
			}
			{
				btnCancelar = new JButton("Cancelar");
				btnCancelar.setEnabled(false);
				btnCancelar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ativaControles(false);
					}
				});
				pnlBotoes.add(btnCancelar);
			}
			{
				btnExcluir = new JButton("Excluir");
				btnExcluir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						EstagiarioDao dao = new EstagiarioDao();
						try {
							dao.remove(estagiario);
							estagiarios.remove(estagiario);
							irPrimeiro();
						} catch (ClassNotFoundException e1) {
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				});
				btnExcluir.setPreferredSize(new Dimension(75, 23));
				btnExcluir.setMinimumSize(new Dimension(75, 23));
				btnExcluir.setMaximumSize(new Dimension(75, 23));
				pnlBotoes.add(btnExcluir);
			}
			{
				btnFechar = new JButton("Fechar");
				btnFechar.setPreferredSize(new Dimension(75, 23));
				btnFechar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				pnlBotoes.add(btnFechar);
			}
		}
		{
			JPanel pnlCentral = new JPanel();
			pnlCentral.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
					null));
			getContentPane().add(pnlCentral, BorderLayout.CENTER);
			pnlCentral.setLayout(null);
			{
				JLabel lblCpf = new JLabel("Cpf");
				lblCpf.setBounds(12, 13, 46, 14);
				pnlCentral.add(lblCpf);
			}
			{
				txtCpf = new JTextField();
				txtCpf.setEnabled(false);
				txtCpf.setBounds(12, 30, 119, 26);
				pnlCentral.add(txtCpf);
				txtCpf.setColumns(10);
			}
			{
				JLabel lblNome = new JLabel("Nome");
				lblNome.setBounds(12, 63, 46, 14);
				pnlCentral.add(lblNome);
			}
			{
				txtNome = new JTextField();
				txtNome.setEnabled(false);
				txtNome.setBounds(12, 83, 223, 26);
				pnlCentral.add(txtNome);
				txtNome.setColumns(10);
			}
			{
				JLabel lblTelefone = new JLabel("Telefone");
				lblTelefone.setBounds(12, 116, 62, 14);
				pnlCentral.add(lblTelefone);
			}
			{
				txtTelefone = new JTextField();
				txtTelefone.setEnabled(false);
				txtTelefone.setBounds(12, 133, 99, 26);
				pnlCentral.add(txtTelefone);
				txtTelefone.setColumns(10);
			}
		}

		EstagiarioDao dao = new EstagiarioDao();
		estagiarios = dao.pegaLista();
		if(estagiarios.size() == 0) {
			novo();
			
		}else {
			estagiario = estagiarios.get(posicao);
		}
		preencheTela(estagiario);
	}

	private void ativaControles(boolean ativo) {

		txtCpf.setEnabled(ativo);
		txtNome.setEnabled(ativo);
		txtTelefone.setEnabled(ativo);

		btnSalvar.setEnabled(ativo);
		btnCancelar.setEnabled(ativo);

		btnNovo.setEnabled(!ativo);
		btnExcluir.setEnabled(!ativo);
		btnFechar.setEnabled(!ativo);
		btnAlterar.setEnabled(!ativo);

		btnUltimo.setEnabled(!ativo);
		btnProximo.setEnabled(!ativo);
		btnAnterior.setEnabled(!ativo);
		btnPrimeiro.setEnabled(!ativo);
	}

	private void preencheTela(Estagiario estagiario) {

		txtCpf.setText(estagiario.getCpf());
		txtNome.setText(estagiario.getNome());
		txtTelefone.setText(Integer.toString((estagiario.getTel())));
	}

	private void irPrimeiro() {

		posicao = 0;
		estagiario = estagiarios.get(posicao);
		preencheTela(estagiario);
	}

	private void limpaTela() {

		txtCpf.setText("");
		txtNome.setText("");
		txtTelefone.setText("");

	}
	
	private void novo() {
		ativaControles(true);
		limpaTela();
		novo = true;
		estagiario = new Estagiario();
	}

}
