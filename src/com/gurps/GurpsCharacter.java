package com.gurps;

import java.util.HashSet;

/**
 * User: Fol de Dol
 * Date: 12/06/13
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */
public class GurpsCharacter
{
	private String _name = "Default Name";
	private int _hp = 100;
	private int _armor = 10;
	private HashSet<Weapon> _weapons = new HashSet<Weapon>();
	private int _speed = 10;
	private Stance _stance = Stance.NORMAL;
	private HashSet<DefenseType> _defenseUsed = new HashSet<DefenseType>();

	private enum Stance {
		NORMAL,
		ALL_IN,
		ALL_OUT
	}

	public enum DefenseType {
		DODGE,
		PARRY,
		BLOCK
	}

	/* *********************************************************
	 *                 Constructors & Destructors
	 ***********************************************************/
	public GurpsCharacter(String name, int hp) {
		_name = name;
		_hp = hp;
	}

	/* *********************************************************
	 *                 Class methods
	 ***********************************************************/

	public void attack(GurpsCharacter target, BodyPart part, Weapon weapon) {
		target.defend();
	}

	public void defend() {
	}
	/**
	 * Le personnage tente d'utiliser le type de défense donné, s'il n'a pas déjà été utilisé.
	 * @param DefenseType defenseTypes
	 * @return boolean
	 */
	public boolean useDefenseType(DefenseType defenseTypes) {
		if(!_defenseUsed.contains(defenseTypes)) {
			_defenseUsed.add(defenseTypes);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Réinitialise les défenses du personnage.
	 */
	public void resetDefenses() {
		_defenseUsed = new HashSet<DefenseType>();
	}

	/* *********************************************************
	 *                 GETTERS and SETTERS
	 ***********************************************************/
}
