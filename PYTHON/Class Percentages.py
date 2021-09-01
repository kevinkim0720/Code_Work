#Client name: Dr. Amy Connolly

#Programmer name: Kevin Kim

#PA purpose: To find the percentage of each gender in the class

#My submission of this program indicates that I have neither received nor given substantial or unauthorized assistance in writing this program.

#Ask user how many of each gender there are in his/her class
women = eval(input("How many women in your class? "))
men = eval(input("How many men in your class? "))
unknown = eval(input("How many unknown genders in your class? "))

#Equation for calculating percentage of each gender in the class
total = women + men + unknown
women_percent = format(women/total, ".1%")
men_percent = format(men/total, ".1%")
unknown_percent = format(unknown/total, ".1%")

#Prints all the percentages
print("The class is composed of", women_percent, "women,", men_percent, "men, and", unknown_percent, "unknown genders.")
