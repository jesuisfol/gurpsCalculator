package com.gurps;

// TODO : pertinence de cette classe ? A inclure dans Character plutot ?

public class Defense
{
	private int _parry;
	private int _dodge;
	private int _block;

	public int getParry() {
		return _parry;
	}

	public void setParry(int parry) {
		this._parry = parry;
	}

	public int getDodge() {
		return _dodge;
	}

	public void setDodge(int dodge) {
		this._dodge = dodge;
	}

	public int getBlock() {
		return _block;
	}

	public void setBlock(int block) {
		this._block = block;
	}
}
