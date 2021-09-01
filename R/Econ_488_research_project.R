library(readr)
library(haven)
library(dplyr)
library(stargazer)
library(car)

data <- read_csv("Downloads/MRW1992data.csv")

delta_gamma = 0.05

data <- data %>%
          mutate(ln_gdp85 = log(rgdpw85),
                 ln_gdp60 = log(rgdpw60),
                 ln_gdpgrowth = ln_gdp85 - ln_gdp60,
                 ln_i_y = log(i_y/100),
                 non_oil = factor(n),
                 intermediate = factor(i),
                 oecd = factor(o),
                 ln_NDG = log((popgrowth/100) + delta_gamma),
                 ln_school = log(school/100)) %>%
            select(country, ln_gdp85, ln_gdp60, ln_i_y, 
                   non_oil, intermediate, oecd,
                   ln_NDG, ln_school, gdpgrowth, ln_gdpgrowth)

non_oil = filter(data, non_oil == 1)
intermediate = filter(data, intermediate == 1)
oecd = filter(data, oecd == 1)

model_N = lm(ln_gdp85 ~ ln_i_y + ln_NDG, data = non_oil)
model_I = lm(ln_gdp85 ~ ln_i_y + ln_NDG, data = intermediate)
model_O = lm(ln_gdp85 ~ ln_i_y + ln_NDG, data = oecd)

stargazer(model_N, model_I, model_O, type = "text",
          column.labels = c("Non-Oil", 
                            "Intermediate", 
                            "OECD"),
          covariate.labels = c("log(I / GDP)", 
                               "log(n+delta+gamma)", 
                               "Constant"), 
          dep.var.labels = "Dependent variable: Log GDP per working-age person in 1985",
          omit.stat = c("f", 
                        "rsq", 
                        "ser"),
          title = "Table 1 - Unrestricted Models",
          no.space = TRUE)

model_N_r = lm(ln_gdp85~I(ln_i_y - ln_NDG), data = non_oil)
model_I_r = lm(ln_gdp85~I(ln_i_y - ln_NDG), data = intermediate)
model_O_r = lm(ln_gdp85~I(ln_i_y - ln_NDG), data = oecd)

stargazer(model_N_r, model_I_r, model_O_r, type = "text",
          column.labels = c("Non-Oil", 
                            "Intermediate", 
                            "OECD"),
          covariate.labels = c("log(I / GDP)- log(n+delta+gamma)", "Constant"), 
          dep.var.labels = "Dependent variable: Log GDP per working-age person in 1985",
          omit.stat = c("f", 
                        "rsq", 
                        "ser"),
          title = "Table 1 - Restricted Models",
          no.space = TRUE)

lh_N = linearHypothesis(model_N, "ln_i_y + ln_NDG = 0")
lh_I = linearHypothesis(model_I, "ln_i_y + ln_NDG = 0")
lh_O = linearHypothesis(model_O, "ln_i_y + ln_NDG = 0")

alpha_N = round(coef(model_N_r)[2] / (1 + coef(model_N_r)[2]), digits = 2)
alpha_I = round(coef(model_I_r)[2] / (1 + coef(model_I_r)[2]), digits = 2)
alpha_O = round(coef(model_O_r)[2] / (1 + coef(model_O_r)[2]), digits = 2)

print(paste("p-value", lh_N[2,6]))
print(paste("p-value", lh_I[2,6]))
print(paste("p-value", lh_O[2,6]))
print(paste("Implied Alpha (Non oil):", alpha_N))
print(paste("Implied Alpha (intermediate):", alpha_I))
print(paste("Implied Alpha (OCED):", alpha_O))

aug_model_N = lm(ln_gdp85 ~ ln_i_y + ln_NDG + ln_school, data = non_oil)
aug_model_I = lm(ln_gdp85 ~ ln_i_y + ln_NDG + ln_school, data = intermediate)
aug_model_O = lm(ln_gdp85 ~ ln_i_y + ln_NDG + ln_school, data = oecd)

stargazer(aug_model_N, aug_model_I, aug_model_O, type = "text",
          column.labels = c("Non-Oil", 
                            "Intermediate", 
                            "OECD"),
          covariate.labels = c("log(I / GDP)", 
                               "log(n+delta+gamma)",
                               "log(school)",
                               "Constant"), 
          dep.var.labels = "Dependent variable: Log GDP per working-age person in 1985",
          omit.stat = c("f", 
                        "rsq", 
                        "ser"),
          title = "Table 2 - Unrestricted Models",
          no.space = TRUE)

aug_model_N_r = lm(ln_gdp85 ~ I(ln_i_y - ln_NDG) + I(ln_school - ln_NDG), data = non_oil)
aug_model_I_r = lm(ln_gdp85 ~ I(ln_i_y - ln_NDG) + I(ln_school - ln_NDG), data = intermediate)
aug_model_O_r = lm(ln_gdp85 ~ I(ln_i_y - ln_NDG) + I(ln_school - ln_NDG), data = oecd)

stargazer(aug_model_N_r, aug_model_I_r, aug_model_O_r, type = "text",
          column.labels = c("Non-Oil", 
                            "Intermediate", 
                            "OECD"),
          covariate.labels = c("log(I / GDP)- log(n+delta+g)", 
                               "log(school)- log(n+delta+g)",
                               "Constant"), 
          dep.var.labels = "Dependent variable: Log GDP per working-age person in 1985",
          omit.stat = c("f", 
                        "rsq", 
                        "ser"),
          title = "Table 2 - Restricted Models",
          no.space = TRUE)

lh_N2 = linearHypothesis(aug_model_N, "ln_i_y + ln_NDG + ln_school = 0")
lh_I2 = linearHypothesis(aug_model_I, "ln_i_y + ln_NDG + ln_school = 0")
lh_O2 = linearHypothesis(aug_model_O, "ln_i_y + ln_NDG + ln_school = 0")

alpha_aug_N = round(coef(aug_model_N_r)[2] / (1 + coef(aug_model_N_r)[2] + coef(aug_model_N_r)[3]), digits = 2)
alpha_aug_I = round(coef(aug_model_I_r)[2] / (1 + coef(aug_model_I_r)[2] + coef(aug_model_I_r)[3]), digits = 2)
alpha_aug_O = round(coef(aug_model_O_r)[2] / (1 + coef(aug_model_O_r)[2] + coef(aug_model_O_r)[3]), digits = 2)

beta_aug_N = round(coef(aug_model_N_r)[3] / (1 + coef(aug_model_N_r)[2] + coef(aug_model_N_r)[3]), digits = 2)
beta_aug_I = round(coef(aug_model_I_r)[3] / (1 + coef(aug_model_I_r)[2] + coef(aug_model_I_r)[3]), digits = 2)
beta_aug_O = round(coef(aug_model_O_r)[3] / (1 + coef(aug_model_O_r)[2] + coef(aug_model_O_r)[3]), digits = 2)

print(paste("p-value", lh_N2[2,6]))
print(paste("p-value", lh_I2[2,6]))
print(paste("p-value", lh_O2[2,6]))
print(paste("Implied Alpha (Non oil):", alpha_aug_N))
print(paste("Implied Alpha (intermediate):", alpha_aug_I))
print(paste("Implied Alpha (OCED):", alpha_aug_O))
print(paste("Implied Beta (Non oil):", beta_aug_N))
print(paste("Implied Beta (intermediate):", beta_aug_I))
print(paste("Implied Beta (OCED):", beta_aug_O))


