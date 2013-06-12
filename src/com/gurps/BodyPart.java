package com.gurps;


public class BodyPart
{
	private String _name;
	private int _armorValue;
	private int _dmgFactor;
	private int _hitFactor;
	private ArmorType _armorType;

	private enum ArmorType{
		RIGID,
		FLEXIBLE,
		TOUGH
	}

	public ArmorType getArmorType() {
		return _armorType;
	}

	public void setArmorType(ArmorType armorType) {
		this._armorType = armorType;
	}

	public int getHitFactor() {
		return _hitFactor;
	}

	public void setHitFactor(int hitFactor) {
		this._hitFactor = hitFactor;
	}

	public int getDmgFactor() {
		return _dmgFactor;
	}

	public void setDmgFactor(int dmgFactor) {
		this._dmgFactor = dmgFactor;
	}

	public int getArmorValue() {
		return _armorValue;
	}

	public void setArmorValue(int armorValue) {
		this._armorValue = armorValue;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}
}
