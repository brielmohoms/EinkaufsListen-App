# EinkaufsListen-App

### 1. Projektbeschreibung

Dies ist eine Java-basierte Einkaufslisten-Anwendung, die eine SQLite-Datenbank 
verwendet, um Benutzer, Einkaufslisten und Artikel zu verwalten. Die Anwendung 
bietet eine textbasierte Benutzeroberfläche (CLI) und ist plattformunabhängig.

### 2. Features
* Benutzerregistrierung und Anmeldung
* Verwaltung von Einkaufslisten
* Verwaltung von Artikeln innerhalb der Einkaufslisten
* Speicherung der Daten in einer SQLite-Datenbank

### 3. Systemanforderungen
* Java-Version: 21.0.5
* Maven-Version: 3.9.6

### 4. Installation
* Repository klonen: `git clone <repository url>` `cd EinkaufsListen`
* Abhängigkeiten installieren: `mvn clean install`

### 5. Schnellstart
* Befehl: `mvn exec:java "-Dexec.mainClass=org.prog3.app.App"`
* Nach dem Start wird das Hauptmenü angezeigt, wo Benutzer zwischen Anmeldung und Registrierung wählen können.
  
### 6. Anwendungsbeispiele
* Nach dem Start der Anwendung kann sich der Benutzer entweder registrieren oder mit vorhandenen Anmeldedaten einloggen.
* **Neue Einkaufsliste erstellen:** Benutzer gibt den Namen der Einkaufsliste ein, und erhält eine Bestätigungsmeldung.
* **Einkaufsliste verwalten:** Benutzer gibt den Namen einer bestehenden Einkaufsliste ein, um Artikel hinzuzufügen, zu löschen, zu finden, die Menge eines Artikels zu aktualisieren oder alle Artikeln eine bestimmte Einkaufsliste aufzulisten.

### 7. Bekannte Einschränkungen
* Passwörter werden aktuell nicht gehasht.
* Keine GUI, nur textbasierte CLI.

### 8. Weiterentwicklung
* Passwort Hash
* Einführung einer GUI als alternative Benutzeroberfläche.