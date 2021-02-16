package data;

import java.util.Optional;

import org.junit.Test;

import com.company.userdb.UserdbApplication;
import com.company.userdb.UserdbApplicationBuilder;
import com.company.userdb.userdb.userdb.user.User;
import com.company.userdb.userdb.userdb.user.UserImpl;
import com.company.userdb.userdb.userdb.user.UserManager;
import com.speedment.runtime.connector.mysql.MySqlBundle;

public class CRUDSpeedment {

	UserdbApplication app = new UserdbApplicationBuilder().withBundle(MySqlBundle.class).withPassword("admin").build();
	UserManager users = app.getOrThrow(UserManager.class);

//		The term CRUD is a short for Create, Read, Update and Delete. 
//		Speedment supports all these operations via table Manager objects and more according to the following table:

//		Operation 	Direct Method 		Functional 	Reference 	Effect
//		Create 		persist(entity) 	persister() Creates a new row in the database table with data from the given entity
//		Read 							stream()	Returns a 	Stream over all the rows in the database table
//		Update 		update(entity) 		updater() 	Updates an existing row in the database from the given entity based on primary key(s)
//		Delete 		remove(entity) 		remover() 	Removes the row in the database that has the same primary key(s) as the given entity
//		Merge 		merge(entity) 		merger() 	If the row does not exists; Creates the row, otherwise Updates the row in the database that has the same primary key(s) as the given entity

	@Test
	public void createRows() {

//		users.persist(new UserImpl().setUsername("cmorris").setFirstName("Morris").setLastName("Chang")
//				.setEmail("morris.chang@gmail.com").setPassword(utilitiesRandom.get_random_string()));

		users.persist(new UserImpl().setUsername("arit").setFirstName("Rita").setLastName("Lee")
				.setEmail("leerita@gmail.com").setPassword(utilitiesRandom.get_random_string()));

	}

//	@Test
	public void readRows() { // example to manipulate stream
		users.stream().filter(User.EMAIL.endsWith("@gmail.com")).forEach(x -> {
			String l = x.getEmail().toUpperCase().replace("@GMAIL.COM", "");
			System.out.println("Modified EMAIL" + l);
		});
	}

//	@Test
	public void updateNamesLower() { // example to manipulate stream

		// For updating each entry with diff items
//		users.stream().map(x -> x.setFirstName(x.FIRST_NAME.toString().toUpperCase())).forEach(System.out::println);
//		users.stream().map(x -> x.setFirstName(x.getFirstName().toString().toUpperCase())).forEach(users.updater());
//		users.stream().forEach(System.out::println);

		// Update only users with 'A' Start
		users.stream().map(x -> x.setFirstName(x.getUsername().toUpperCase())).forEach(users.updater());

	}

	@Test
	public void updateNameToNamesUpper() { // example to manipulate stream

// 		For updating each entry with diff items
//		users.stream().map(x -> x.setFirstName(x.FIRST_NAME.toString().toUpperCase())).forEach(System.out::println);
//		users.stream().map(x -> x.setFirstName(x.getFirstName().toString().toUpperCase())).forEach(users.updater());
//		users.stream().forEach(System.out::println);

		// Update Optional -> Nullable values

		users.stream().map(x -> x.setLastName(OptionalStringConverter(x.getLastName()).toUpperCase()))
				.map(x -> x.setFirstName(OptionalStringConverter(x.getFirstName()).toUpperCase()))
				.forEach(users.updater());

//		users.stream().map(x -> x.setLastName(OptionalStringConverter(x.getLastName()).toLowerCase()))
//		.map(x -> x.setFirstName(OptionalStringConverter(x.getFirstName()).toLowerCase()))
//		.forEach(users.updater());

	}

	public String OptionalStringConverter(Optional<String> s) {
		if (s.isPresent())
			return s.get();
		else
			return "";
	}

//		Speedment CRUD Operations : https://speedment.github.io/speedment-doc/crud.html

//	@Test
	public void UpdatePasswordsRandom() { // example to manipulate stream

		// For updating each entry with diff items
		users.stream().map(x -> x.setPassword(utilitiesRandom.get_random_string())).forEach(users.updater());
		users.stream().forEach(System.out::println);

		// Update only users with 'A' Start
		users.stream().filter(User.FIRST_NAME.startsWith("A"))
				.map(x -> x.setPassword(utilitiesRandom.get_random_string())).forEach(users.updater());

	}

}
