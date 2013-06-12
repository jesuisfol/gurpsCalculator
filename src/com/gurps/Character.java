package com.gurps;

/**
 * User: Fol de Dol
 * Date: 12/06/13
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class Character
{
	private int _hp;
	private int _armor;
	private int _speed;
	private Stance _stance;
	private Defense _defense;

	private enum Stance {
		NORMAL,
		ALL_IN,
		ALL_OUT
	}

	public Defense getDefense() {
		return _defense;
	}

	public void setDefense(Defense defense) {
		this._defense = defense;
	}

	public Stance getStance() {
		return _stance;
	}

	public void setStance(Stance stance) {
		this._stance = stance;
	}

	public int getHp() {
		return _hp;
	}

	public void setHp(int hp) {
		this._hp = hp;
	}

	public int getArmor() {
		return _armor;
	}

	public void setArmor(int armor) {
		this._armor = armor;
	}

	public int getSpeed() {
		return _speed;
	}

	public void setSpeed(int speed) {
		this._speed = speed;
	}
}
