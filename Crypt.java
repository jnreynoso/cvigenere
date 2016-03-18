import java.io.Console;

public class Crypt implements Vigenere {

    String keyword;
    String message;
    String alphabet;

    public void fill(String keyword, String text) {
        int kl = keyword.length();
        int tl = text.length();
        int v = tl / kl;
        String finalKey = keyword;
        int outside = 0;

        for(int i=0; i<v; i++){
            finalKey += finalKey;
        }

        outside = tl - finalKey.length();

        if(outside!=0 || outside>0){

            for(int i=0;i<outside;i++){
                finalKey += finalKey.toCharArray()[i];
            }
        }

        this.keyword=finalKey;

    }
    public String cipher(String plaintext) {
        //E(Xi) = (Xi + Ki) mod L
        String cryptogram = "";
        int L = alphabet.length();
        int Xi = 0;
        int Ki = 0;

        fill(keyword, plaintext);

        for(int i=0;i < plaintext.length(); i++){

            Xi = alphabet.indexOf(plaintext.toCharArray()[i]);
            Ki = alphabet.indexOf(keyword.toCharArray()[i]);

            cryptogram += alphabet.toCharArray()[(Xi+Ki)%L];

        }

        return cryptogram;
    }
    public String decipher(String ciphertext) {
        //D(Ci) = (Ci - Ki) mod L
        String decrypted = "";
        int L = alphabet.length();
        int Ci = 0;
        int Ki = 0;

        fill(keyword, ciphertext);

        for(int i=0;i < ciphertext.length(); i++){

            Ci = alphabet.indexOf(ciphertext.toCharArray()[i]);
            Ki = alphabet.indexOf(keyword.toCharArray()[i]);

            decrypted += alphabet.toCharArray()[myMod((Ci-Ki), L)];

        }

        return decrypted;

    }

    // Hacked for Mod negative
    int myMod(int x, int modulo) {
        return ((x % modulo) + modulo)  % modulo;
    }

    public static void main(String[] args) {

        Crypt c = new Crypt();

        Console cnsl = null;
        int opcion = 0;

        try{

            System.out.println("1. Cifrar");
            System.out.println("2. Decifrar");

            cnsl = System.console();

            if (cnsl != null) {
                opcion = Integer.parseInt(cnsl.readLine("Opcion: "));
                if(opcion== 1){
                    c.alphabet=cnsl.readLine("Ingrese Alfabeto: ").replaceAll("\\s","");
                    c.message=cnsl.readLine("Ingrese Mensaje: ").replaceAll("\\s","");
                    c.keyword=cnsl.readLine("Ingrese Clave: ").replaceAll("\\s","");
                    System.out.println(">> " + c.cipher(c.message));
                }else {
                    c.alphabet=cnsl.readLine("Ingrese Alfabeto: ").replaceAll("\\s","");
                    c.message=cnsl.readLine("Ingrese Texto Cifrado: ").replaceAll("\\s","");
                    c.keyword=cnsl.readLine("Ingrese Clave: ").replaceAll("\\s","");
                    System.out.println(">> " + c.decipher(c.message));
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
