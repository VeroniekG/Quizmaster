package model;

public enum Role {
    STUDENT("Student"), DOCENT("Docent"), COORDINATOR("Coördinator"), ADMINISTRATOR(
            "Administrator"), TECHNISCH_BEHEERDER("Technisch beheerder");

    private final String roleName;

    private Role(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return roleName;
    }
}
