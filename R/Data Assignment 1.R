# Reads file for data assignement
library(readr)
data_assignment1 = read_csv("Documents/data_assignment1.csv")

# Plot graphs for BAC stocks and AMZ stocks over time on two separate graphs
plot(data_assignment1$bac, xlab = "Time", ylab = "BAC Stock Prices", main = "BAC Stock Prices Over Time") 
plot(data_assignment1$amz, xlab = "Time", ylab = "AMZ Stock Prices", main = "AMZ Stock Prices Over Time")

# Compute means of both January and non-January stock prices for both BAC and AMZ
mean(data_assignment1$bac[data_assignment1$jan=="0"])
mean(data_assignment1$bac[data_assignment1$jan=="1"])
mean(data_assignment1$amz[data_assignment1$jan=="0"])
mean(data_assignment1$amz[data_assignment1$jan=="1"])

# Add variables for non-january and january stock prices for both BAC and AMZ
BAC_jan = data_assignment1$bac[data_assignment1$jan == "1"]
BAC_nonjan = data_assignment1$bac[data_assignment1$jan == "0"]
AMZ_jan = data_assignment1$amz[data_assignment1$jan == "1"]
AMZ_nonjan = data_assignment1$amz[data_assignment1$jan == "0"]

# Calculate t-test if mean jan stock price is greater than mean non-jan stock price for both BAC and AMZ
t.test(BAC_jan, BAC_nonjan, alternative = "greater")
t.test(AMZ_jan, AMZ_nonjan, alternative = "greater")

# Calculate variance if jan stock price is greater than non-jan stock price for both BAC and AMZ
var.test(BAC_jan, BAC_nonjan, ratio = 1)
var.test(AMZ_jan, AMZ_nonjan, ratio = 1)

# Calculate time-series table for both BAC and AMZ
stock_return_BAC = ts(data_assignment1$bac,start=c(2014,1),end=c(2020,1),frequency=12)
stock_return_AMZ = ts(data_assignment1$amz,start=c(2014,1),end=c(2020,1),frequency=12)

# Find stock returns and stock return percentages for both BAC and AMZ
return_BAC = diff(log(stock_return_BAC))
return_AMZ = diff(log(stock_return_AMZ))

mean(return_BAC)
mean(return_AMZ)
sd(return_BAC)
sd(return_AMZ)

return_percent_BAC = return_BAC * 100
return_percent_AMZ = return_AMZ * 100

# Plot percentage returns for both BAC and AMZ separately
plot(return_percent_BAC, main = "BAC Monthly  Percentage Returns (2014-2020)", ylab = "Return")
plot(return_percent_AMZ, main = "AMZ Monthly  Percentage Returns (2014-2020)", ylab = "Return")

# Plot Scatter plot for AMZ and BAC and the line of best fit
plot(x = data_assignment1$amz, y = data_assignment1$bac, xlab = "AMZ Monthly Returns", ylab = "BAC Monthly Returns",main = "Scatter Plot of Monthly Returns for AMZ and BAC", pch=21, bg="blue")

abline(lm(data_assignment1$bac~data_assignment1$amz))              
              
              
              
              
              