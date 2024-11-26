/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		String newString1 = preProcess(str1);
		String newString2 = preProcess(str2);
		boolean isAnagram = true;
		String finalString1 = "";
		String finalString2 = "";
		
		//remove spaces
		for (int i = 0 ; i < newString1.length() ; i++){
			if (newString1.charAt(i) != 32){
				finalString1 = finalString1 + newString1.charAt(i);
			}
		}
		for (int i = 0 ; i < newString2.length() ; i++){
			if (newString2.charAt(i) != 32){
				finalString2 = finalString2 + newString2.charAt(i);
			}

		}

		if (finalString1.length() != finalString2.length()){
			return false;
		}

		for (int i = 0 ; i < finalString1.length() ; i++){
			char current = finalString1.charAt(i);
			int index = finalString2.indexOf(current);

			if (index != -1){
				finalString2 = finalString2.substring(0, index) + finalString2.substring(index + 1);
			} else {
				return false;
			}
		}

		if (finalString2.length() == 0){
			isAnagram = true;
		}
		return isAnagram;
	}
	   
	// Returns a preprocessed version of the given string: all the letter characters are converted
	// to lower-case, and all the other characters are deleted, except for spaces, which are left
	// as is. For example, the string "What? No way!" becomes "whatnoway"
	public static String preProcess(String str) {
		String newString = "";
		for (int i = 0 ; i < str.length() ; i++){
			if (str.charAt(i) >= 65 && str.charAt(i) <= 90){
				newString = newString + (char)(str.charAt(i) + 32);
			} else if ((str.charAt(i) >= 97 && str.charAt(i) <= 122) || str.charAt(i) == 32){
				newString = newString + str.charAt(i);
			}
		}
		return newString;
	} 
	   
	// Returns a random anagram of the given string. The random anagram consists of the same
	// characters as the given string, re-arranged in a random order. 
	public static String randomAnagram(String str) {
		preProcess(str);
		String randomString = "";
		while (str.length() > 0){
			int index = (int) (Math.random() * (str.length()));
			char selectedChar = str.charAt(index);
			str = str.substring(0, index) + str.substring(index + 1);
			randomString = randomString + selectedChar;
		}
		return randomString;
	}
}
