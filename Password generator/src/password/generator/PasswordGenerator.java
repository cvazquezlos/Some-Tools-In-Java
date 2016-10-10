package password.generator;

/**
 *
 * @author c.vazquezlos
 */
public class PasswordGenerator {

    Index index;
    int numChar;
    String secLevel;
    boolean specialChar;
    boolean numbers;
    boolean repeated;
    String regionChar;

    public PasswordGenerator(Index index, String[] result) {
        this.index = index;
        updateValues(result);
    }

    private void updateValues(String[] result) {
        numChar = Integer.valueOf(result[0]);
        secLevel = result[1];
        specialChar = uploadBooleanValue(result[2]);
        numbers = uploadBooleanValue(result[3]);
        repeated = uploadBooleanValue(result[4]);
        regionChar = result[5];
    }

    private Boolean uploadBooleanValue(String result) {
        if (result == "true") {
            return true;
        } else {
            return false;
        }
    }

    public String createPassword() {
        String pass = "";
        String[] elements;
        for (int i = 0; i < numChar; i++) {
            elements = new String[10];
            if (numbers && specialChar) {
                for (int j = 0; j < elements.length; j++) {
                    if (j >= 0 && j < 4) {
                        elements[j] = Integer.toString((int) (Math.random() * 9));
                    } else if (j >= 4 && j < 6) {
                        char[] valuesAvaible = {'!', '"', '#', '$', '%', '&', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', ']', '^', '{', '}', '~'};
                        elements[j] = "" + valuesAvaible[(int) (Math.random() * 27)] + "";
                    } else if (j >= 6) {
                        int rnd = (int) (Math.random() * 52);
                        char base = (rnd < 26) ? 'A' : 'a';
                        elements[j] = String.valueOf((char) (base + rnd % 26));
                    }
                }
            } else if (numbers || specialChar) {
                if (numbers) {
                    for (int j = 0; j < elements.length; j++) {
                        if (j >= 0 && j < 5) {
                            elements[j] = Integer.toString((int) (Math.random() * 9));
                        } else if (j >= 5) {
                            int rnd = (int) (Math.random() * 52);
                            char base = (rnd < 26) ? 'A' : 'a';
                            elements[j] = String.valueOf((char) (base + rnd % 26));
                        }
                    }
                } else if (specialChar) {
                    for (int j = 0; j < elements.length; j++) {
                        if (j >= 0 && j < 4) {
                            char[] valuesAvaible = {'!', '"', '#', '$', '%', '&', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', ']', '^', '{', '}', '~'};
                            elements[j] = "" + valuesAvaible[(int) (Math.random() * 27)] + "";
                        } else if (j >= 4) {
                            int rnd = (int) (Math.random() * 52);
                            char base = (rnd < 26) ? 'A' : 'a';
                            elements[j] = String.valueOf((char) (base + rnd % 26));
                        }
                    }
                }
            } else {
                for (int j = 0; j < elements.length; j++) {
                    int rnd = (int) (Math.random() * 52);
                    char base = (rnd < 26) ? 'A' : 'a';
                    elements[j] = String.valueOf((char) (base + rnd % 26));
                }
            }
            pass += elements[(int) (Math.random() * 9)];
        }
        return pass;
    }

}
