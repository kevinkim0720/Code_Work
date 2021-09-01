library(readr)
library(lmtest)
library(quantmod)
library(xts)
library(knitr)

# get data from fred stat
FredStat_Data = read_csv("FredStat_Data_1.csv")

# real money demand and logs for real disposable income and 3-month AA commerical paper rate
Y = log(FredStat_Data$M2SL/FredStat_Data$GDPDEF) * 100 
x_1 = log(FredStat_Data$DPIC96)
x_2 = log(FredStat_Data$CPF3M)

# estimate regression model
model = lm(Y~x_1+x_2)
summary(model)

# bg test the model for serial correlation
bg_model = bgtest(model, order = 4, fill = NA)

# puts results into a table
dfr=data.frame(bg_model[c(1,2,4)])
kable(dfr)

