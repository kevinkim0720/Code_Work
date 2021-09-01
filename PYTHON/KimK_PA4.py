#Client name: Dr. Amy Connolly

#Programmer name: Kevin Kim

#PA purpose: Opens text file and does what PA wants

#My submission of this program indicates that I have neither received nor given substantial or unauthorized assistance in writing this program.

# try to see if text file is readable
try:
    a = open("animals.txt",'r')
    print("Success, file has been read\n")
    
# except to print if try does not work
except:
    print("unsuccessful")

# creates a list called alphabet from lines in text file
alphabet = list(a.readlines()[1:])

# sorts the list and inserts two new lines for index 0 and 1
alphabet.sort()
alphabet.insert(0,'Name,Phylum,Diet')
alphabet.insert(1,'----,------,----')

# for loop to split the lines into three parts and prints each item into each catagory
for item in alphabet:
    item = item.replace(',',' ').split()
    for x in range(len(alphabet)):
        if x == 0:
            print('{:<9s}{:<9s}{:<9s}'.format(item[x:][0],item[x:][1],item[x:][2]))

# for loop to print mammals that start if letters A to E
print("\n\n--- Mammals A - E ---")
for item in alphabet:
    item = item.replace(',',' ').split()
    if "Mammal" in item:
        if item[0].startswith("A") or item[0].startswith("B") or item[0].startswith("C") or item[0].startswith("D") or item[0].startswith("E"):
            print(item[0])
# for loops to catagorize animals to each phylum and prints name and diet
print("\n\n--- Animals by Phylum ---\n")
print("Mammals\n---------")
for item in alphabet:
    item = item.replace(',',' ').split()
    if "Mammal" in item:
        print('{:<9s}{:<9s}'.format(item[0],item[2]))
print("\nReptiles\n----------")
for item in alphabet:
    item = item.replace(',',' ').split()
    if "Reptile" in item:
        print('{:<9s}{:<9s}'.format(item[0],item[2]))
print("\nBirds\n--------")
for item in alphabet:
    item = item.replace(',',' ').split()
    if "Bird" in item:
        print('{:<9s}{:<9s}'.format(item[0],item[2]))

# input user to enter an animal
name = input("\n\nEnter an animal to show: ")

# if name starts with lowercase, changes it to uppercase
name = name[0].upper() + name[1:]

# for loop to find the animal and prints if it's in the list
for item in alphabet:
    item = item.replace(',',' ').split()
    if name in item:
        print("\n" + name + " is a " + item[1] + ", " + item[2])
