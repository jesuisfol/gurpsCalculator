package com.gurps;


public class RangedAttack extends Attack
{
	private int _halfRange;
	private int _rateOfFire;
	private int _speed;
	private int _shots;
	private int _reloadTime;
	private int _intrinsicStr; // TODO : mieux définir ce bazar
	private int _bulk;
	private int _recoil;

	public int getHalfRange() {
		return _halfRange;
	}

	public void setHalfRange(int halfRange) {
		this._halfRange = halfRange;
	}

	public int getRateOfFire() {
		return _rateOfFire;
	}

	public void setRateOfFire(int rateOfFire) {
		this._rateOfFire = rateOfFire;
	}

	public int getSpeed() {
		return _speed;
	}

	public void setSpeed(int speed) {
		this._speed = speed;
	}

	public int getShots() {
		return _shots;
	}

	public void setShots(int shots) {
		this._shots = shots;
	}

	public int getReloadTime() {
		return _reloadTime;
	}

	public void setReloadTime(int reloadTime) {
		this._reloadTime = reloadTime;
	}

	public int getIntrinsicStr() {
		return _intrinsicStr;
	}

	public void setIntrinsicStr(int intrinsicStr) {
		this._intrinsicStr = intrinsicStr;
	}

	public int getBulk() {
		return _bulk;
	}

	public void setBulk(int bulk) {
		this._bulk = bulk;
	}

	public int getRecoil() {
		return _recoil;
	}

	public void setRecoil(int recoil) {
		this._recoil = recoil;
	}
}
