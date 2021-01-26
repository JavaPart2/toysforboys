package be.vdab.toysforboys.sessions;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
@SessionScope
public class CheckedOrders implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Set<Integer> ids = new LinkedHashSet<>();

    public boolean isOrderChecked(int id){
        return ids.contains(id);
    }
    public void voegToe(int id){
        ids.add(id);
    }

    public void verwijder(int id){
        ids.remove(id);
    }

    public Set<Integer> getIds() {
        return ids;
    }
}
