package executor;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

public class Executor {
	
	private String[] paths;
	private List<Integer> testsResults;
	private String bar = System.getProperty("file.separator");

	
	public Executor(String[] newPaths) {
		
		this.paths = newPaths;
		testsResults = new ArrayList<>();
	}
	public Integer[] getTestsResults(){
		
		int size = this.testsResults.size();
		Integer[] result = this.testsResults.toArray(new Integer[size]); 
		System.out.println(this.testsResults.toString());
		
		return result;
	}
	
	public void executeAllTests() throws ClassNotFoundException {

		for(String path : paths) {
			
			String[] pathParts = path.split(bar);
			int size = pathParts.length - 1;
			String[] pathParts2 = this.mySplit(pathParts[size], "$");
			String[] pathParts3 = this.mySplit(pathParts2[1], "#");
			
			String packag = pathParts[size - 1];
			String method = pathParts2[0];
			String clazz = pathParts3[0].replace(".java", "");
			
			this.executeSingleTest(packag, clazz, method);
		}
	}
	
	private void executeSingleTest(String packag, String clazz, String method) throws ClassNotFoundException{	
		
		String pathTest = packag + "." + clazz;
		Class<?> testClass =  Class.forName(pathTest);
				
		Request request = Request.method(testClass, method);
		Result result = new JUnitCore().run(request);
				
		if(result.getFailureCount() == 0){
					
				this.testsResults.add(0);
		}else {

				this.testsResults.add(1);
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
