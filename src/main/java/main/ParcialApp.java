package main;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;

import entities.Autor;
import entities.Domicilio;
import entities.Libro;
import entities.Localidad;
import entities.Persona;

public class  ParcialApp{
		public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("ParcialAppPU");
		EntityManager em = emf.createEntityManager();
		
		try {
			em.getTransaction().begin();
			
			Autor autor = Autor.builder()
					.nombre("William")
					.apellido("Shakespeare")
					.biografia("un dramaturgo, poeta y actor inglés")
					.build();
			
			Domicilio domicilio = Domicilio.builder()
					.calle("Masa")
					.numero(123)
					.localidad(localidad)
					.build();
			
			Libro libro = Libro.builder()
					.fecha(1609)
					.genero("Tragedia")
					.paginas(720)
					.titulo("Hamlet")
					.build();
			
			Localidad localidad = Localidad.builder()
					.denominacion("asd")
					.build();
			
			
			
			Persona persona = Persona.builder()
					.apellido("Escanes")
					.nombre("Valentino")
					.dni(43354725)
					.domicilio(domicilio)
					.build();
			
			libro.getAutores().add(autor);
			persona.getLibros().add(libro);
			
			em.persist(autor);
			em.persist(libro);
			em.persist(localidad);
			em.persist(domicilio);
			em.persist(persona);
			em.flush();
			em.getTransaction().commit();
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			JOptionPane.showConfirmDialog(null, e);
		}
		em.close();
		emf.close();
	}
}