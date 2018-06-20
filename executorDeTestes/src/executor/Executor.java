package executor;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

public class Executor {
	
	private String path;
	private List<Integer> testsResults;
	private String bar = System.getProperty("file.separator");

	
	public Executor(String newPath) {
		
		this.path = newPath;
		testsResults = new ArrayList<>();
	}
	public Integer[] getTestsResults(){
		
		int size = this.testsResults.size();
		Integer[] result = this.testsResults.toArray(new Integer[size]); 
		System.out.println(this.testsResults.toString());
		
		return result;
	}
	
	public void executeAllTests() throws ClassNotFoundException {

			File folder = new File(this.path);
			File[] clazzs = folder.listFiles();
			
			for(File clazz : clazzs ) {
				
				String[] pathParts = this.mySplit(clazz.getPath(), bar);
				int size = pathParts.length - 1;
				String packag = pathParts[size - 1];
				String clas_s = pathParts[size].replace(".java", "");
				String pathTest = packag + "." + clas_s;
				
				this.executeSingleTest(pathTest);
		}
	}
	
	private void executeSingleTest(String pathTest) throws ClassNotFoundException{	
		
		
		Class<?> testClass =  Class.forName(pathTest);
				
		for(Method method : testClass.getDeclaredMethods()) {
			
			if(method.isAnnotationPresent(Test.class)) {
				
				Request request = Request.method(testClass, method.getName());
				Result result = new JUnitCore().run(request);
				
				if(result.getFailureCount() == 0) {
					
					this.testsResults.add(0);
				}else {
					
					this.testsResults.add(1);
				}
				
			}
		}
	} 
	
	
	private String[] mySplit(String palavra, String quebra ) {
		
		List<String> saida = new ArrayList<>();
		String word = "";
		
		for(int i = 0; i < palavra.length(); i++) {
			
			String caracter = "" + palavra.charAt(i);
			if(caracter.equals(quebra)) {
				
				saida.add(word);
				word = "";
			}
			else {
				
				word += caracter;
			}
		}
		saida.add(word);
		
		String[] arraySaida = saida.toArray(new String[saida.size()]);
		
		return arraySaida;
	} 
}
