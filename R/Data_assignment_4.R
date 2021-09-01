library(readr)

## Load data
sda4 <- read_csv("Downloads/sda4.csv")

## Declare time series for each variable
expenditure = ts(sda4$govt_spending, start = c(1947,1), end = c(2020,2), frequency = 4)
tax = ts(sda4$tax_revenue, start = c(1947,1), end = c(2020,2), frequency = 4)
gdp = ts(sda4$real_gdp, start = c(1947,1), end = c(2020,2), frequency = 4)

library(urca)

## Test for stationarity: Level
summary(ur.df(expenditure, type = "none", lags = 10, selectlags = "AIC"))
summary(ur.df(expenditure, type = "drift", lags = 10, selectlags = "AIC"))
summary(ur.df(expenditure, type = "trend", lags = 10, selectlags = "AIC"))
summary(ur.df(tax, type = "none", lags = 10, selectlags = "AIC"))
summary(ur.df(tax, type = "drift", lags = 10, selectlags = "AIC"))
summary(ur.df(tax, type = "trend", lags = 10, selectlags = "AIC"))
summary(ur.df(gdp, type = "none", lags = 10, selectlags = "AIC"))
summary(ur.df(gdp, type = "drift", lags = 10, selectlags = "AIC"))
summary(ur.df(gdp, type = "trend", lags = 10, selectlags = "AIC"))

## First difference
summary(ur.df(na.omit(diff(log(expenditure))), type = "none", lags = 10, selectlags = "AIC"))
summary(ur.df(na.omit(diff(log(expenditure))), type = "drift", lags = 10, selectlags = "AIC"))
summary(ur.df(na.omit(diff(log(expenditure))), type = "trend", lags = 10, selectlags = "AIC"))
summary(ur.df(na.omit(diff(log(tax))), type = "none", lags = 10, selectlags = "AIC"))
summary(ur.df(na.omit(diff(log(tax))), type = "drift", lags = 10, selectlags = "AIC"))
summary(ur.df(na.omit(diff(log(tax))), type = "trend", lags = 10, selectlags = "AIC"))
summary(ur.df(na.omit(diff(log(gdp))), type = "none", lags = 10, selectlags = "AIC"))
summary(ur.df(na.omit(diff(log(gdp))), type = "drift", lags = 10, selectlags = "AIC"))
summary(ur.df(na.omit(diff(log(gdp))), type = "trend", lags = 10, selectlags = "AIC"))

## Estimate model using log first difference
d_expenditure = na.omit(diff(log(expenditure)))
d_tax = na.omit(diff(log(tax)))
d_gdp = na.omit(diff(log(gdp)))

library(vars)

## vector of y 
yvector = ts.union(d_expenditure, d_tax, d_gdp)

## select optimal lag:
VARselect(yvector, lag.max = 8, type = c("const"))

## Estimate VAR(1)
varmodel = VAR(yvector, p = 1, type = c("const"))
summary(varmodel)

## Impulse response of gdp to a positive shock to taxes for 12 quarters
impulse_tax = irf(varmodel, impulse = "d_tax", ortho = TRUE, n.ahead = 12, response = c("d_gdp"))

## Plot impulse_tax
plot(impulse_tax)

## Impulse response of gdp to a positive shock to expenditures for 12 quarters
impulse_expenditure = irf(varmodel, impulse = "d_expenditure", ortho = TRUE, n.ahead = 12, response = c("d_gdp"))

## Plot impulse_expenditure
plot(impulse_expenditure)

## Variance decomposition
fevd(varmodel, n.ahead = 12)

## Vector of y2
yvector2 = ts.union(d_tax, d_expenditure, d_gdp)

## select optimal lag:
VARselect(yvector2, lag.max = 8, type = c("const"))

## Estimate VAR(1)
varmodel2 = VAR(yvector2, p = 1, type = c("const"))
summary(varmodel)

## Impulse response of gdp to a positive shock to taxes for 12 quarters
impulse_tax2 = irf(varmodel2, impulse = "d_tax", ortho = TRUE, n.ahead = 12, response = c("d_gdp"))

## Plot impulse_tax2
plot(impulse_tax2)

## Impulse response of gdp to a positive shock to expenditures for 12 quarters
impulse_expenditure2 = irf(varmodel2, impulse = "d_expenditure", ortho = TRUE, n.ahead = 12, response = c("d_gdp"))

## Plot impulse_expenditure2
plot(impulse_expenditure2)






