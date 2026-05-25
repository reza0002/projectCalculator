# Bidrag til ProjectCalculator

Hvis du gerne vil bidrage til projektet, skal du følge de nedenstående retningslinjer

---

## Teknologier

| Lag                 | Teknologi          |
|---------------------|--------------------|
| Sprog               | Java 21            |
| Framework           | Spring Boot 4.0.6  |
| Template engine     | Thymeleaf          |
| Database            | MySQL 8            |
| Database access     | JDBCTemplate       |
| Build system        | Maven              |
| Test frameworks     | JUnit, MockMvc, H2 |
| Statisk kodeanalyse | Qodana             |

Valg af teknologi afhænger ikke af, hvor nyt noget er, men hvor stabilt og robust det er.

Der må ikke tilføjes nye teknologier til programmet uden at have henvendt sig til en
maintainer/developer først og fået lov.

---

## Kom i gang

For at bidrage meningsfuldt skal du sørge for at have programmet kørende lokalt først
Se [README.md](README.md) for hjælp til denne proces.

---

## Kodningsregler

### Generelt

- Følg de etablerede konventioner for Java, SQL, HTML, CSS osv.
- Brug meningsfulde og beskrivende navne
- Ingen hardcodede værdier
- Refaktorer ikke andres kode uden aftale

### Database

- Brug altid `JDBCTemplate` til at interagere
- Brug aldrig string-konkatenering til SQL

Eksempel på korrekt brug af JDBCTemplate:

```java
// Brug (?) som value, så lader jdbcTemplate dig erstatte den med parameteren
jdbcTemplate.update("INSERT INTO projects (name) VALUES (?)",name);
```

### Test

- Brug JUnit til unit tests
- Brug MockMvc til controllertests og web slice-tests
- Brug H2 til integrationstests

---

## Branching-strategi

| Branch      | Formål                                |
|-------------|---------------------------------------|
| `main`      | Stabil, produktionsklar kode          |
| `prototype` | Aktiv udvikling / ustabil prototype   |
| `feature/*` | Nye features (f.eks. `feature/login`) |

---

## Workflow

1. Fork repository
2. Opret en branch ud fra `prototype`
3. Lav dine ændringer
4. Commit med en kort, beskrivende besked
5. Push din branch
6. Opret en Pull Request til `prototype`

---

## Pull Requests

For at få dine ændringer godkendt, skal der være lavet meningsfulde tests for al den funktionalitet
du vil tilføje.

Når du åbner en Pull Request, skal du:

- Beskrive dine ændringer
- Linke til det relevante issue, hvis der er nogen
- Vente på review og godkendelse inden merge

Pull Requests merges ikke uden review.
