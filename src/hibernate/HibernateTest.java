package hibernate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.BeforeClass;
import org.junit.Test;

public class HibernateTest{

	
	@BeforeClass
	public static void setup() {
	}
	
	@Test
	public void getEntityManager() {
		
		BootstrapServiceRegistryBuilder bootstrapRegistryBuilder =
				new BootstrapServiceRegistryBuilder();
		BootstrapServiceRegistry bootstrapRegistry = bootstrapRegistryBuilder.build();
		
		StandardServiceRegistryBuilder standardRegistryBuilder =
			    new StandardServiceRegistryBuilder();
		
		StandardServiceRegistryBuilder standardServiceRegistryBuilder=
				new StandardServiceRegistryBuilder();
		standardServiceRegistryBuilder.applySetting("hibernate.dialect", "org.hibernate.dialect.DB2Dialect");
		standardServiceRegistryBuilder.applySetting("hibernate.connection.driver_class", "org.h2.Driver");
		standardServiceRegistryBuilder.applySetting("hibernate.connection.url", "jdbc:h2:tcp://localhost/~/test");
		standardServiceRegistryBuilder.applySetting("hibernate.connection.username", "sa");
		standardServiceRegistryBuilder.applySetting("hibernate.connection.password", "");
		standardServiceRegistryBuilder.applySetting("hibernate.connection.pool_size", "1");

		ServiceRegistry standardRegistry = standardServiceRegistryBuilder.build();

		MetadataSources sources = new MetadataSources( standardRegistry );
		sources.addPackage("hibernate");

		Metadata metadata = sources.buildMetadata();
		
		SessionFactory sessionFactory = metadata.buildSessionFactory();
		
		EntityManager entityManager = sessionFactory.openSession();

		assertThat(entityManager, is(notNullValue()));
		
		Member member = new Member("Kim");
		entityManager.persist(member);

	}

}
