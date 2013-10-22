package com.eldest.old.annotations;


// http://java.sun.com/docs/books/tutorial/java/javaOO/annotations.html
// http://www.javenue.info/post/79

public class test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Main.deprecatedMethod();
	}

}

@ClassPreamble(author="Eldest", date="21.01.09", reviewers={"me"})
class Main {
	   
    /**
     * @deprecated Use System.foobar().
    */ 

    @Deprecated
    public static void deprecatedMethod() {
      System.out.println("Don't call me");
    }
  }