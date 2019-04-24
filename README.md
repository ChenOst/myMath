#My Math

Chen Ostrovski
Ester Reznikov

This project was done as part of assignment given at class. In this project we will implement two different classes: “Polynom” (collection of Monoms) and “Monom”. The two classes contain Constructor and Methods which help us to do different actions on the objects.
##MonomThis class helps us to build objects from the type “Monom” (a*x^b). The Monom is made up of: ‘a’ is a double that represents the coefficient (can be any number) and ‘b’ is an integer (must be bigger or equal to zero) that represents the power. This class contains multiple mathematical functions: add, subtract, multiply, division and more, it also contains functions that compare and checks the Monon value. This class is the base of the Polynom class.

##Polynom
Polynom is an ArrayList of Monoms. This class helps us to build objects from the type “Polynom” (f(x) = a_1*x^b_1 + a_2*x^b_2 + … + a_n*x^b_n). Every Monom contain the qualities given to him from the class Monom. In the class Polynom the user can see how different mathematical functions influencing the Polynom. The methods can be done on Monoms with different power. This class contains all the methods from the Monom class and it combines actions that can be done between Monom and Polynom and between two Polynoms.

##Monom Comperator
This class compares between two Monoms and help the user to sort the Polynom by the size of the power.
Plot:
This class represents the library “Gral”. This code was taken from the site: https://github.com/eseifert/gral and was adapted to our needs.  The class accepts Polynom, draws it and marks the min and max points of the Polynom. The user has to use frame.setVisible(true); in order to see the plot. The class contains only one constructor, this constructor build the Plot. 

##Plot constructor
This method builds a new plot. It accepts Polynom (represented by polynom) and range (represented by x1 and x2). The method will search in the range for the min and max points. x1 has to be smaller than x2, else the method will throw exception. Given a Polynom we will mark it in blue, and after finding the min and max points we will mark them in black. The calculation of the points will be done by using the function "root" from the Polynom class. The user has to use frame.setVisible(true); in order to see the plot. The constructor throw exception if the range is invalid (x1 is bigger than x2).

