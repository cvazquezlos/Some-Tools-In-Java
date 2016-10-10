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
    
    private void updateValues(String[] result){
        numChar = Integer.valueOf(result[0]);
        secLevel = result[1];
        specialChar = uploadBooleanValue(result[2]);
        numbers = uploadBooleanValue(result[3]);
        repeated = uploadBooleanValue(result[4]);
        regionChar = result[5];
    }
    
    private Boolean uploadBooleanValue(String result){
        if (result=="true")
            return true;
        else
            return false;
    }

    public String createPassword(){
        String pass="";
        for (int i=0; i<numChar; i++){
            String[] elements = new String[10];
            for (int j=0; j<elements.length; j++){
                
            }
            // Genera random A-Z, a-z
            int rnd = (int) (Math.random() * 52);
            char base = (rnd < 26) ? 'A' : 'a';
            pass+=(char) (base + rnd % 26);
        }
        return pass;
    }
    
}
