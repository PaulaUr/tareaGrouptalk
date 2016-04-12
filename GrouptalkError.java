package eetac.upc.eetac.dsa.grouptalk.entity;

/**
 * Created by pauli on 12/04/2016.
 */
public class GrouptalkError {
    private int status;
    private String reason;
    public GrouptalkError() {
    }
    public GrouptalkError(int status, String reason) {
        this.status = status;
        this.reason = reason;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }

}
