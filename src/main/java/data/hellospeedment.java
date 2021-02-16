package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.company.userdb.UserdbApplication;
import com.company.userdb.UserdbApplicationBuilder;
import com.company.userdb.userdb.userdb.user.User;
import com.company.userdb.userdb.userdb.user.UserManager;
import com.speedment.runtime.connector.mysql.MySqlBundle;

// Example SQL databases: https://dev.mysql.com/doc/index-other.html

// Getting started with Speedment :: https://speedment.github.io/speedment-doc/tutorials.html

public class hellospeedment {

	// https://speedment.github.io/speedment-doc/tutorials.html#tutorial-1---hello-speedment

	UserdbApplication app = new UserdbApplicationBuilder().withBundle(MySqlBundle.class).withPassword("admin").build();
	UserManager users = app.getOrThrow(UserManager.class);

//	@Test
	public void dbReadTest() {

		// users.stream().limit(2).forEach(System.out::println);

		users.stream().filter(User.LAST_NAME.startsWith("A")).forEach(System.out::println);

	}

	// https://speedment.github.io/speedment-doc/tutorials.html#tutorial-2---a-first-stream-from-speedment

//	@Test
	public void fetchEntryFilter() {

		users.stream().filter(User.EMAIL.endsWith("@gmail.com")).forEach(System.out::println);

	}

//	@Test
	public void fetchEntryEmail() { // example to manipulate stream
		users.stream().filter(User.EMAIL.endsWith("@gmail.com")).forEach(x -> {
			String l = x.getEmail().toUpperCase().replace("@GMAIL.COM", "");
			System.out.println("Modified EMAIL" + l);
		});

	}

//		CRUD Operations : https://speedment.github.io/speedment-doc/crud.html

//		The term CRUD is a short for Create, Read, Update and Delete. Speedment supports all these operations via table Manager objects and more according to the following table:
//		Operation 	Direct Method 	Functional Reference 	Effect
//		Create 	persist(entity) 	persister() 	Creates a new row in the database table with data from the given entity
//		Read 						stream() 	  	Returns a Stream over all the rows in the database table
//		Update 	update(entity) 	updater() 	Updates an existing row in the database from the given entity based on primary key(s)
//		Delete 	remove(entity) 	remover() 	Removes the row in the database that has the same primary key(s) as the given entity
//		Merge 	merge(entity) 	merger() 	If the row does not exists; Creates the row, otherwise Updates the row in the database that has the same primary key(s) as the given entity

	@Test
	public void UpdatePasswordsRandom() { // example to manipulate stream

		// new stream generated and set
		// users.stream().forEach(x -> x.setPassword(get_random_string()));
		// This maps single value to all the stream items -> not for variable inputs
		// users.stream().map(User.PASSWORD.setTo(get_random_string())).forEach(users.updater());

		/* Some Examples */

		// For updating each entry with diff items
		users.stream().map(x -> x.setPassword(get_random_string())).forEach(users.updater());
		users.stream().forEach(System.out::println);

		// Update only users with 'A' Start
		users.stream().filter(User.FIRST_NAME.startsWith("A")).map(x -> x.setPassword(get_random_string()))
				.forEach(users.updater());

	}

	/*
	 * Utility functions
	 * 
	 */

//	@Test
	public void random_string() {
		int leftLimit = 64; // letter 'a'
		int rightLimit = 125; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		System.out.println(generatedString);
		// return generatedString;
		System.out.println(random_string_List(10, 8));
	}

	public String get_random_string() {
		int leftLimit = 64; // letter 'a'
		int rightLimit = 125; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

//		System.out.println(generatedString);
		return generatedString;

	}

	public List<String> random_string_List(int listSize, int stringSize) {
		int leftLimit = 33; // letter 'a'
		int rightLimit = 125; // letter 'z'
		int targetStringLength = stringSize;

		List<String> randStringList = new ArrayList<String>();
		for (int i = 0; i < listSize; i++) {
			Random random = new Random();
			String s = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
					.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
			randStringList.add(s);

		}
		return randStringList;
	}

}
