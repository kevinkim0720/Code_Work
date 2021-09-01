#Client name: Dr. Amy Connolly

#Programmer name: Kevin Kim

#PA purpose (in your own words, what does the program do for the client): Computes net pay check for the user by asking for inputs and creates a new access code

#My submission of this program indicates that I have neither received nor given substantial or unauthorized assistance in writing this program.

# Asks the user to input his/her name, hours worked, hourly pay, his/her federal and state tax rates.
name = input("Enter employee's name (first and last): ")
number_of_hours = eval(input("Enter nubmer of hours worked this week: "))
hourly_pay = eval(input("Enter hourly pay rate: "))
federal_tax = eval(input("Enter federal tax withholding rate (.10 for single or .15 for married): "))
state_tax = eval(input("Enter state tax withholding rate: "))

# Calculates gross pay by multiplying hours worked and hourly pay
gross_pay = number_of_hours * hourly_pay

# Calculates federal and state tax, total tax cut, and net pay check
federal_withholding = gross_pay * federal_tax
state_withholding = gross_pay * state_tax
total_deduction = federal_withholding + state_withholding
net_pay_check = gross_pay - total_deduction

# Code to create the new access code using the user's name and requirements given by the instructor
position = len(name)//4
a = name[position:10]
b = name[-2:].upper()
c = min(name) + max(name)
name1,name2 = name.split()
d = name1[1]
e = name2[1]
code = a+b+c+d+e

# Prints all things that the instruction wants printed for the user to see
print("\nEmployee Name:",name,"\n\nHours Worked:",number_of_hours,"\nPay Rate:",'${:0,.2f}'.format(hourly_pay),"\nGross Pay:",'${:0,.2f}'.format(gross_pay))
print("Deductions: \n\tFederal Withholding (",format(federal_tax,'.0%'),"):\t",'${:0,.2f}'.format(federal_withholding))
print("\tState Withholding (",format(state_tax, '.0%'),"):\t",'${:0,.2f}'.format(state_withholding))
print("\tTotal Deduction:\t\t",'${:0,.2f}'.format(total_deduction))
print(name,"net pay check:\t\t",'${:0,.2f}'.format(net_pay_check))
print("Your new access code is:", code)
