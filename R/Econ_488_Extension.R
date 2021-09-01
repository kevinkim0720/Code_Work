library(readr)
library(lmtest)
data_e <- read_csv("Downloads/Extension_Econ488.csv")

delta_gamma = 0.05

railpercapita = data_e$`railway in millions`/data_e$`Population (millions)`

data_e <- data_e %>%
  mutate(ln_gdp2010 = log(rgdpw2010),
         ln_gdp85 = log(rgdpw85),
         ln_gdpgrowth = ln_gdp2010 - ln_gdp85,
         ln_i_r = log(investment_rate/100),
         non_oil = factor(n),
         ln_NDG = log((popgrowth/100) + delta_gamma),
         ln_railway_percapita = log(`railway per capita`)) %>% 
  select(country, ln_gdp2010, ln_gdp85, ln_i_r, 
         non_oil, ln_NDG, ln_railway_percapita, ln_gdpgrowth)

summary(railpercapita)
plot(x = data_e$ln_railway_percapita, y = data_e$ln_gdp2010, main = "Railway Per Capita on GDP per Worker in 2010")

non_oil = filter(data_e, non_oil == 1)

model = lm(ln_gdp2010 ~ ln_i_r +ln_NDG + ln_railway_percapita, data = non_oil)
model_original = lm(ln_gdp2010 ~ ln_i_r + ln_NDG, data = non_oil)

summary(model)
summary(model_original)

waldtest(model, model_original)

implied_alpha = round(coef(model)[2] / (1 + coef(model)[2]), digits = 2)

print(paste("implied alpha", implied_alpha))
