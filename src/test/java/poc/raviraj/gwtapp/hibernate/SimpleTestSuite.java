package poc.raviraj.gwtapp.hibernate;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	ConfigurationAdditionViaTests.class,
	TestHibernateConfiguration.class
})
public class SimpleTestSuite {
	
	@BeforeClass
	public static void beforeClassMethod(){
		System.out.println("Before Class Method Called");
	}
	
	@AfterClass
	public static void afterClassMethod(){
		System.out.println("After Class Method Called");
	}

}
