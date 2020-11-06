package controller;

public enum WarningMessage {

    USER_FIRSTNAME("Geen geldige voornaam opgegeven!\n"),
    USER_LASTNAME("Geen geldige achternaam opgegeven!\n"),
    USER_USERNAME("Geen geldige gebruikersnaam opgegeven!\n"),
    USER_PASSWORD("Geen geldig wachtwoord opgegeven!");

    private String message;

    WarningMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
