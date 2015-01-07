package com.mihael.script.combat.tasks;

import com.mihael.script.combat.Data;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.rs3.local.hud.interfaces.eoc.ActionBar;
import com.runemate.game.api.script.Execution;
import framework.Node;


import java.util.concurrent.Callable;

public class Eat extends Node {

	private int i;

	@Override
	public boolean activate() {
		i = Random.nextInt(30, 50);
		return Health.getCurrentPercent() <= i;
	}

	@Override
	public void execute() {
		if (!ActionBar.getActions(Data.NPC.getFood()).isEmpty()) {
			ActionBar.getActions(Data.NPC.getFood()).get(0).activate();
			Execution.delayUntil(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return Health.getCurrentPercent() > i;
				}
			});
		}

	}

}
