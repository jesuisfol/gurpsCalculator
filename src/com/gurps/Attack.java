package com.gurps;

abstract class Attack
{
	private Character _target;
	private int _damage;
	private double _critMultiplier;
	private int _range;
	private int _bonus;
	private Dice _dice;
	private int _minStr;

	Character getTarget() {
		return _target;
	}

	void setTarget(Character _target) {
		this._target = _target;
	}

	int getDamage() {
		return _damage;
	}

	void setDamage(int _damage) {
		this._damage = _damage;
	}

	double getCritMultiplier() {
		return _critMultiplier;
	}

	void setCritMultiplier(double _critMultiplier) {
		this._critMultiplier = _critMultiplier;
	}

	int getRange() {
		return _range;
	}

	void setRange(int _range) {
		this._range = _range;
	}

	int getBonus() {
		return _bonus;
	}

	void setBonus(int _bonus) {
		this._bonus = _bonus;
	}

	Dice getDice() {
		return _dice;
	}

	void setDice(Dice _dice) {
		this._dice = _dice;
	}

	int getminStr() {
		return _minStr;
	}

	void setminStr(int _minStr) {
		this._minStr = _minStr;
	}
}
