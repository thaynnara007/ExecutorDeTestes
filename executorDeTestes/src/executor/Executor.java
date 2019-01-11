package executor;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Request;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Executor {

    private String path;
    private List<Integer> testsResults;
    private String reportFile;
    private String bar = System.getProperty("file.separator");


    public Executor(String newPath, String reportFile) {

        this.path = newPath;
        testsResults = new ArrayList<>();
        this.reportFile = reportFile;
    }
    public Integer[] getTestsResults(){

        int size = this.testsResults.size();
        Integer[] result = this.testsResults.toArray(new Integer[size]);
        System.out.println(this.testsResults.toString());

        return result;
    }

    public void executeAllTests() throws ClassNotFoundException {

        try {
            this.cleanReporFile(this.reportFile);
        }catch(IOException e){
            System.out.println( e.getMessage());
        }

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

                if(result.wasSuccessful()) {

                    this.testsResults.add(0);

                    try {
                        this.writeFile(this.reportFile, true, "\n------------------------------------------------------------------------------\n" + pathTest + " --- Pass\n");
                    }catch(IOException e){
                        System.out.println( e.getMessage());
                    }

                }else {

                    String erro = "\n------------------------------------------------------------------------------\n" +
                            pathTest + "--- Fail\n----------------------------Exception-----------------------------------------\n";
                    for (Failure failure : result.getFailures()) {

                        try {
                            erro = erro + failure.toString();
                            this.writeFile(this.reportFile, true, erro );
                        }catch(IOException e){
                            System.out.println( e.getMessage());
                        }
                    }

                    this.testsResults.add(1);
                }

            }
        }
    }

    private void writeFile(String path, boolean appendFile, String text) throws IOException{

        FileWriter write = new FileWriter(path, appendFile);
        PrintWriter print_line = new PrintWriter(write);

        print_line.printf("%s" + "%n", text);

        print_line.close();
    }

    private void cleanReporFile(String path) throws IOException{

        FileWriter write = new FileWriter(path, false);
        PrintWriter print_line = new PrintWriter(write);

        print_line.printf("");
        print_line.close();
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
