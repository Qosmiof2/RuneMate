package com.mihael.script.combat.tasks;

import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.Screen;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import framework.Node;


public class AntiBan extends Node {

	@Override
	public boolean activate() {
		// TODO Auto-generated method stub
		return Players.getLocal().getTarget() != null
				|| Players.getLocal().getAnimationId() != -1;
	}

	@Override
	public void execute() {
		switch (Random.nextInt(1, 300)) {

		case 20:
			Camera.setPitch(Random.nextDouble(0.450, 0.568));
			break;

		case 30:
			Camera.setPitch(Random.nextDouble(0.450, 0.568));
			Camera.setYaw(Random.nextInt(1, 360));
			break;

		case 50:
			Camera.setYaw(Random.nextInt(1, 360));
			break;

		default:
			switch (Random.nextInt(1, 100)) {
			case 15:
				Mouse.move(Screen.getBounds().getInteractionPoint());
				break;
			case 20:
				Mouse.move(Screen.getBounds().getInteractionPoint());

				break;
			}

			break;
		}

	}

}
