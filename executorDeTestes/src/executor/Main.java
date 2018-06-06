package executor;

public class Main {
	
	//example of how the class executes
	
	public static void main(String[] args){
		
		String[] paths = new String[2];
		paths [0] = "/executorPriorJ/executorDeTestes/src/testes/SingleTest";
		paths[1] = "/executorPriorJ/executorDeTestes/src/executor/Teste";
		Executor executor = new Executor(paths);
		
		try {
			
			executor.executeAllTests();
			System.out.println(executor.getTestsResults().toString());
			
		}catch(ClassNotFoundException e) {
			
			System.out.println("classe não encontrada");
			e.printStackTrace();
		}	
	}	
}