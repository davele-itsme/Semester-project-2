package model;

/**
 * <h1>Validation Class used for validating input with sets of regex's</h1>
 * This Class contains methods to check whether a string contains only Letters,
 * only Numbers or a combination of the two.
 *
 * @author Kenneth Jensen
 * @author Floring Bordei
 * @author Jaime Lopez
 * @author Dave Joe Lê
 */

public class Validation {

    // TODO: Remove Constructor and make methods static?
    public Validation()
    {

    }

    /**
     * Checks whether all Characters of a String are Letters.
     * @param name The {@link String} to test.
     * @return Whether the {@link String} only contains Letters or not.
     */
    public boolean onlyLetters(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Checks whether all Characters of a String are Numbers.
     * @param name The {@link String} to test.
     * @return Whether the {@link String} only contains Numbers or not.
     */
    public boolean onlyNumbers(String name)
    {
        if(name.matches("[0-9]+") && Integer.parseInt(name) >= 0)
        {
            return true;
        }
        return false;
    }

    /**
     * Checks whether All Characters of a String are either a Letter or a Number.
     * @param name The {@link String} to test.
     * @return Whether the {@link String} only contains Letters and Numbers.
     */
    public boolean onlyLettersAndNumbers(String name)
    {
        char[] chars = name.toCharArray();

        // For every character in the String, this for Loop checks whether or not it is a Letter or Number.

        for(char c : chars)
        {
            String charString = String.valueOf(c);

            // First it check whether it is a Letter. If it is, the loop continues.
            // If it is not, it will check whether it is a Number.

            if(!(Character.isLetter(c)))
            {
                // If it is not a number, it will return false, as a Character in the String is neither a number
                // or a Letter.

                String c1 = c + "";

                if(!(c1.matches("[0-9]+")) && Integer.parseInt(charString) >= 0 )
                {
                    return false;
                }
            }
        }

        // If it comes through the Loop without returning false, the method will return true,
        // as all Characters are now either Letters and Numbers

        return true;
    }

}
