#Client name: Amy Connolly
#Program name:
#Students' names: Kevin Kim & Sahana Ven
#Our submission of this program indicates that we have neither given nor received unauthorized assistance in writing this program.
#I feel that work book questions being done in class help me understand concepts the most. The PAs challenge me and help me realize the extent of my knowledge.

# user input
name = input("What is the name of the new phone? ")
if name[0].isalpha():
    sales_price = input("What is the sales price of the new phone? ")
    if "$" in sales_price:
        sales_price = sales_price[1:]
    if "." in sales_price or sales_price.isdigit():
        sales_price = float(sales_price)
        if sales_price > 100:
            if sales_price > 599.99:
                chip = 120.00
            elif sales_price > 349.99 and sales_price < 600.00:
                chip = 90.00
            else:
                chip = 80.00
# calculate cost of other items based on input
            camera = .06 * sales_price
            battery = sales_price // 9
            display = ord(name[0])
            chipset = input("Which chipset - Helio or Exynos? ")
            chipset = chipset.upper()
            if chipset[0] == "H":
                memory = .18 * sales_price
                discount = input("Do you qualify for the Helio discount? ")
                discount = discount.upper()
                if discount[0] == "Y":
                    chip = chip * .9
            elif chipset[0] == "E":
                memory = .27 * sales_price           
            else:
                print("Invalid chipset. Must be Helio or Exynos")
            total_cost = chip + memory + camera + battery + display
            net_profit = round(sales_price - total_cost,2)
            name = name.upper()
            print("The new phone is named",name)
            print("\nNew price\t\t",'${:7,.2f}'.format(sales_price),"\nThe costs are: \n\tChipset\t\t",'${:7,.2f}'.format(chip),"\n\tMemory\t\t",'${:7,.2f}'.format(memory),"\n\tCamera\t\t",'${:7,.2f}'.format(camera),"\n\tBattery\t\t",'${:7,.2f}'.format(battery),"\n\tDisplay\t\t",'${:7,.2f}'.format(display),"\n\tTotal Cost\t",'${:7,.2f}'.format(total_cost),"\nNet profit\t\t",'${:7,.2f}'.format(net_profit))

            euro = input("Convert profit to Euros (Y/N)? ")
            króna = input("Convert profit to Króna (Y/N)? ")
            yen = input("Convert profit to Yen (Y/N)? ")
# Calculate different currency conversions using user input
            euro = euro.upper()
            króna = króna.upper()
            yen = yen.upper()
            if "Y" in euro[0]:
                e = round(.9 * net_profit,3)
                print("Profit of",'${:0,.2f}'.format(net_profit), "is",e,"\u20AC")
            if "Y" in króna[0]:
                k = round(126.233 * net_profit,3)
                print("Profit of",'${:0,.2f}'.format(net_profit), "is",k,"\u00f3")
            if "Y" in yen[0]:
                y = round(106.975 * net_profit,3)
                print("Profit of",'${:0,.2f}'.format(net_profit), "is",y,"\u00A5")
# Based on user input, print corrections to user input
        else:
            print("Price must be at least $100")
    else:
        print("Price must be numeric") 
        
else:
    print("Invalid phone name", name)
    
