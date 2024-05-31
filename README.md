# BONTECH

simple sms service manager

## Features

- **authorization**: we don't have role table because we have two types of person, and because of that i used Enum role
- **SmsService**: this entity have last start time and last end time because when ever admin wants to enable service we should check new times with this times
- **optimistic lock**: for not update in same time on table all tables contains version column


## Getting Started

1. Clone the repository:

```shell
git clone https://github.com/seyyedBagherMusavi/bontech.git
```

2. Navigate to the project directory and run mysql docker:
```shell
cd GeoFind && docker-compose -f mysql.yml up -d
```
3.Build & run project with mvn command or anything else :)


# 💻 Tech Stack:
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MongoDB](https://img.shields.io/badge/MySql-%234ea94b.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
