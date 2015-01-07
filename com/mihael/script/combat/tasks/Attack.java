package com.mihael.script.combat.tasks;

import com.mihael.script.combat.Data;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.Filter;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import framework.Node;



public class Attack extends Node {

    public Attack() {

    }

    @Override
    public boolean activate() {
        return Health.getCurrentPercent() > 30
                && (Players.getLocal().getTarget() == null && Players
                .getLocal().getAnimationId() == -1);
    }

    @Override
    public void execute() {
        final Npc npc;
        switch (Random.nextInt(1, 10)) {

            case 5:
                npc = Npcs.newQuery().names(Data.NPC.getNpcs()).actions("Attack")
                        .filter(new Filter<Npc>() {

                            @Override
                            public boolean accepts(Npc n) {
                                return n.getAnimationId() == -1
                                        && n.getTarget() == null
                                        && Players.getLocal().getTarget() == null;
                            }
                        }).results().limit(2).random();
                break;

            default:
                npc = Npcs.newQuery().names(Data.NPC.getNpcs()).actions("Attack")
                        .filter(new Filter<Npc>() {

                            @Override
                            public boolean accepts(Npc n) {
                                return n.getAnimationId() == -1
                                        && n.getTarget() == null
                                        && Players.getLocal().getTarget() == null;
                            }
                        }).results().nearest();
                break;
        }


        if (npc != null && npc.getTarget() == null
                && Players.getLocal().getTarget() == null) {
            if (Players.getLocal().getTarget() == null) {
                if (npc.getVisibility() >= 50.00
                        && npc.distanceTo(Players.getLocal()) <= 10) {
                    if (npc.interact("Attack")) {
                        Execution.delayUntil(() -> Players.getLocal().getTarget() != null
                                || Players.getLocal().getAnimationId() != -1, Random.nextInt(3000, 5000));
                    }
                } else {
                    Camera.turnTo(npc.getPosition().randomize(0, 20));
                    if (!npc.isVisible()
                            || npc.distanceTo(Players.getLocal()) > 10) {
                        WebPath patha = Traversal.getDefaultWeb()
                                .getPathBuilder()
                                .buildTo(npc.getPosition().randomize(0, 3));

                        if (patha != null) {
                            if (patha.getNext() != null) {
                                if (patha.getNext().getPosition().isVisible()) {
                                    if (patha.getNext().getPosition()
                                            .interact("Walk here")) {
                                        Execution.delayUntil(
                                                () -> Players
                                                        .getLocal()
                                                        .isMoving()
                                                        && npc.getPosition()
                                                        .distanceTo(
                                                                Players.getLocal()) < 10, 3000);
                                    }
                                } else {
                                    patha.step(true);
                                    Execution.delayUntil(() -> Players.getLocal().isMoving(), 5000);
                                }
                            }
                        }
                    }
                }
            }

        }
    }
}
