package Univ_JeuDeLaVie_Java.grille.observeurs;

public interface Observable {
    public void attacheObservateur(Observateur o);
    public void detacheObservateur(Observateur o);
    public void notifieObservateurs();
}
