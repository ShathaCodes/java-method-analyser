package analyser;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import java.io.File;
import java.io.IOException;

public class MethodAnalyser {

	public static void listMethodNames(File file) {

		System.out.println(file);
		try {
			new VoidVisitorAdapter<Object>() {
				@Override
				public void visit(MethodDeclaration n, Object arg) {
					super.visit(n, arg);
					System.out.println(" [L " + n.getBegin() + "] " + n.getName());
				}
			}.visit(JavaParser.parse(file), null);
			System.out.println(); // empty line
		} catch (IOException e) {
			new RuntimeException(e);
		}

	}

	public static void main(String[] args) {
		File filename = new File("TestCode.java");
		listMethodNames(filename);
	}

}
