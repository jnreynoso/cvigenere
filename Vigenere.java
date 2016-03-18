interface Vigenere {
    String keyword = null;
    String alphabet = null;

    void fill(String keyword,String text);
    String cipher(String plaintext);
    String decipher(String ciphertext);
}
