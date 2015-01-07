package com.mihael.script.combat;

public enum Data {

	NPC(new String[]{}, "");

	private String[] npc;
	private String food;

	private Data(String[] npc, String food) {
		this.npc = npc;
		this.food = food;

	}

	public String[] getNpcs() {
		return npc;
	}

	public String getFood() {
		return food;
	}

	public String[] setNpcs(String[] npc) {
		return this.npc = npc;
	}

	public void setFoodName(String food) {
		this.food = food;
	}
}
