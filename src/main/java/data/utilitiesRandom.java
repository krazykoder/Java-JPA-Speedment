package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class utilitiesRandom {
//	@Test
	public static void random_string() {
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

	public static String get_random_string() {
		int leftLimit = 64; // letter 'a'
		int rightLimit = 125; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

//		System.out.println(generatedString);
		return generatedString;

	}

	public static List<String> random_string_List(int listSize, int stringSize) {
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
