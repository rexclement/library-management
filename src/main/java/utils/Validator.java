package utils;

public class Validator {

    public static boolean isValidISBN(String isbn){
        return isbn != null && isbn.matches("\\d{5,13}");
    }

    public static boolean isValidEmail(String email){
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
