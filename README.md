# CorrectAnAddress

CorrectAnAddress este o aplicație în care un utilizator poate introduce o adresă în formatul (Țară, Stat/Județ, Oraș) pentru a fi verificată corectitudinea acesteia.

## Membrii echipei:
* Pogor Ștefan
* Tincu Evelyna

### Contribuții

* Pogor Ștefan: crearea pachetelor services, controllers, beans cu clasele aferente; realizarea de unit tests; creare algoritm ce corectează o adresă introdusă de un utilizator; rest api.
* Tincu Evelyna: crearea pachetelor repositories și models cu clasele aferente; realizare pagini html; realizare design pentru pagini (css); populare json cu țările, statele/județele și orașele respective fiecăreia. 

### Tehnologii folosite 
* Java - limbaj de programare
* PostgreSQL - creare bază de date
* Spring Boot (Spring Web, Spring Data-Jpa) - pentru realizare unui rest api, conectare la baza de date
* HTML - realizarea paginilor web în care utilizatorul introduce datele 
* CSS - definirea unui design plăcut și atractiv
* Thymeleaf - pentru a putea lua informațiile introduse de un utilizator și a le putea folosi mai departe
* AssertJ - pentru realizarea de unit tests (este mai intuitiv)
* Json - pentru stocarea țărilor, statelor/județelor, orașelor într-un format ce ne permite parsarea datelor într-un mod mai ușor

### Baza de date 
Baza de date ce stochează țările, statele/județele, orașele este structurată în felul următor:
* Tabela denumită "country" reține țările și are atributele id (generate de o secvență) și name (nume).
* Tabela "state" stochează statele/județele, are atributele id (generate de o secvență), name (nume) și country_id (fk, referențiază id-ul țării corespunzătoare); este legată de tabela country de o relație one (country) to many (state). 
* Tabela "city" stochează orașele are atributele id (generate de o secvență), name (nume) și state_id (fk, referențiază id-ul statului/județului corespunzător); este legată de tabela state de o relație one (state) to many (city).

### Algoritmul ce corectează o adresă
Logica algoritmului: în primă fază am presupus că câmpul denumit "Oraș" este cel mai important dintre cele trei (Țară, Stat/Județ, Oraș), deoarece orașul este efectiv locul în care un utilizator dorește să ajungă/trimită ceva prin poștă, celelalte două fiind mai generice. În cazul în care orașul a fost introdus greșit sau nu există în baza de date, utilizatorul va primi un mesaj de tipul "Orașul nu există în Baza de Date". Dacă acesta există, se trece la următorul câmp, anume "Stat/Județ" și începe o nouă verificare. Se preia din baza de date foreign key-ul orașului care reprezintă id-ul statului/județului corespunzător și se testeză egalitatea acestora. Dacă sunt egale se trece la câmpul "Țară" și, din nou, se preia foreign key-ul statului, de această dată, care reprezintă id-ul țării. Se testează și aceste două valori, iar dacă sunt egale se va returna un obiect care conține adresa corectă, iar dacă țara nu este corectă se va căuta în baza de date o altă țară al cărei id este egal cu foreign key-ul statului. La fel și în cazul în care statul/județul nu este corect se caută un altul al cărui id este egal cu foreign key-ul orașului.  

### Cum funcționează aplicația web
Utilizatorul este direcționat pe prima pagină, unde este prezent un formular cu câmpurile "Țară", "Stat/Județ", "Oraș" ce trebuiesc completate cu datele corespunzătoare. După introducerea acestor date utilizatorul apasă butonul "Verifică adresa". După apăsarea butonului acesta va fi direcționat către o altă pagină, unde va fi afișată adresa corectată, dacă este cazul. Pe această pagină există un buton "Înapoi" ce îi permite utilizatorului să ajungă la prima pagină pentru a introduce o altă adresă, dacă dorește.
