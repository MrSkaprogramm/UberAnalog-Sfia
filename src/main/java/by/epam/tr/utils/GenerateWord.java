package by.epam.tr.utils;

/**
 * Random word generation class
 */
public class GenerateWord {

  /**
   * Generates a random word
   * 
   * @return Returns this word as a string
   */
  public String generateRandomWord() {
    String letters = "abcdefghijklmnopqrstuvwxyz";

    char[] randomWord = new char[(int) (Math.random() * 10 + 1)];
    for (int i = 0; i < randomWord.length; i++) {
      randomWord[i] = letters.charAt((int) (Math.random() * letters.length()));
    }
    randomWord[0] = Character.toUpperCase(randomWord[0]);
    return new String(randomWord);
  }
}
