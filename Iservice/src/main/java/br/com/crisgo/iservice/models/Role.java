package br.com.crisgo.iservice.models;
public enum Role {
    ADMIN("ROLE_ADMIN"),
    SELLER("ROLE_SELLER"),
    COMMON_USER("ROLE_COMMON_USER");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
