# myMath

Ex1- ReadMe
Chen Ostrovski 
Ester Reznikov 
This project was done as part of assignment number 0. In this project we will implement two different classes:  “Polynom” (collection of Monoms) and “Monom”. The two classes contain Constructor and Methods which help us to do different actions on the objects.
Momon:
This class helps us to build objects from the type “Monom” (a*x^b). The Monom is made up of: ‘a’ is a double that represents the coefficient (can be any number) and ‘b’ is an integer (must be bigger or equal to zero) that represents the power. The class Monom implements from the interface “function” that contain only one function called “f” (see below for details). In the class Monom the user can see how different mathematical functions influencing the Monom. The methods can only be done on Monoms with the same power.
The user can build new Monoms with the help of the Constructors. Each Constructor is unique and helps us in different ways to build our Monom.
Regular Constructor: gets from the user two numbers ‘a’ and ‘b’. As already noted ‘a’ is a double that represents the coefficient (can be any number) and ‘b’ is an integer that represents the power. It must be bigger or equal to zero, if it’s not the user will be requested to put a valid input. The program will not allow the user to continue until proper input is entered.
Default Constructor: sets the coefficient and power to be equal to zero.
Copy Constructor: copying the values of existing Monom. Set the values in the coefficient and power as same as the values from the copied Monom.
String Constructor: get a String and converts it into numbers. Proper input: a*x^b. Putting another input might cause the program to stop working. For example inputs that contain letters of the alphabet will cause the program to send error.
The Monom class contains different mathematical functions each in its own way affects the Monom and allows doing different calculations on it.
F: function from the interface. In this function the user puts a value of any number (represented by ‘x’) and the function calculates the value of the Monom in this number.  
Add: add two Monoms one with the other. Only if both of the Monoms have the same power the function can add them. The function adds the two coefficients together and the power value stays the same.
Multiply: multiply two Monoms. The function multiplies the two coefficients one with the other and add the powers together. 
Derivative: derivative this Monom, It takes Monom and changes it to derivative version. The function multiplies the coefficient by power and then subtracts the power by one. If the power is equal to zero the function return zero.
Subtract: subtract two Monom one with the other. Only if both of the Monoms have the same power the function can subtract them. The function subtracts the two coefficients together and the power value stays the same.
ToString: prints the Monom. This function gets a Monom and prints its coefficient and power values, the function would print different Strings that depend on the values of the coefficient and power. If the coefficient is a negative number the function would print “()” around the coefficient. If the coefficient is equal to one the function wouldn’t print “1” and will print only “x” and the power. If the coefficient is equal to zero the function would print “0”. If the power is equal to one the function wouldn’t print “1” and will print only the coefficient and “x”. If the power is equal to zero the function wouldn’t print “0” and will a free number and if none of the above the function will print a regular Monom “a*x^b”.
Polynom:
Polynom is an ArrayList of Monoms. This class helps us to build objects from the type “Polynom” (f(x) = a_1*x^b_1 + a_2*x^b_2 + … + a_n*x^b_n). Every Monom contain the qualities given to him from the class Monom. The class Polynom implements from the interface “Polynom_able” that contain several function. ). In the class Polynom the user can see how different mathematical functions influencing the Polynom. The methods can be done on Monoms with different power.
The user can build new Polynoms with the help of the Constructors. Each Constructor is unique and helps us in different ways to build our Polynom.
Zero Constructor: build a new empty Polynom.
Copy Constructor: : copying the values of existing Polynom. Set the values of the Monoms in the copied Polynom. 
String Constructor: get a String and converts it into numbers. Proper input: f(x) = a_1*x^b_1 + a_2*x^b_2 + … + a_n*x^b_n. Putting another input might cause the program to stop working. For example inputs that contain letters of the alphabet will cause the program to send error. This function make split on the plus (“ + “) divides the String into different Monoms. The function uses “add” fuction of the Monom and the “String Construcrot” of the Monom.
The Polynom class contains different mathematical functions each in its own way affects the Polynom and allows doing different calculations on it.

Add Monom:  add Monom to a Polynom. The function checks if the Polynom contain a Monom with the same power as the Monom that we want to add. If it contains the function add those Monoms together, if this doesn’t it add the Monom in the end of the Polynom. In the end of the function we send the Polynom to the function “Remove zero” to check if it contain zero and to sort and Polynom.
Add Polynom: add Polynom to our Polynom. Goes through all the Monoms in the Polynoms and add them together, the same way the function “Add Monom” does.
Subtract Monom: subtract Monom from Polynom. The function checks if the Polynom contain a Monom with the same power as the Monom that we want to subtract. If it contains the function subtract those Monoms from each other, if this doesn’t it subtract the Monom from the end of the Polynom. In the end of the function we send the Polynom to the function “Remove zero” to check if it contain zero and to sort and Polynom.
Subtract Polynom: subtract Polynom from our Polynom. Goes through all the Monoms in the Polynoms and subtract them from each other, the same way the function “Subtract Monom” does.
Multiply: multiply two Polynom with each other. The function goes through all the Monoms in both of the Polynom and multiplies them. The function multiplies the Monom by multiply two coefficients one with the other and add the powers together.
Equals: this function check if two Polynoms have the same Monoms. If both contain the same Monoms return true, else return false.
IsZero: this function check if the Polynom is empty, Return true if it is, else return false.
Root: this function gets Polynom, two numbers x0 and x1 from the type double that represents a particular domain, x0 is smaller the x1 and epsilon. The function finds the root of the equation in a particular field.
Copy: create deep copy of Polynom.
Area: compute Reimann’s integral over the Polynom starting from x0 till x1 using epsilon as size steps. X0 has to be smaller than x1. The approximated area above the x-axis below this Polynom and between the [x0, x1]  range.
Iterator:  help the user to go over the ArrayList.
ToString: prints the Polynom. This function gets all the Monoms in the Polynom and prints the coefficient and power values, the function would print different Strings that depend on the values of the coefficient and power. The function uses the function “ToString” from the Monom and prints all the different possibilities that were mention before. 
F: in this function the user puts a value of any number (represented by ‘x’) and the function calculates the value of the Polynom in this number.
RemoveZero: remove zero Monom from the Polynom. This function remove from the Polynom Monoms that their coefficient is equal to zero. Also this function is sorting the Polynom, sorting from the biggest to lowest power. 
Monom Comperator:
This class compares between two Monoms and help the user to sort the Polynom by the size of the power.
