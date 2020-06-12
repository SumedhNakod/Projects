library(ggplot2)
library(ggthemes)
library(dplyr)
library(lubridate)
library(tidyr)
library(DT)
library(scales)

#color vectors to be used for plotting
colors = c("#CC1011", "#665555", "#05a399", "#cfcaca",
           "#f5e840", "#0683c9", "#e075b0")

#DataFrames
apr_data <- read.csv("uber-raw-data-apr14.csv")
may_data <- read.csv("uber-raw-data-may14.csv")
jun_data <- read.csv("uber-raw-data-jun14.csv")
jul_data <- read.csv("uber-raw-data-jul14.csv")
aug_data <- read.csv("uber-raw-data-aug14.csv")
sep_data <- read.csv("uber-raw-data-sep14.csv")

#Combine all DataFrames
data_2014 <- rbind(apr_data,may_data,jun_data,jul_data,aug_data,sep_data)

#formatting Dates and Time column

data_2014$Date.Time <- as.POSIXct(data_2014$Date.Time ,
                                  format ="%m/%d/%Y %H:%M:%S" )
data_2014$Time <- format(as.POSIXct(data_2014$Date.Time,
                                    format ="%m/%d/%Y %H:%M:%S"),
                                    format = "%H:%M:%S")

data_2014$Date.Time <- ymd_hms(data_2014$Date.Time)

data_2014$Day <- factor(day(data_2014$Date.Time))
data_2014$Month <- factor(month(data_2014$Date.Time,label = TRUE))
data_2014$Year <- factor(year(data_2014$Date.Time))
data_2014$Dayofweek <- factor(wday(data_2014$Date.Time, label = TRUE))

data_2014$hour <- factor(hour(hms(data_2014$Time)))
data_2014$minute <- factor(minute(hms(data_2014$Time)))
data_2014$second <- factor(second(hms(data_2014$Time)))

#visualize by hour data
hour_data <- data_2014 %>% group_by(hour) %>% dplyr::summarize(Total = n())
datatable(hour_data)

#plotting
ggplot(hour_data, aes(hour,Total))+
  geom_bar(stat = "identity" , fill = "steelblue",color = "red")+
  ggtitle("Trip every hour")+
  theme(legend.position = "none")+
  scale_y_continuous(labels = comma)

#data by hour,month
month_hour <- data_2014 %>% group_by(Month,hour) %>% dplyr::summarise(Total = n())
datatable(month_hour)


ggplot(month_hour, aes(x=hour, y=Total, fill = Month))+
  geom_bar(stat = "identity")+
  ggtitle("Trips by Hour and Month")+
  scale_y_continuous(labels = comma)

#plotting by every day of month

day_hour <- data_2014 %>% group_by(Day) %>% dplyr::summarise(Total =n())
datatable(day_hour)

ggplot(day_hour,aes(Day,Total))+
  geom_bar(stat = "identity", fill= "steelblue")+
  ggtitle("Trips by day")+
  scale_y_continuous(labels = comma)

#plot by months and days
day_month_group <- data_2014 %>% group_by(Month, Dayofweek) %>%dplyr::summarize(Total = n())
datatable(day_month_group)

ggplot(day_month_group,aes(x=Month,y=Total,fill=Dayofweek))+
  geom_bar(stat = "identity",position = position_dodge())+
  ggtitle("Trips by month and day")+
  scale_y_continuous(labels=comma)+
  scale_fill_manual(values = colors)







