package btssio.appliresto.modele;

/**
 * Created by mick.souloumiac1 on 17/03/2022.
 */

public class Critique {

    private int idR;
    private String mailU;
    private int note;
    private String commentaire;

    public Critique(int idR, String mailU, int note, String commentaire) {
        this.idR = idR;
        this.mailU = mailU;
        this.note = note;
        this.commentaire = commentaire;
    }

    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public String getMailU() {
        return mailU;
    }

    public void setMailU(String mailU) {
        this.mailU = mailU;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Critique{" +
                "idR=" + idR +
                ", mailU='" + mailU + '\'' +
                ", note=" + note +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
