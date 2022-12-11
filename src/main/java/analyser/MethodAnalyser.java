package analyser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MethodAnalyser {
	
	/**
	 * Simple visitor implementation for visiting MethodDeclaration nodes.
	 */
	private static class MethodVisitor extends VoidVisitorAdapter<Object> {

		@Override
		public void visit(MethodDeclaration n, Object arg) {
			// this method will be called for all methods in this
			// CompilationUnit, including inner class methods
			System.out.println(n.getName());
		}
	}

	public static void main(String[] args) {
		
		String filename= "TestCode.java";
		
		System.out.println("**** File names in file "+filename+" ****");

		FileInputStream in;
		JavaParser javaParser = new JavaParser();
		ParseResult<CompilationUnit> pcu;
		
		try {
			in = new FileInputStream(filename);
			pcu = javaParser.parse(in);

			if (pcu.isSuccessful() && pcu.getResult().isPresent()) {
				CompilationUnit cu = pcu.getResult().get();
				new MethodVisitor().visit(cu, null);
			} 
			else {
				System.out.println("Issue，" + pcu.getProblem(0).toString());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
