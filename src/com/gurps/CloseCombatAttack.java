package com.gurps;

public class CloseCombatAttack extends Attack
{
	private Type _type;

	enum Type {
		SWING,
		THRUST
	}

	public Type getType() {
		return _type;
	}

	public void setType(Type type) {
		this._type = type;
	}
}
