using System;
using System.Text;

public class Crypt
{
    String keyword;
    String message;
    String alphabet;

    public void fill(String keyword, String text) {
        int kl = keyword.Length;
        int tl = text.Length;
        int v = tl / kl;
        String finalKey = keyword;
        int outside = 0;

        for(int i=0; i<v; i++){
            finalKey += finalKey;
        }

        outside = tl - finalKey.Length;

        if(outside!=0 || outside>0){

            for(int i=0;i<outside;i++){
                finalKey += finalKey.ToCharArray()[i];
            }
        }

        this.keyword=finalKey;

    }
    public String cipher(String plaintext) {
        //E(Xi) = (Xi + Ki) mod L
        String cryptogram = "";
        int L = alphabet.Length;
        int Xi = 0;
        int Ki = 0;

        fill(keyword, plaintext);

        for(int i=0;i < plaintext.Length; i++){

            Xi = alphabet.IndexOf(plaintext.ToCharArray()[i]);
            Ki = alphabet.IndexOf(keyword.ToCharArray()[i]);

            cryptogram += alphabet.ToCharArray()[(Xi+Ki)%L];

        }

        return cryptogram;
    }
    public String decipher(String ciphertext) {
        //D(Ci) = (Ci - Ki) mod L
        String decrypted = "";
        int L = alphabet.Length;
        int Ci = 0;
        int Ki = 0;

        fill(keyword, ciphertext);

        for(int i=0;i < ciphertext.Length; i++){

            Ci = alphabet.IndexOf(ciphertext.ToCharArray()[i]);
            Ki = alphabet.IndexOf(keyword.ToCharArray()[i]);

            decrypted += alphabet.ToCharArray()[myMod((Ci-Ki), L)];

        }

        return decrypted;

    }

    // Hacked for Mod negative
    int myMod(int x, int modulo) {
        return ((x % modulo) + modulo)  % modulo;
    }


    public static void Main(String[] args) {

       Crypt c = new Crypt();

       int opcion = 0;

       try{

            System.Console.WriteLine("1. Cifrar");
            System.Console.WriteLine("2. Decifrar");

            Console.Write("Opcion: ");
            opcion = Int32.Parse(Console.ReadLine());
            if(opcion== 1){
                Console.Write("Ingrese Alfabeto: ");
                c.alphabet=Console.ReadLine().Replace("\\s","");
                Console.Write("Ingrese Mensaje: ");
                c.message=Console.ReadLine().Replace("\\s","");
                Console.Write("Ingrese Clave: ");
                c.keyword=Console.ReadLine().Replace("\\s","");
                System.Console.WriteLine(">> " + c.cipher(c.message));
            }else {
                Console.Write("Ingrese Alfabeto: ");
                c.alphabet=Console.ReadLine().Replace("\\s","");
                Console.Write("Ingrese Texto Cifrado: ");
                c.message=Console.ReadLine().Replace("\\s","");
                Console.Write("Ingrese Clave: ");
                c.keyword=Console.ReadLine().Replace("\\s","");
                System.Console.WriteLine(">> " + c.decipher(c.message));
            }

        }catch(Exception ex){
           Console.WriteLine(ex.ToString());
        }

    }
}
