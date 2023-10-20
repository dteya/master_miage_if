package master_miage_if.project.domain;

public class Email {

    public String lastEmail;
    public String newEmail;

    public Email(String lastEmail, String newEmail) {
        this.lastEmail = lastEmail;
        this.newEmail = newEmail;
    }

    public String getLastEmail() {
        return lastEmail;
    }

    public void setLastEmail(String lastEmail) {
        this.lastEmail = lastEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }
}
