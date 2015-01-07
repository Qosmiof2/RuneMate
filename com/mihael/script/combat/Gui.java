package com.mihael.script.combat;

import com.mihael.script.combat.tasks.AntiBan;
import com.mihael.script.combat.tasks.Attack;
import com.mihael.script.combat.tasks.Eat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Gui {

	private JFrame frame;
	private static JTextField npc = new JTextField();
	private static JTextField food = new JTextField();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.setForeground(Color.DARK_GRAY);
		frame.setBackground(Color.DARK_GRAY);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				Gui.class.getResource("/resources/images/icon.png")));
		frame.setTitle("Fighter");
		frame.setBounds(100, 100, 189, 143);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);

		npc.setText("Npc");
		npc.setBounds(10, 11, 153, 20);
		frame.getContentPane().add(npc);
		npc.setColumns(10);

		food.setText("Food");
		food.setBounds(10, 42, 153, 20);
		frame.getContentPane().add(food);
		food.setColumns(10);

		JButton start = new JButton("Start");
		start.setBounds(10, 73, 153, 23);
		frame.getContentPane().add(start);

		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Data.NPC.setFoodName(food.getText());
				Data.NPC.setNpcs(getNPCs(npc.getText()));
				Fighter.nodeList.add(new Attack());
				Fighter.nodeList.add(new AntiBan());
				Fighter.nodeList.add(new Eat());
				frame.dispose();

			}
		});
	}

	private static String[] getNPCs(String str) {
		for (int i = 0; i < str.split(",").length; i++) {
			if (i >= 1) {
				npcList.add(i, str.split(",")[i].trim());
			} else {
				npcList.add(str.split(",")[i]);
			}
		}

		String[] s = new String[npcList.size()];
		s = npcList.toArray(s);

		for (String string : s) {
			System.out.println(string);
		}
		return s;
	}

	private static ArrayList<String> npcList = new ArrayList<String>();
}
