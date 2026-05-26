# ProjectCalculator

En webapplikation til hurtigt og nemt at oprette projekter og give kunden et estimat på tid og pris.

## Funktionalitet

- Oprette projekter
- Gøre en bruger til projektleder
- Tilføje medlemmer til projektet
- Tilføje delprojekter til et projekt
- Tilføje opgaver til delprojekterne
- Udpege hvem fra projektet der står for hvilken opgave
- Generere et tid og pris estimat automatisk ud fra oprettede opgaverne i et projekt

## Teknologier

- Java 21
- Spring Boot 4.0.6
- Thymeleaf
- MySQL 8

## Installation

```zsh
git clone https://github.com/reza0002/projectCalculator
cd projectCalculator
mvn spring-boot:run
```

Alternativt kan du bruge den medfølgende Maven wrapper:

```zsh
./mvnw spring-boot:run
```

## Brug

1. Åbn en browser
2. Naviger til `localhost:8080/login`

## Bidrag

Se [CONTRIBUTING.md](CONTRIBUTING.md) for mere information.
