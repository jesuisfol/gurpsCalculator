package com.gurps;

import com.gurps.sql.GurpsBase;

public class Main {

/*
	public static void main(String[] args) {
		System.out.println("Hello World!");
	}
*/
public static void main(String[] args)
{
	try
	{
		GurpsBase g = new GurpsBase();
	}
	catch (Exception e)
	{
		System.err.println(e.getMessage());
	}
}


}
