#Client name: Dr. Amy Connolly

#Programmer name: Kevin Kim

#PA purpose: Accepts a pair of coordinates and outputs the equation of the line

#My submission of this program indicates that I have neither received nor given substantial or unauthorized assistance in writing this program.

#Asks user to input x and y value
(x,y) = eval(input("Give a pair of coordinates (number separated with a comma): "))

#Equation for slope and y-intercept
m = y/x
b = y

#Prints the linear equation
print("y = ",m,"* x +",b)
