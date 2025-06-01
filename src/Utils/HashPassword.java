package utils;

public class HashPassword {
    private static final int SHIFT = 10;

    public static String hashPassword(String password) {
        StringBuilder hashedPassword = new StringBuilder();

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            int shifted;

            if (i % 2 == 0) {
                shifted = c + SHIFT;
            } else {
                shifted = c - SHIFT;
            }

            if (shifted > 126)
                shifted = 33 + (shifted - 127);
            if (shifted < 33)
                shifted = 127 - (33 - shifted);

            hashedPassword.append((char) shifted);
        }

        return hashedPassword.toString();
    }
}