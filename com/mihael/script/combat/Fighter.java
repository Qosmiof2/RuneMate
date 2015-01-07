package com.mihael.script.combat;

import com.runemate.game.api.client.paint.PaintListener;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.framework.LoopingScript;
import framework.Node;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;


public class Fighter extends LoopingScript implements PaintListener {

	public static ArrayList<Node> nodeList = new ArrayList<Node>();
	private int exp, startExp, expPH, startStr, startAtt, startDef;
	public static StopWatch runTime = new StopWatch();
	private final Color color = new Color(0, 0, 0, 120);
	private String string, string1, string2;

	@Override
	public void onStart(String... arg0) {
		startExp = Skill.ATTACK.getExperience() + Skill.DEFENCE.getExperience()
				+ Skill.STRENGTH.getExperience() + Skill.RANGE.getExperience()
				+ Skill.CONSTITUTION.getExperience();

		startAtt = Skill.ATTACK.getCurrentLevel();
		startStr = Skill.STRENGTH.getCurrentLevel();
		startDef = Skill.DEFENCE.getCurrentLevel();

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Gui();
			}
		});
		setLoopDelay(350, 500);
		getEventDispatcher().addListener(this);
		runTime.start();
		super.onStart(arg0);
	}

	@Override
	public void onPause() {
		runTime.stop();
		super.onPause();
	}

	@Override
	public void onResume() {
		runTime.start();
		super.onResume();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	public void onLoop() {
		if (!nodeList.isEmpty()) {
			for (Node node : nodeList) {
				if (node.activate()) {
					node.execute();
				}
			}
		}

	}

	@Override
	public void onPaint(Graphics2D g) {
		exp = Skill.ATTACK.getExperience() + Skill.DEFENCE.getExperience()
				+ Skill.STRENGTH.getExperience() + Skill.RANGE.getExperience()
				+ Skill.CONSTITUTION.getExperience();

		expPH = (int) ((exp - startExp) * (3600000D / runTime.getRuntime()));

		g.setColor(Color.white);
		g.drawRect(5, 5, 200, 105);
		g.setColor(color);
		g.fillRect(6, 6, 199, 104);

		g.setColor(Color.white);
		g.drawString(
				"Exp: " + format((exp - startExp), string, string1, string2)
						+ "( " + format(expPH, string, string1, string2) + " )",
				50, 30);
		g.drawString("Attack: " + Skill.ATTACK.getCurrentLevel() + "( "
				+ (Skill.ATTACK.getCurrentLevel() - startAtt) + " )", 50, 45);
		g.drawString("Strength: " + Skill.STRENGTH.getCurrentLevel() + "( "
				+ (Skill.STRENGTH.getCurrentLevel() - startStr) + " )", 50, 60);
		g.drawString("Defence: " + Skill.DEFENCE.getCurrentLevel() + "( "
				+ (Skill.DEFENCE.getCurrentLevel() - startDef) + " )", 50, 75);
		g.drawString("Running: " + runTime.getRuntimeAsString(), 50, 90);
	}

	private final NumberFormat format = NumberFormat.getInstance();

	private String format(int number, String string, String i, String string1) {

		if (number >= 1000) {
			string = format.format(number);
			string = string.substring(string.lastIndexOf(","));
			string = string.substring(0, string.length() - 1);

			string1 = "" + number;
			if (Integer.parseInt(string1) >= 1000000) {
				i = "M";
			} else if (Integer.parseInt(string1) >= 1000) {
				i = "K";
			} else {
				i = "";
			}
			string1 = "" + format.format(number);

			string1 = string1.replace(",", " ");
			string1 = string1.split(" ")[0];

		} else {
			string = "";
			string1 = "" + number;
			i = "";
		}

		if (number == 0) {
			string = "";
			string1 = "none";
			i = "";
		}

		if (number < 0) {
			string = "";
			string1 = "0";
			i = "";
		}
		return string1 + string.replace(",", ".") + i;

	}

}
