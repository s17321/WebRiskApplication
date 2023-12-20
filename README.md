Wybrałem spring boota ponieważ wydawał mi się najprostrzy w konfiguracji i obsłudze.  
Te aplikacje buduje się podobnie jak aplikacje baz danych w .NET - przynajmniej tak z założenia. 
Miałem takie zajęcia na uczelni i dlatego było mi najłatwiej się do tego odnieść.

Wydaje mi się że dla takiego projektu aplikacja w RESTowym stylu ma dodatkowe korzyści jak łatwość w testowaniu,
Automatyczne tworzenie dokumentacji API, było mi też łatwiej zrozumieć co robię i z jakim endpointem.
Później też w aplikacji wiedziałem które elementy poprawić dla jakiego endpointu aby poprawić działanie aplikacji.

Na wstępie napiszę że nie udało mi się wykonać zadania. 
Nie udało mi się dobić do tego api Web Risk API. Mimo że utworzyłem swój klucz oraz dodałem dane billingowe. 
Wydaje mi się, że cała logika podejścia do zadania jest jednak widoczna w mojej pracy, dlatego zdecydowałem się oddać zadanie.

w założeniach był podany format w jakim operator przechowuje pliki json. Utworzyłem więc na tą podobizne klasy modelowe
np. SMSData. w której mamy sendera, recipienta oraz message jako parametry. 

Następnie w metodzie SmsService obiekt klasy SMSData przyjmuje jako parametr i getMessage wyciągam wiadomość wysłaną do klienta.
A następnie przekazuje do atrybutu 'url' który korzysta z publicznej metody z klasy UrlExtractor i regexem wyciągam url'a.

utworzyłem klase modelową EvaluateRiskRequest i obiekt tej klasy - zgodny z dokumentacją API próbowałem wysłać,
Na adres https://webrisk.googleapis.com/v1eap1:evaluateUri.
Niestety to się nie udało. 

Dalej przygotowałem klasę modelową EvaluateUriResponse, która zgodnie z api miała przyjmować odpowiedź.
Po poprawnym wykonaniu sprawdzenia czy otrzymany url był phishingiem klient miał być chargeowany o 2.0 zł
Aktualizowany jest plik Subscribers.csv.
Subscriber to też klasa modelowa i przyjmuje parametry: phoneNumber, isSubscribed, balance, operationValue.
Myślę że oczywiste jest że w takiej sytuacji najlepiej było by modyfikować dane w bazie danych. 
Jednak na potrzeby tego zadania wykorzystałem plik csv. Klasa Subscriber jest Serializowana aby zachować ciągłość 
oraz spójność danych, również po wyłączeniu aplikacji. 

Dodałem jeszcze sms-controller 
endpoint /reciveSMS służy do subskrybowania lub wypisania się w zależności od podania wartości w polu context.
SMS doda numer do pliku Subscribers.csv, o ile już na liście nie jest oraz przestawi flagę isSubscriber na true.
Jeśli jednak numer wyśle sms o treści STOP to flaga zostanie przestawiona na false.


metoda GET /subscriber/{phoneNumber} sprawdza czy dany numer jest subscriberem.