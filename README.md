# Aplikacja vKalkulator
Zaliczenie z przedmiotu Mobilne aplikacje multimedialne

# Wymagania dotyczące aplikacji
1. Aplikacja musi mieć rozpoznawanie mowy.
2. Aplikacja musi mieć pobieranie obrazów z sieci
3. Aplikacja musi mieć pobieranie dźwięku z sieci

# Co zostało zrobione
Dodałem rozpoznawanie mowy - działa w języku angielskim.
Dodałem wstępny interfejs aplikacji.

Do przetestowania trzeba włączyć mikrofon w emulatorze oraz udzielić dostępu aplikacji (pojawi się monit podczas jej uruchomienia)
![](https://projekty.azurewebsites.net/screens/vKalkulator/screenshot_1.png)

**Logika**

Aby uruchomić rozpoznawanie mowy trzeba kliknąć w klawisz mikrofonu, wypowiedzieć działanie, kliknąć ponownie klawisz mikrofonu, np:
1. klkamy przycisk mikrofonu 
2. mówimy 5+10 (five plus ten)
3. klikamy przycisk mikrofonu

**Tak to teraz działa**

Opis znajduje się w MainActivity.java.
1. przetworzony tekst jest rozbijany po spacjach np. (5 + 2) ***String[] dane = data.get(0).split("\\s+")***;
2. rozbity ciąg znaków jest pakowany do tablicy ***dane***

***System.out.println(dane[0]); // pierwsza cyfra/liczba***

***System.out.println(dane[1]); // znak operacji***

***System.out.println(dane[2]); // druga cyfra/liczba***

3. dane są przypisywane do zmiennych i na podstawie znaku operacji wykonywane jest działanie

Aktualnie działa to w w oparciu o jedno działanie np. (1+1), a nie (1+1*3,itd)- do zmiany jak wszystkie kryteria co do aplikacji będą spełnione.

# Do zrobienia
1. dodać np. obrazki, które będą pobierane z sieci jako obraz do klawiszy (np. klawisz 1, będzie miał pobrany obrazek z sieci, itd..).
2. dodać muzykę z sieci np. podczas kliknięcia.
3. oprogramować sam kalkulator
4. zwiększyć ilość wykonywanych działań podczas rozpoznawania mowy (jeśli nie będzie to problematyczne)
5. dodać obsługę wyjątku, gdy tekst nie zostanie poprawnie rozpoznany (obecnie apka się zamknie, tylko dlatego, że nie wpisywałem takiego warunku ze względu na pkt.4).
6. można zrobić aby nasłuchiwanie wyłączyło się automatycznie (do przetestowania po zrobieniu wszystkich powyżych punktów)



