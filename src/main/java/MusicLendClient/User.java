package MusicLendClient;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

class User  {
    private Boolean _isAdmin;

    User() {
        this(Boolean.FALSE);
    }

    User(Boolean isAdmin) {
        this._isAdmin = isAdmin;
    }

    Boolean isAdmin() {
        return _isAdmin;
    }
}


class LocalUser extends User implements Observable {
    private Collection<Instrument> instrumentsInUse;
    private Collection<InvalidationListener> listeners = new HashSet<>();

    LocalUser(Boolean isAdmin) {
        this(isAdmin, new LinkedList<>());
    }

    LocalUser(Boolean isAdmin, Collection<Instrument> instrumentsInUse) {
        super(isAdmin);
        this.instrumentsInUse = instrumentsInUse;
    }

    Collection<Instrument> getInstrumentsInUse() {
        return instrumentsInUse;
    }

    void setInstrumentsInUse(Collection<Instrument> instrumentsInUse) {
        this.instrumentsInUse = instrumentsInUse;
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    void invalidate() {
        for (InvalidationListener listener: listeners) {
            listener.invalidated(this);
        }
    }

    User toUser() {
        return new User(isAdmin());
    }

    static LocalUser fromUser(User user) {
        return new LocalUser(user.isAdmin());
    }
}