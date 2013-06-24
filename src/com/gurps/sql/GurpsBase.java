package com.gurps.sql;

import com.gurps.GurpsCharacter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe de gestion de la base de données SQLite
 * @todo : faire marcher les INDEX ?
 */
public class GurpsBase
{
	private Connection _connection;
	private Statement _statement;

	/**
	 * Constructeur par défaut. Utilise le fichier gurps.sqlite près de l'exécutable.
	 *
	 * @throws ClassNotFoundException
	 */
	public GurpsBase() throws ClassNotFoundException, SQLException
	{
		this("gurps.sqlite");
	}

	/**
	 * Constructeur. Utilise le nom de fichier donné en paramètre pour se connecter à la base.
	 * @param fileName Nom de fichier.
	 * @throws ClassNotFoundException
//	 * @see GrupsBase::GurpsBase()
	 */
	public GurpsBase(String fileName) throws ClassNotFoundException, SQLException
	{
		// load the sqlite-JDBC driver using the current class loader
		Class.forName("org.sqlite.JDBC");

		_connection = null;
		_connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
		_statement = _connection.createStatement();
		_statement.setQueryTimeout(30);  // set timeout to 30 sec.

		initializeDatabase();
	}

	/**
	 * Crée les tables nécessaires.
	 * @throws SQLException
	 */
	private void initializeDatabase() throws SQLException
	{
		_statement.execute("PRAGMA foreign_keys = ON");

		// Tables "enumeration"
		initAttributeType();
		initArmorType();

		initCharacter();
		initWeapon();
		initBodyPart();
		initSkill();

		// Tables de liaison
		initCharacterWeapon();
		initCharacterBodyPart();
		initCharacterSkill();
		initCharacterAttribute();
		initSkillAttributeModifier();
	}

	private void initCharacter() throws SQLException
	{
		System.out.println("character");
		_statement.executeUpdate("DROP TABLE IF EXISTS character");
		_statement.execute("CREATE TABLE IF NOT EXISTS character(" +
				"id INTEGER PRIMARY KEY, " +
				"name TEXT UNIQUE, " +
				"speed INTEGER" +
				")"
		);
	}

	private void initWeapon() throws SQLException
	{
		System.out.println("weapon");
		_statement.executeUpdate("DROP TABLE IF EXISTS weapon");
		_statement.execute("CREATE TABLE IF NOT EXISTS weapon(" +
				"id INTEGER PRIMARY KEY, " +
				"name TEXT UNIQUE, " +
				"attack INTEGER " +
				")"
		);
	}

	private void initArmorType() throws SQLException
	{
		System.out.println("armorType");
		_statement.executeUpdate("DROP TABLE IF EXISTS armorType");
		_statement.execute("CREATE TABLE IF NOT EXISTS armorType(" +
				"type TEXT PRIMARY KEY" +
				")"
		);
		_statement.executeUpdate("INSERT INTO armorType VALUES('SKIN')");
		_statement.executeUpdate("INSERT INTO armorType VALUES('RIGID')");
		_statement.executeUpdate("INSERT INTO armorType VALUES('FLEXIBLE')");
		_statement.executeUpdate("INSERT INTO armorType VALUES('TOUGH_SKIN')");
	}

	private void initBodyPart() throws SQLException
	{
		System.out.println("bodyPart");
		_statement.executeUpdate("DROP TABLE IF EXISTS bodyPart");
		_statement.execute("CREATE TABLE IF NOT EXISTS bodyPart(" +
				"id INTEGER PRIMARY KEY, " +
				"name TEXT, " +
				"damageFactor INTEGER, " +
				"hitChancesFactor INTEGER, " +
				"armorValue INTEGER, " +
				"armorType TEXT, " +
				"FOREIGN KEY (armorType) REFERENCES armorType(type)" +
				")"
		);
	}

	private void initSkill() throws SQLException
	{
		System.out.println("skill");
		_statement.executeUpdate("DROP TABLE IF EXISTS skill");
		_statement.execute("CREATE TABLE IF NOT EXISTS skill(" +
				"id INTEGER PRIMARY KEY, " +
				"name TEXT UNIQUE" +
				")"
		);
	}

	private void initAttributeType() throws SQLException
	{
		System.out.println("attributeType");
		_statement.executeUpdate("DROP TABLE IF EXISTS attributeType");
		_statement.execute("CREATE TABLE IF NOT EXISTS attributeType(" +
				"type TEXT PRIMARY KEY" +
				")"
		);
		_statement.executeUpdate("INSERT INTO attributeType VALUES ('STR')");
		_statement.executeUpdate("INSERT INTO attributeType VALUES ('DEX')");
		_statement.executeUpdate("INSERT INTO attributeType VALUES ('CON')");
		_statement.executeUpdate("INSERT INTO attributeType VALUES ('INT')");
		_statement.executeUpdate("INSERT INTO attributeType VALUES ('SAG')");
		_statement.executeUpdate("INSERT INTO attributeType VALUES ('CHA')");
	}

	private void initCharacterWeapon() throws SQLException
	{
		System.out.println("character_weapon");
		_statement.executeUpdate("DROP TABLE IF EXISTS character_weapon");
		_statement.execute(
				"CREATE TABLE IF NOT EXISTS character_weapon(" +
						"character_id INTEGER, " +
						"weapon_id INTEGER, " +
						"FOREIGN KEY (character_id) REFERENCES character(id), " +
						"FOREIGN KEY (weapon_id) REFERENCES weapon(id)" +
						")"
		);
//		_statement.execute("CREATE INDEX character_id ON character_weapon(character_id)");
//		_statement.execute("CREATE INDEX weapon_id ON character_weapon(weapon_id)");
	}

	private void initCharacterBodyPart() throws SQLException
	{
		System.out.println("character_bodyPart");
		_statement.executeUpdate("DROP TABLE IF EXISTS character_bodyPart");
		_statement.execute("CREATE TABLE IF NOT EXISTS character_bodyPart(" +
				"character_id INTEGER, " +
				"bodyPart_id INTEGER, " +
				"FOREIGN KEY (character_id) REFERENCES character(id), " +
				"FOREIGN KEY (bodyPart_id) REFERENCES bodyPart(id)" +
				")"
		);
//		_statement.execute("CREATE INDEX character_id ON character_bodyPart(character_id)");
//		_statement.execute("CREATE INDEX bodyPart_id ON character_bodyPart(bodyPart_id)");
	}

	private void initCharacterAttribute() throws SQLException
	{
		System.out.println("character_attribute");
		_statement.executeUpdate("DROP TABLE IF EXISTS character_attribute");
		_statement.execute("CREATE TABLE IF NOT EXISTS character_attribute(" +
				"character_id INTEGER, " +
				"attribute TEXT, " +
				"value INTEGER DEFAULT(0) NOT NULL, " +
				"FOREIGN KEY (character_id) REFERENCES character(id), " +
				"FOREIGN KEY (attribute) REFERENCES attributeType(type) " +
				")"
		);
//		_statement.execute("CREATE INDEX character_id ON character_attribute(character_id)");
//		_statement.execute("CREATE INDEX attribute ON character_attribute(attribute)");
	}

	private void initCharacterSkill() throws SQLException
		{
			System.out.println("character_skill");
			_statement.executeUpdate("DROP TABLE IF EXISTS character_skill");
			_statement.execute("CREATE TABLE IF NOT EXISTS character_skill(" +
					"character_id INTEGER, " +
					"skill_id INTEGER, " +
					"value INTEGER DEFAULT(0) NOT NULL, " +
					"FOREIGN KEY (character_id) REFERENCES character(id), " +
					"FOREIGN KEY (skill_id) REFERENCES skill(id)" +
					")"
		);
//		_statement.execute("CREATE INDEX character_id ON character_skill(character_id)");
//		_statement.execute("CREATE INDEX skill_id ON character_skill(skill_id)");
	}

	private void initSkillAttributeModifier() throws SQLException
	{
		System.out.println("skill_attribute_modifier");
		_statement.executeUpdate("DROP TABLE IF EXISTS skill_attribute_modifier");
		_statement.execute("CREATE TABLE IF NOT EXISTS skill_attribute_modifier(" +
				"skill_id INTEGER PRIMARY KEY, " +
				"attribute TEXT, " +
				"modifierFactor REAL, " +
				"FOREIGN KEY (skill_id) REFERENCES skill(id), " +
				"FOREIGN KEY (attribute) REFERENCES attributeType(type) " +
				")"
		);
		//		_statement.execute("CREATE INDEX skill_id_id ON skill_attribute_modifier(skill_id)");
		//		_statement.execute("CREATE INDEX attribute ON character_bodyPart(attribute)");
	}

	public int createCharacter(GurpsCharacter character)
	{
		return 0;
	}

	public GurpsCharacter getCharacter(String name)
	{
		return new GurpsCharacter("toto", 42);
	}
	//			CREATE INDEX trackindex ON track(trackartist);

	//			System.out.println("11");
/*			_statement.executeUpdate("insert into characters(name) values('leo')");
			_statement.executeUpdate("insert into characters(name) values('toto')");
			_statement.executeUpdate("insert into characters(name) values('pouet')");
			_statement.executeUpdate("insert into weapons(name) values('chaussette')");
			_statement.executeUpdate("insert into weapons(name) values('cuillère')");

			ResultSet leo = _statement.executeQuery("select id from characters where name = 'leo'");
			while(leo.next())
			{
				System.out.println("Leo id = " + leo.getString("id"));
			}

			ResultSet ids = _statement.executeQuery("select id from characters");
			while(ids.next())
			{
				System.out.println("ids id = " + leo.getInt("id"));
			}

			ResultSet all = _statement.executeQuery("select * from characters");
			while(all.next())
			{
				System.out.println("all id = " + leo.getInt("id"));
			}

			System.out.println("22");*/
	//			_statement.executeUpdate("insert into characters_weapons(character_id, weapon_id) values(leo,saucisse)");
	//			_statement.executeUpdate("insert into characters_weapons(character_id, weapon_id) values(leo,cuillère)");
	//			_statement.executeUpdate("insert into characters_weapons(character_id, weapon_id) values(toto,saucisse)");
	//			_statement.executeUpdate("insert into characters_weapons(character_id, weapon_id) values(pouet,cuillère)");
}
