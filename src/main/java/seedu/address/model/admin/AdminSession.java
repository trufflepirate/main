package seedu.address.model.admin;


public class AdminSession {

    private Admin loggedInAdmin;
    public AdminSession() {
        this.loggedInAdmin = null;
    }

    public boolean isAdminLoggedIn() {
        return !(loggedInAdmin == null);
    }
    
    public void setLogin(Admin admin) {
        this.loggedInAdmin = admin;
    }

    public void clearLogin() {
        this.loggedInAdmin = null;
    }

    public Admin getLoggedInAdmin(){
        return this.loggedInAdmin;
    }


}
