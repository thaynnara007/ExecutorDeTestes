package executor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;

public class Executor {
	
	private String[] paths;
	private List<Integer> testsResults;

	
	public Executor(String[] newPaths) {
		
		this.paths = newPaths;
		testsResults = new ArrayList<>();
	}
	public Integer[] getTestsResults(){
		
		int size = this.testsResults.size();
		Integer[] result = this.testsResults.toArray(new Integer[size]); 
		
		return result;
	}
	
	public void executeAllTests() throws ClassNotFoundException {

		for(String path : paths) {
			
			String[] pathParts = path.split("/");
			int size = pathParts.length - 1;
			String path2 = pathParts[size -1] + "." + pathParts[size];
		
			this.executeSingleTest(path2);
		}
	}
	
	private void executeSingleTest(String pathTest) throws ClassNotFoundException{	
		
		Class<?> testClass =  Class.forName(pathTest);
		
		for (Method method : testClass.getDeclaredMethods()) {

			if(method.isAnnotationPresent(Test.class)) {
				
				Request request = Request.method(testClass, method.getName());
				Result result = new JUnitCore().run(request);
				
				if(result.getFailureCount() == 0){
					
					this.testsResults.add(0);
				}else {
					
					this.testsResults.add(1);
				}
			}
		}
	}
}
