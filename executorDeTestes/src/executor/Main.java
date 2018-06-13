package executor;

public class Main {
	
	//example of how the class executes
	
	public static void main(String[] args){
		
		String[] paths = new String[4];
		paths[0] = "/executorDeTestes/src/testes/test1$SingleTest.java#algumaCoisa";
		paths[1] = "/executorDeTestes/src/testes/test2$SingleTest.java#algumaCoisa";
		paths[2] = "/executorDeTestes/src/executor/test1$Teste.java#algumaCoisa";
		paths[3] = "/executorDeTestes/src/executor/test2$Teste.java#algumaCoisa";
		Executor executor = new Executor(paths);
		
		try {
			
			executor.executeAllTests();
			executor.getTestsResults();
			
		}catch(ClassNotFoundException e) {
			
			System.out.println("classe não encontrada");
			e.printStackTrace();
		}	
	}	
}
