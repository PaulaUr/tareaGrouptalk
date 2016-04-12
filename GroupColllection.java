package eetac.upc.eetac.dsa.grouptalk.entity;

import javax.ws.rs.core.Link;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pauli on 12/04/2016.
 */
public class GroupColllection {
    private List<Link> links;
    private long newestTimestamp;
    private long oldestTimestamp;
    private List<Group> grupos = new ArrayList<>();

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public long getNewestTimestamp() {
        return newestTimestamp;
    }

    public void setNewestTimestamp(long newestTimestamp) {
        this.newestTimestamp = newestTimestamp;
    }

    public long getOldestTimestamp() {
        return oldestTimestamp;
    }

    public void setOldestTimestamp(long oldestTimestamp) {
        this.oldestTimestamp = oldestTimestamp;
    }

    public List<Group> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Group> grupos) {
        this.grupos = grupos;
    }
}
