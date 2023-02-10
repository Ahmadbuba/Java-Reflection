package com.ahmad.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Checked Exception
public class Main {

	public static void main(String[] args) throws Exception {

		Cat.thisIsAPublicStaticMethod();
		System.out.println();
		
		Cat myCat = new Cat("Stella", 6);
		
		// Code to see structure of a java class 13-18
		Field[] catFields = myCat.getClass().getDeclaredFields();
		
		for(Field field: catFields) {
			if(field.getName().equals("name")) {
				field.setAccessible(true);
				field.set(myCat, "Jimmy McGill");
			}
			// System.out.println(field.getName());
		}
		
		Method[] catMethods = myCat.getClass().getDeclaredMethods();
		
		for (Method method: catMethods) {
			if (method.getName().equals("meow")) {
				method.invoke(myCat);
				
				// Since meow method is actually public, theirs no use of the above
				// code since we could easily call it directly as seen below
				myCat.meow();
				System.out.println();
			}
			
			// Calling public static methods is easy, just set object null when invoking
			if (method.getName().equals("thisIsAPublicStaticMethod")) {
				method.setAccessible(true);
				method.invoke(null);
				System.out.println();
			}
			
			// typical use case of reflection is to access private methods as shown
			// below
			if (method.getName().equals("heyThisIsPrivate")) {
				method.setAccessible(true);
				method.invoke(myCat);
				System.out.println();
			}
			
			// Calling private static methods
						if (method.getName().equals("thisIsAPrivateStaticMethod")) {
							method.setAccessible(true);
							method.invoke(null);
							System.out.println();
						}
			
//			System.out.println(method.getName());
//			System.out.println();
		}
		
		System.out.println(myCat.getName());
	}

}
