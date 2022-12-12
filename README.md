# Aplikacja do zapisu studentów na zajęcia
projekt z baz danych na IV semestrze

## Opis
Aplikacja okienkowa umożliwiająca studentom zapisy na zajęcia. ( Technologie:
PostgreSql, Hibernate, Swing). Student, po zalogowaniu się do aplikacji będzie
mógł zapisać się na zajęcia przypisane do swojego wydziału i semestru, a
także zobaczyć zajęcia, na które już się zapisał. Aplikacja uniemożliwi zapis na
zajęcia, których termin będzie kolidował z innymi zajęciami, na zajęcia, które
nie mają wolnych miejsc oraz w przypadku gdy student przekroczy
maksymalną ilość punktów ECTS. Po kliknięciu na konkretne zajęcia,
studentowi ukażą się szczegółowe informacje.

## Schemat bazy:
<p align="center">
  <img src="https://user-images.githubusercontent.com/80721230/190899592-76a332f7-3cd8-4adb-97f8-cd8a3cbca334.png">
</p>

## Opis tabel:
- Students – zawiera podstawowe informacje o studencie, kluczem
głównym jest indeks
- Lecturers – zawiera informacje o wykładowcach prowadzących zajęcia.
Informacje te pokazywane będą po wejściu przez studenta w dany kurs.
- Courses – zawiera informacje o zajęciach takie jak nazwa, ilość wolnych
miejsc, liczba punktów ECTS, opis, termin
- Faculties – zawiera nazwy wydziałów
- Students_Courses – zawiera informację o tym, jaki student jest zapisany
na jakie zajęcia
- Students_Faculties – zawiera informację na jakich wydziałach studiuje
student (student może studiować na wielu wydziałach)






## Jak pobrać:
1. klonujemy repozytorium:
```
git clone https://github.com/pwgacek/projekt-bd2.git
```
2. pobieramy baze z dockera i odpalamy ją na porcie np 5433
```
docker pull pwgacek/db_project
docker run --name db_container --env PGDATA=postgres -d -p 5433:5432 -i pwgacek/db_project
```

3. łączymy się z bazą na wskazanym porcie </br>
hasło: postgres, login : postgres, nazwa bazy : enroll_database </br>

4. w jednej z klas hibernate'a po najechaniu na błąd wybieramy opcje Assign Data Source -> enroll_database </br>

5. odpalamy projekt, przykladowe loginy 100123, 100124, 100128 </br>



