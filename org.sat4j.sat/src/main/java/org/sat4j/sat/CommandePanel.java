/*******************************************************************************
 * SAT4J: a SATisfiability library for Java Copyright (C) 2004-2008 Daniel Le Berre
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Alternatively, the contents of this file may be used under the terms of
 * either the GNU Lesser General Public License Version 2.1 or later (the
 * "LGPL"), in which case the provisions of the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of the LGPL, and not to allow others to use your version of
 * this file under the terms of the EPL, indicate your decision by deleting
 * the provisions above and replace them with the notice and other provisions
 * required by the LGPL. If you do not delete the provisions above, a recipient
 * may use your version of this file under the terms of the EPL or the LGPL.
 * 
 * Based on the original MiniSat specification from:
 * 
 * An extensible SAT solver. Niklas Een and Niklas Sorensson. Proceedings of the
 * Sixth International Conference on Theory and Applications of Satisfiability
 * Testing, LNCS 2919, pp 502-518, 2003.
 *
 * See www.minisat.se for the original solver in C++.
 * 
 *******************************************************************************/
package org.sat4j.sat;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * 
 * This panel contains buttons that control restart and clean on solver.
 * It also displays history of commands.
 * 
 * @author sroussel
 *
 */
public class CommandePanel extends JPanel{
	
	
	private static final long serialVersionUID = 1L;

	private TelecommandeStrategy telecomStrategy;
	
	public final static String RESTART = "Restart";
	public final static String CLEAN = "Clean";
	
	private JButton restartButton;
	private JButton cleanButton;
	
	private JTextArea console;
	
	public CommandePanel(TelecommandeStrategy telecomStrategy){
		super();
		
		this.setPreferredSize(new Dimension(200,200));
		
		this.telecomStrategy = telecomStrategy;
		
		restartButton = new JButton(RESTART);
		
		restartButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hasClickedOnRestart();
			}
		});
		
		cleanButton = new JButton(CLEAN);
		
		cleanButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hasClickedOnClean();
			}
		});
		
		console = new JTextArea();
		
		JScrollPane scrollPane = new JScrollPane(console);

		//scrollPane.setMinimumSize(new Dimension(100,100));
		scrollPane.setPreferredSize(new Dimension(100,100));	
		
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(restartButton);
		this.add(cleanButton);
		this.add(scrollPane);
	
		
	}
	
	public void hasClickedOnRestart(){
		telecomStrategy.setHasClickedOnRestart(true);
		console.append("Has clicked on " + RESTART + "\n");
		console.repaint();
		this.repaint();
	}
	
	public void hasClickedOnClean(){
		telecomStrategy.setHasClickedOnClean(true);
		console.append("Has clicked on " + CLEAN + "\n");
		console.repaint();
		this.repaint();
	}
	
//	public void maj(){
//		console.append(fauxModele.getLastCommande() + "\n");
//		console.repaint();
//		this.repaint();
//	}

}