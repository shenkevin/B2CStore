package cn.mstar.store.interfaces;

/**
 * Created by Ultima on 7/14/2015.
 */
public interface ConnectedStatusListener {

    // get from the sharedpreferences the connexion status.
    boolean isLogged();
    void logIn();
    void exitLoginState();
}
