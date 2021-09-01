#Client name: Dr. Amy Connolly

#Programmer name: Kevin Kim

#PA purpose: To print out employee payroll summary using functions and user inputs.

#My submission of this program indicates that I have neither received nor given substantial or unauthorized assistance in writing this program.


## Global lists
list_name = []
list_hourly_pay = []
list_total_hours = []
list_total_pay = []
list1 = []
list2 = []

# function (r)ead the file given and separate them into respective lists using good data.
def r():
    total = 0
    file = input("Enter the file name: ")
    if ".txt" not in file:
        file1 = file + ".txt"
    else:
        file1 = file
    try:
        r = open(file1,'r')
        print("File has been read\n")
        
        alphabet = r.readlines()[1:]
        alphabet.sort()
        alphabet = list(map(str.strip,alphabet))

        for i in range(len(alphabet)):
            list1 = alphabet[i].split(',')
            for j in range(0,len(list1),7):
                list_name.append(list1[j])
                list_hourly_pay.append(list1[j+1])
            try:
                if len(list_name) == len(list_hourly_pay):
                    Total_hours = float(list1[j+2]) + float(list1[j+3]) + float(list1[j+4]) + float(list1[j+5]) + float(list1[j+6])
                    list_total_hours.append(str(Total_hours))
            except:
                print("bad data in file " + file1)
                break
                main()
                
        for i in range(len(list_hourly_pay)):
            Total_pay = float(list_hourly_pay[i]) * float(list_total_hours[i])
            list_total_pay.append(str('{:.2f}'.format(Total_pay)))
                    
        for k in range(len(list_name)):
            list2.append(list_name[k])
            list2.append(list_total_hours[k])
            list2.append(list_total_pay[k])
            list2.append(list_hourly_pay[k])
            
        main()
        
    except:
        print("Error reading file",file,".txt\n")
        main()

# function (p)rint payroll by the given list and only works if the data was read first
def p():
    total = 0
    if len(list_name)!= 0:
        for i in range(len(list_total_pay)):
            total += float(list_total_pay[i])
        
        star_max_hourly_pay = list2.index(max(list_hourly_pay))
        list2[star_max_hourly_pay-2] = list2[star_max_hourly_pay-2] + "*"

        star_min = list2.index(min(list_hourly_pay))
        list2[star_min-2] = list2[star_min-2] + "*"

        
        print('{:<9s}{:<9s}{:<9s}'.format("Name","Hours","Pay"))
        print('{:<9s}{:<9s}{:<9s}'.format("----","-----","---"))
    
        for x in range(0,len(list2),4):
            print('{:<9s}{:<9s}${:<9s}'.format(list2[x],list2[x+1],list2[x+2]))

        print("\nTotal payroll = " + str('${:,.2f}'.format(total)) + "\n")

        main()
       
    else:
        print("Please read the file before making this choice\n")
        main()


# function (d)isplay given user name their total work hours, pay hour, and total pay if the name is in the list.
def d():
    if len(list_name) != 0:        
        name = input("Enter the employee's name: ")
        name = name[0].upper() + name[1:]
        
        if name in list2:
            i = list2.index(name)
            print(name + " worked " + list2[i+1] + " hours at " + '${}'.format(list2[i+3]) + " per hour, and earned " + '${}'.format(list2[i+2]) + "\n")
            main()
        else:
            print(name + " is not in data. Try again.\n")
            d()
    else:
        print("Employee data has not been read.\nPlease read the file before making this choice\n")
        main()
        
# function (h)ighest given the highest total pay in the given list
def h():
    if len(list2) != 0:
        max_pay = list2.index(max(list_total_pay))
        print(list2[max_pay-2] + " earned " + '${}'.format(list2[max_pay]) + "\n")
        main()
    else:
        print("Employee data has not been read.\nPlease read the file before making this choice\n")
        main()

# function (l)owest given the lowest total pay in the given list
def l():
    if len(list2) != 0:
        min_pay = list2.index(min(list_total_pay))
        print(list2[min_pay-2] + " earned " + '${}'.format(list2[min_pay]) + "\n")
        main()
    else:
        print("Employee data has not been read.\nPlease read the file before making this choice\n")
        main()

# function (q)uit to end the program
def q():
    print("goodbye")

# function main which will call other functions when the user inputs a letter in the main.
def main():
    
    print("Menu of choices:\n\t(r)ead employee data\n\t(p)rint employee by name\n\t(d)isplay an employee by name\n\tfind (h)ighest paid employee\n\tfind (l)owest paid employee\n\t(q)uit\n")
    choice = input("Please enter your choice: ")
    
    choice = choice.lower()
    
    if choice == "r":
        return r()
    elif choice == "p":
        return p()
    elif choice == "d":
        return d()
    elif choice == "h":
        return h()
    elif choice == "l":
        return l()
    elif choice == "q":
        return q()
    
    while choice != "r" and "p" and "d" and "h" and "l" and "q":
        print("Invalid choice. Please try again")
        print("Menu of choices:\n\t(r)ead employee data\n\t(p)rint employee by name\n\t(d)isplay an employee by name\n\tfind (h)ighest paid employee\n\tfind (l)owest paid employee\n\t(q)uit\n")
        choice = input("Please enter your choice: ")
        
        choice = choice.lower()
        
        if choice == "r":
            return r()
        elif choice == "p":
            return p()
        elif choice == "d":
            return d()
        elif choice == "h":
            return h()
        elif choice == "l":
            return l()
        else:
            return q()
    
main()
