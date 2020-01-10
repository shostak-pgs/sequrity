package app.role;

public enum Role {
    SURFER("surfer"),
    USER("user"),
    ADMIN("admin");

    private final String role;

    Role(String role){
        this.role = role;
    }

    /**
     * Returns Role enum from string representation
     * @param str string for Role search
     * @return the Role
     */
    public static Role toRole(String str){
        Role role;
        switch(str){
            case "admin":
                role = ADMIN;
                break;
            case "user":
                role = USER;
            break;
            default:
                role = SURFER;
            break;
        }
        return role;
    }
}

