# Studentenverwaltung

Dieses Projekt ist zur Bearbeitung der JavaFX-Aufgabe (HttpClient) erstellt worden.

## Starten der Anwendung

### Docker

- Das Backend kann über [DockerHub](https://hub.docker.com/r/moinmarcell/studentdb-backend) bezogen werden

### Ansonsten

- Das Projekt forken und in IntelliJ öffnen
- Sollte MongoDb lokal laufen, muss nichts weiter eingestellt werden
- Sollte MongoDb nicht lokal laufen, muss in der Datei `application.properties` die Property `spring.data.mongodb.uri`
  angepasst werden
    - Hierzu kann z.B. ein kostenloser Account bei [MongoDB Atlas](https://www.mongodb.com/cloud/atlas) erstellt werden
    - Nach der Registrierung kann eine kostenlose Datenbank angelegt werden
    - In der Datenbank muss ein User angelegt werden, der Lese- und Schreibrechte hat
    - Die Verbindungs-URI kann dann in der Datei `application.properties` eingetragen werden
- Die Anwendung kann dann gestartet werden

## Übersicht der Endpunkte

- `GET /students` - Gibt alle Studenten zurück
    - Query Parameter:
        - `course` - Filtert die Studenten nach Studiengang
        - `firstName` - Filtert die Studenten nach Vornamen
        - `lastName` - Filtert die Studenten nach Nachnamen
    - Query Parameter können nicht kombiniert werden und sind optional
    - `responseBody`:
      ```json
      [
        {
          "matriculationNumber": "MN-123456",
          "firstName": "Max",
          "lastName": "Mustermann",
          "age": 21,
          "course": "Medieninformatik"
        },
        {
          "matriculationNumber": "MN-654321",
          "firstName": "Erika",
          "lastName": "Mustermann",
          "age": 20,
          "course": "Medieninformatik"
        }
      ]
      ```
- `POST /students` - Fügt einen neuen Studenten hinzu
    - Request Body: `{"firstName": "Max", "lastName": "Mustermann", "age": 21, "course": "Medieninformatik"}`
    - Status Code: `201 Created`
    - `responseBody`:
        ```json
        {
        "matriculationNumber": "MN-123456",
        "firstName": "Max",
        "lastName": "Mustermann",
        "age": 21,
        "course": "Medieninformatik"
        }
        ```
- `DELETE /students/{matriculationNumber}` - Löscht einen Studenten anhand der Matrikelnummer
    - Status Code: `204 No Content`
- `PUT /students/{matriculationNumber}` - Aktualisiert einen Studenten anhand der Matrikelnummer
    - Request Body: `{"firstName": "Max", "lastName": "Mustermann", "age": 21, "course": "Medieninformatik"}`
    - Status Code: `200 OK`
    - `responseBody`:
        ```json
        {
        "matriculationNumber": "MN-123456",
        "firstName": "Max",
        "lastName": "Mustermann",
        "age": 21,
        "course": "Medieninformatik"
        }
        ```
- `GET /students/{matriculationNumber}` - Gibt einen Studenten anhand der Matrikelnummer zurück
    - `responseBody`:
        ```json
        {
        "matriculationNumber": "MN-123456",
        "firstName": "Max",
        "lastName": "Mustermann",
        "age": 21,
        "course": "Medieninformatik"
        }
        ```
- `GET /students/search` - Gibt den ersten Studenten zurück, der den Suchparametern entspricht
    - Query Parameter:
        - `firstName`
        - `lastName`
    - Query Parameter sind pflicht
    - Beispiel: `GET /students/search?firstName=Max&lastName=Mustermann`
        - `responseBody`:
            ```json
              {
                "matriculationNumber": "MN-123456",
                "firstName": "Max",
                "lastName": "Mustermann",
                "age": 21,
                "course": "Medieninformatik"
              }
            ```
- Wenn ein einzelner Student nicht gefunden wird, wird ein `404 Not Found` zurückgegeben
- `firstName` und `lastName` sind Pflichtfelder und müssen mindestens 2 Zeichen lang sein und dürfen nur aus Buchstaben
  bestehen
- `age` ist ein Pflichtfeld und muss zwischen 1 und 99 liegen
- `course` ist ein Pflichtfeld und darf nur Buchstaben und Zahlen enthalten