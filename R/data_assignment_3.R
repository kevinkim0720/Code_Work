library(readr)
campus = read_csv("Downloads/campus.csv")

## set log of crime and log of enrollment
campus$lcrime = log(campus$crime)
campus$lenroll = log(campus$enroll)

## scatterplot of log enrollment and log crime for private and public universities
plot(campus$lenroll[campus$status == "Privat"],campus$lcrime[campus$status == "Privat"], main = "Scatterplot between log enrollment and log crime for Private Universities", xlab = "log enrollment (Private Universities)", ylab = "Log crime")
plot(campus$lenroll[campus$status == "Public"], campus$lcrime[campus$status == "Public"], main = "Scatterplot between log enrollment and log crime for Public Universities", xlab = "log enrollment (Public Universities)", ylab = "Log crime")

## sets private schools to 1 and other to 0
campus$Privat = as.numeric(campus$status == "Privat")

## Level effect: regression model where log crime equals log enrollment + dummy variable
model_level = lm(lcrime~lenroll+Privat, data = campus)
summary(model_level)

## Interaction effect: regression model where log crime equals log enrollment + Privat + Privat*log enrollment
model_full = lm(lcrime~lenroll + Privat*lenroll, data = campus)
summary(model_full)

## Waldtest to see if the full model is the right model
library(lmtest)
waldtest(model_full, model_level)
