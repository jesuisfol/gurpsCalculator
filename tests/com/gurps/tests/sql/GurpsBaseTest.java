package com.gurps.tests.sql;

import com.gurps.GurpsCharacter;
import com.gurps.sql.GurpsBase;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;
import java.io.File;


public class GurpsBaseTest
{
	private GurpsBase _gurpsBase;
	private Statement _statement;

	/**
	 * Test d'initialisation de la base. Un peu particulier, init et test en même temps...
	 */
	// @TODO : voir si on peut pas faire autrement.
	@BeforeClass
	@Test
	public void setUp()
	{
		try
		{
			String fileName = "gurps.sqlite";
			File baseFile = new File(fileName);
			baseFile.delete();

			// Création et vérification du fichier de database
			_gurpsBase = new GurpsBase();
			baseFile = new File(fileName);
			Assert.assertTrue(baseFile.exists());

			// Verification de l'existence d'une table
			//@todo : vérifier les autres tables !
			Class.forName("org.sqlite.JDBC");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
			_statement = connection.createStatement();
			_statement.setQueryTimeout(30);

			Assert.assertNotNull(_statement.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='character';"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail();
		}
	}

	@AfterClass
	public void tearDown()
	{
		try
		{
			File file = new File("gurps.sqlite");
			file.delete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@DataProvider
	public Object[][] characterProvider()
	{
		return new Object[][] {
				{"toto", 3}, {"pouet", 12}
		};
	}

	@Test (dataProvider = "characterProvider")
	public void createCharacter(final String name, final int hp)
	{
		GurpsCharacter character = new GurpsCharacter(name, hp);
		_gurpsBase.createCharacter(character);
		GurpsCharacter charFromBase = _gurpsBase.getCharacter(name);

		try (ResultSet rs = _statement.executeQuery("SELECT name FROM character WHERE name='"+name+"'"))
		{
			while (rs.next())
			{
				Assert.assertEquals(name, rs.getString("name"));
				Assert.assertEquals(hp, rs.getString("health"));
			}
		}
		catch (SQLException e)
		{
			Assert.fail();
		}
	}
}
