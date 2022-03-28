package btssio.appliresto.modele;

/**
 * Created by mick.souloumiac1 on 17/03/2022.
 */

public class User {

    private String mailU;
    private String mdpU;
    private String pseudoU;

    public User(String mailU, String mdpU, String pseudoU) {
        this.mailU = mailU;
        this.mdpU = mdpU;
        this.pseudoU = pseudoU;


    }

    public String getMailU() {
        return mailU;
    }

    public void setMailU(String mailU) {
        this.mailU = mailU;
    }

    public String getMdpU() {
        return mdpU;
    }

    public void setMdpU(String mdpU) {
        this.mdpU = mdpU;
    }

    public String getPseudoU() {
        return pseudoU;
    }

    public void setPseudoU(String pseudoU) {
        this.pseudoU = pseudoU;
    }

    @Override
    public String toString() {
        return "User{" +
                "mailU='" + mailU + '\'' +
                ", mdpU='" + mdpU + '\'' +
                ", pseudoU='" + pseudoU + '\'' +
                '}';
    }


}
