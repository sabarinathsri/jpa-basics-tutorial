package com.jpa.basics.tutorial;

import com.jpa.basics.tutorial.domain.Artist;
import com.jpa.basics.tutorial.service.ArtistService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaBasicsTutorial {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JpaBasicsTutorial");
        EntityManager em = emf.createEntityManager();
        ArtistService service = new ArtistService(em);

        System.out.println("--- Create and persist artist ---");
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Artist artist = service.createArtist(1, "Franz Ferdinand", "Rock");
        transaction.commit();
        System.out.println(String.format("Persisted: %s\n", artist));

        System.out.println("--- Find artist ---");
        artist = service.findArtist(1);
        System.out.println(String.format("Found: %s\n", artist));

        System.out.println("--- Find all artists ---");
        List<Artist> artists = service.findAllArtists();
        for (Artist foundArtist : artists) {
            System.out.println(String.format("Found: %s\n", foundArtist));
        }

        System.out.println("--- Update artist ---");
        transaction.begin();
        artist = service.changeArtistGenre(1, "Indie Rock");
        transaction.commit();
        System.out.println(String.format("Updated: %s\n", artist));

        System.out.println("--- Remove artist ---");
        transaction.begin();
        service.removeArtist(1);
        transaction.commit();
        artist = service.findArtist(1);
        System.out.println(String.format("Found: %s\n", artist));
    }
}
