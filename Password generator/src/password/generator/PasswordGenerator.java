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
    double index1, index2, index3;

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
                setAllIndex(1);
                for (int j = 0; j < elements.length; j++) {
                    if (j >= 0 && j < ((int) (index1*elements.length))) {
                        elements[j] = Integer.toString(generateRandomInt(0, 9));
                    } else if (j >= ((int) (index1*elements.length)) && j < ((int) (index2*elements.length))) {
                        elements[j] = String.valueOf(generateRandomLetter());
                    } else if (j >= ((int) (index2*elements.length))) {
                        elements[j] = "" + generateRandomChar(valuesAvaible) + "";
                    }
                }
            } else if (numbers || specialChar) {
                if (numbers) {
                    setAllIndex(2);
                    for (int j = 0; j < elements.length; j++) {
                        if (j >= 0 && j < 5) {
                            elements[j] = Integer.toString(generateRandomInt(0, 9));
                        } else if (j >= 5) {
                            elements[j] = String.valueOf(generateRandomLetter());
                        }
                    }
                } else if (specialChar) {
                    setAllIndex(3);
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

    private void setAllIndex(int grade){
        switch(grade){
            case 1:
                switch(gradeLevel){
                    case 1:
                        index1 = 0.2;
                        index2 = 0.6;
                        index3 = 0.2;
                        break;
                    case 2:
                        index1 = 0.3;
                        index2 = 0.4;
                        index3 = 0.3;
                        break;
                    case 3:
                        index1 = 0.4;
                        index2 = 0.2;
                        index3 = 0.4;
                        break;
                    case 4:
                        index1 = 0.45;
                        index2 = 0.10;
                        index3 = 0.45;
                        break;
                }
                break;
        }
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
