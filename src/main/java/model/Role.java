package model;

public enum Role {
    STUDENT("Student"), DOCENT("Docent"), COORDINATOR("Coördinator"), ADMINISTRATOR(
            "Administrator"), TECHNISCH_BEHEERDER("Technisch beheerder");

    public final String roleName;

    private Role(String roleName) {
        this.roleName = roleName;
    }

}
