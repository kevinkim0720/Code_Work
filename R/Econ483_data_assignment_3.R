library(readr)
library(forecast)
library(xts)

## load data from csv with date column  and the data (adjusted close)
data =read.csv("~/Documents/daily_Dow.csv")

data=na.omit(data)

## FORMAT  date COLUMN TO POSIXlt
data$Time = strptime(data$Date, format = "%m/%d/%y")

## MAKE XTS with custom dates

## make XTS format using time column for the data columns of interest
y = xts(data$Adj.Close, order.by = data[,"Time"])

View(y)

plot.xts(y)


## Compute daily returns using natural log
daily_return = diff(log(y))[-1]

## Plotting ACF and PACF
acf(daily_return)
pacf(daily_return)

## Optimal lag for AR model
T = length(daily_return)
pmax = 12
p = seq(1,pmax,1)
aic = double(length(p))
bic = double(length(p))

for (i in seq(along = p)) {
  k = p[i]
  out = arima(as.ts(y), order = c(k,0,0))
  aic[i] = AIC(out)
  bic[i] = AIC(out, k = log(T))
  
}

select <- cbind(p,aic,bic)
dimnames(select) <- list(NULL, c("lag","AIC","BIC"))
select

## Forecast with AR(10) model
model_a = arima(xts(y),c(10,0,0))
fa_y = forecast(model_a, h = 3)
plot(fa_y, include = 30)

## Optimal lag for MA model
T = length(daily_return)
qmax = 12
q = seq(1,qmax,1)
aic = double(length(q))
bic = double(length(q))

for (i in seq(along = q)) {
  k = q[i]
  out = arima(as.ts(y), order = c(0,0,k))
  aic[i] = AIC(out)
  bic[i] = AIC(out, k = log(T))
  
}

select <- cbind(q,aic,bic)
dimnames(select) <- list(NULL, c("lag","AIC","BIC"))
select

## Forecast with MA(12) model
model_m = arima(y,c(0,0,12))
fm_y = forecast(model_m, h = 3)
plot(fm_y, include = 30)

