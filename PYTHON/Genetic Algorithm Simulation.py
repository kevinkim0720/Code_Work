#Client name: Dr. Amy Connolly

#Programmer name: Kevin Kim

#PA purpose: Calculating population growth of weebles

#My submission of this program indicates that I have neither received nor given substantial or unauthorized assistance in writing this program.

import math

#Ask for user input for starting population
population = eval(input("How many weebles do you start with? "))

#Population increases by 10 every odd month
increase_population = 10

#4 people die every month because 1 dies every week
dead_old_age = 4

#calculation for each month given rules (even months, 20% of the population dies, every 3rd month, population doubles)
month1 = population + increase_population - dead_old_age
month2 = math.ceil(month1 * .8) - dead_old_age
month3 = (month2 - dead_old_age) * 2 + increase_population
month4 = math.ceil(month3 * .8) - dead_old_age
month5 = month4 + increase_population - dead_old_age
month6 = math.ceil((month5 *.8 - dead_old_age) * 2)

print("The weeble population is", format(month1,","), "after month 1. \nThe weeble population is", month2, "after month 2.\nThe weeble population is", format(month3,","),"after month 3.\nThe weeble population is", format(month4,","),"after month 4.\nThe weeble population is", format(month5,","),"after month 5.\nThe weeble population is", format(month6,","),"after month 6.")
