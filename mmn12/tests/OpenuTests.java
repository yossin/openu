import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class OpenuTests {

	private final Class<?> clazz;
	public OpenuTests(Class<?> clazz){
		this.clazz=clazz;
	}
	
	
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {     
                 { RGBColorTester.class },  
                 { LightBulbTester.class },  
                 { DiscoTester.class },  
           });
    }

    
    private List<String> loadExpectedResult() throws Exception{
    	URL url = OpenuTests.class.getClassLoader().getResource(clazz.getSimpleName()+".txt");
    	Path path = Paths.get(url.toURI());
    	return Files.readAllLines(path, Charset.forName("ISO-8859-1"));
    }
	
	PrintStream origOut;
	ByteArrayOutputStream out;
	private List<String> expected;
	
	@Before
	public void before() throws Exception {
		origOut = System.out;
		out=new ByteArrayOutputStream();
		System.setOut(new PrintStream(out));
		expected = loadExpectedResult();
	}
	
	@After
	public void after() {
		System.setOut(origOut);
	}
	
	
	@Test
	public void testRGBColorTester() throws Exception{
	    Method method = clazz.getMethod("main", String[].class);
	    String[] params = null;
	    method.invoke(null, (Object) params); // static method doesn't have an instance
	    
	    String lines[] = out.toString().split("\\r?\\n");
	    for (int i=0;i<lines.length;i++){
	    	assertEquals(expected.get(i).trim(), lines[i].trim());
	    }
	}
	
}
