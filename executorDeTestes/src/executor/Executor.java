package executor;

import java.util.ArrayList;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

public class Executor {
	
	private String[] allPaths;
	private List<List<Integer>> testsResults;
	private String bar = System.getProperty("file.separator");

	
	public Executor(String newPaths) {
		
		this.allPaths = this.mySplit(newPaths, ";");
		testsResults = new ArrayList<>();
	}
	public List<List<Integer>> getTestsResults(){
		
		for (List<Integer> list : this.testsResults) {

			System.out.println(list.toString());
		}
		
		return testsResults;
	}
	public void executeAll() throws ClassNotFoundException {
		
		
		for(String path: allPaths) {
			
			List<Integer> resultTest = new ArrayList<>();
			String[] testsPath = this.mySplit(path, ",");
			
			this.executeAllTestsFromOnePath(testsPath, resultTest);
			this.testsResults.add(resultTest);
		}
	}
	
	private void executeAllTestsFromOnePath(String[] testsPath, List<Integer> resultTest) throws ClassNotFoundException {

		for(String path : testsPath) {

			String[] pathParts = path.split(bar);
			int size = pathParts.length - 1;
			
			String[] pathParts2 = this.mySplit(pathParts[size], "$");
			String[] pathParts3 = this.mySplit(pathParts2[1], "#");
			
			String packag = pathParts[size - 1];
			String clazz = pathParts2[0];
			String method = pathParts3[0].replace(".java", "");
			
			this.executeSingleTest(packag, clazz, method, resultTest);
			
		}
	}
	
	private void executeSingleTest(String packag, String clazz, String method, List<Integer> resultTest) throws ClassNotFoundException{	
		
		String pathTest = packag + "." + clazz;
		Class<?> testClass =  Class.forName(pathTest);

		Request request = Request.method(testClass, method);
		Result result = new JUnitCore().run(request);
		
		if(result.getFailureCount() == 0){
					
				resultTest.add(0);
		}else {

				resultTest.add(1);
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
				
				if(!caracter.equals(" ")) 
				
					word += caracter;
			}
		}
		if (!word.equals(""))
			saida.add(word);
		
		String[] arraySaida = saida.toArray(new String[saida.size()]);
		
		return arraySaida;
	}
}
