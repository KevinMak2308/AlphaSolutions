package gruppe5.alphasolutions.models;

public class Roles {
    private int roleID;
    private String rolename;
    private String userEmail;


    public Roles(int roleID, String rolename, String userEmail) {
        this.roleID = roleID;
        this.rolename = rolename;
        this.userEmail = userEmail;
    }


    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleID=" + roleID +
                ", rolename='" + rolename + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
