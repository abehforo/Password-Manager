all: Main.java User.java AccountManager.java AppData.java FileIO.java PasswordGenerator.java PasswordStrengthTester.java AppManager.java Account.java
	javac Main.java User.java AccountManager.java AppData.java FileIO.java PasswordGenerator.java PasswordStrengthTester.java AppManager.java Account.java	

run: all
	java Main
    
jar: all
	jar cfm PasswordManager.jar manifest.txt *.class
	java -jar PasswordManager.jar
    
clean:
	rm*.class
