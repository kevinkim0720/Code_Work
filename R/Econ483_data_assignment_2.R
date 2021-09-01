library(readr)
library(forecast)

## Create a data object frame called pcdgcc96 that imports the csv file with data
pcdgcc96 = read_csv("Downloads/PCDGCC96.csv")

## Time series plot
y = ts(pcdgcc96$PCDGCC96, start=c(2002,1), end=c(2020,1), frequency=4)

## Holt-Winter's exponential smoothing
holt_exp = HoltWinters(y, beta = TRUE, gamma = FALSE)

## Creating forecast objects for next 8 quarters
f_exp = forecast(holt_exp, h = 8)

## Generate time as an independent variable
t = seq(1, length(y), by = 1)
n = length(t)

## maximum order of polynomial in time
qmax = 4
q = seq(1, qmax, 1)
aic = double(length(q))
bic = double(length(q))

for (i in seq(along = q)) {
  k = q[i]
  out = lm(y ~ poly(t, k, raw = T))
  aic[i] = AIC(out)
  bic[i] = AIC(out, k = log(n))
  
}

## Finding the optimal degree
select <- cbind(q, aic, bic)
dimnames(select) <- list(NULL, c("order", "AIC", "BIC"))

## Final model has 4th order time polynomial
final = tslm(y ~ trend + I(trend^2) + I(trend^3) + I(trend^4))

## Creating forecast objects for next 8 quarters
fcast = forecast(final, h = 8)

## Find RMSE for each equation
accuracy(f_exp)
accuracy(fcast)

## Find Residuals for both equations
e_a = holt_exp$x - holt_exp$fitted
e_b = final$residuals

## Diebold-Mariano Test
dm.test(e_a, e_b, alternative = "less")

