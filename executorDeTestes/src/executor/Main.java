package executor;

public class Main {
	
	//example of how the class executes
	
	public static void main(String[] args){

        String path = "/home/r2d2/turmalina/TestCases/src/test";
        String report = "/home/r2d2/turmalina/TestCases/src/report.txt";

        Executor executor = new Executor(path, report);

        try {

            executor.executeAllTests();
            executor.getTestsResults();

        }catch(ClassNotFoundException e) {

            System.out.println("classe não encontrada");
            e.printStackTrace();
        }
    }
}
