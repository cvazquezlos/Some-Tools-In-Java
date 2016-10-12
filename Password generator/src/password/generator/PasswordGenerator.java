package password.generator;

/**
 *
 * @author c.vazquezlos
 */
public class PasswordGenerator {

    Index index;
    int numChar;
    String secLevel;
    int gradeLevel;
    boolean specialChar;
    boolean numbers;
    boolean wantRepeated;
    String regionChar;

    public PasswordGenerator(Index index, String[] result) {
        this.index = index;
        updateValues(result);
        setSecLevelsInt(secLevel);
        
    }

    private void updateValues(String[] result) {
        numChar = Integer.valueOf(result[0]);
        secLevel = result[1];
        specialChar = uploadBooleanValue(result[2]);
        numbers = uploadBooleanValue(result[3]);
        wantRepeated = uploadBooleanValue(result[4]);
        regionChar = result[5];
    }
    
    private void setSecLevelsInt(String secLevel){
        switch (secLevel){
            case "Low":
                gradeLevel = 1;
            case "Medium":
                gradeLevel = 2;
            case "Advanced":
                gradeLevel = 3;
            case "Super":
                gradeLevel = 4;
        }
    }

    private Boolean uploadBooleanValue(String result) {
        if (result.equals("true")) {
            return true;
        } else {
            return false;
        }
    }

    public String createPassword() {
        String password = "";
        String[] elements;
        char[] valuesAvaible = {'!', '"', '#', '$', '%', '&', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', ']', '^', '{', '}', '~'};
        for (int i = 0; i < numChar; i++) {
            elements = new String[10];
            if (numbers && specialChar) {
                for (int j = 0; j < elements.length; j++) {
                    if (j >= 0 && j < 4) {
                        elements[j] = Integer.toString(generateRandomInt(0, 9));
                    } else if (j >= 4 && j < 6) {
                        elements[j] = "" + generateRandomChar(valuesAvaible) + "";
                    } else if (j >= 6) {
                        elements[j] = String.valueOf(generateRandomLetter());
                    }
                }
            } else if (numbers || specialChar) {
                if (numbers) {
                    for (int j = 0; j < elements.length; j++) {
                        if (j >= 0 && j < 5) {
                            elements[j] = Integer.toString(generateRandomInt(0, 9));
                        } else if (j >= 5) {
                            elements[j] = String.valueOf(generateRandomLetter());
                        }
                    }
                } else if (specialChar) {
                    for (int j = 0; j < elements.length; j++) {
                        if (j >= 0 && j < 4) {
                            elements[j] = "" + generateRandomChar(valuesAvaible) + "";
                        } else if (j >= 4) {
                            elements[j] = String.valueOf(generateRandomLetter());
                        }
                    }
                }
            } else {
                for (int j = 0; j < elements.length; j++) {
                    elements[j] = String.valueOf(generateRandomLetter());
                }
            }
            String elementChosen = elements[generateRandomInt(0, 9)];
            if (isRepeated(password, elementChosen)) {
                if (wantRepeated) {
                    password += elementChosen;
                } else {
                    while (isRepeated(password, elementChosen)) {
                        elementChosen = elements[generateRandomInt(0, 9)];
                    }
                    password += elementChosen;
                }
            } else {
                password += elementChosen;
            }
        }
        return password;
    }

    private char generateRandomLetter() {
        int rnd = generateRandomInt(0, 52);
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);
    }

    private char generateRandomChar(char[] valuesAvaible) {
        return valuesAvaible[generateRandomInt(0, 26)];
    }

    private int generateRandomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1 + min));
    }

    private boolean isRepeated(String password, String element) {
        return password.contains(element);
    }

}
