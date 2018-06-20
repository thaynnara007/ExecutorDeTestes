package executor;

public class Main {
	
	//example of how the class executes
	
	public static void main(String[] args){
		
		String path = "/home/obi-wan/executorPriorJ/executorDeTestes/src/testes";
		
		Executor executor = new Executor(path);
		
		try {
			
			executor.executeAllTests();
			executor.getTestsResults();
			
		}catch(ClassNotFoundException e) {
			
			System.out.println("classe não encontrada");
			e.printStackTrace();
		}	
	}	
}
