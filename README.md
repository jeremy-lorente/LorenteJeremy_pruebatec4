# LorenteJeremy_pruebaTec4

 ## Objetivo
> [!NOTE]
> El objetivo de este desafío es aplicar los contenidos dados hasta el momento durante el BOOTCAMP (Git, Java, Spring Boot, Testing, JPA + Hibernate, Spring Security y JWT) en la implementación de una API REST a partir de un enunciado propuesto, una especificación de requisitos técnico-funcionales y documentación anexada.


## Iniciación proyecto
> [!IMPORTANT]
> - Clonar repositorio
> - Abrir en Intellij
> - Abrir gestor de base de Datos (XAMPP en mi caso)
> - Tener descargado apache-tomcat 9.0.85 (Versiones inferiores podrian provocar fallos)
> - Crear la base de datos con el SQL facilitado en: [Abrir documento](https://github.com/jeremy-lorente/LorenteJeremy_pruebatec4/blob/main/src/main/resources/booking.sql)
> - Para testear se facilita una coleccion de postman con las "Story" de usuario y diversos endpoints en:  [Abrir Documento](https://github.com/jeremy-lorente/LorenteJeremy_pruebatec4/blob/main/src/main/resources/pruebaTec4.postman_collection.json)
> - Ejecutar "LorenteJeremyPruebatec4Application.java"

## Usos
> [!NOTE]
> - Ejecute el programa y acceder a los distintos Endpoints de la aplicacion atrabes de la coleccion de Postman facilitada:
>    - Flight:
>      - Permite las operaciones crud de la entidad Flight, algunas restringidas por "Basic Auth", estas ya se encuentran configuradas
>    - Hotel:
>      - Permite las operaciones crud de la entidad Hotel, algunas restringidas por "Basic Auth", estas ya se encuentran configuradas
>    - Room:
>      - Permite las operaciones crud de la entidad Room, algunas restringidas por "Basic Auth", estas ya se encuentran configuradas
>    - Person:
>      - Permite las operaciones crud de la entidad Person, algunas restringidas por "Basic Auth", estas ya se encuentran configuradas
>    - RoomBooking:
>      - Permite las operaciones crud de la entidad RoomBooking, algunas restringidas por "Basic Auth", estas ya se encuentran configuradas
>    - FlightBooking:
>      - Permite las operaciones crud de la entidad FlightBooking, algunas restringidas por "Basic Auth", estas ya se encuentran configuradas     
>    - Story:
>      - Se compone por un total de 7 Storys, que permiten hacer el testeo de historias propuestas por esta prueba
>      - La "Story n7" se compone por dos subcarpetas:
>        - Path Hotel: Permite operaciones crud sobre la entidad Hotel con las restricciones solicitadas para esta Story
>        - Path Flight: Permite operaciones crud sobre la entidad Flight con las restricciones solicitadas para esta Story


## Casos Supuestos
> [!WARNING]
> - Se ha añadido la entidad Persona:
>   - Se ha añadido la entidad Persona, sera necesaria crear una persona mediante el correspondiente endpoint antes de poder realizar cualquier tipo de reserva, en caso de no existir alguna de las personas de la reserva, esta no podra crearse 
> - Se ha añadido la entidad Room:
>   - Se ha añadido la entidad Room, para facilitar el manejo de reservas por Hotel, el hotel estara abierto durante todo el año y seran las habitaciones las que tendran unas fechas concretas en las que se podran reservar.
>   - Se establece la propiedad "availableRoom", esta propiedad se setea en la creacion de una habitacion, en caso de setearse como "False", la habitacion no podra ser reservada ya que se entiende que no esta operativa por temas de reparacion, reformas, etc... 
> - Precio asiento Flight:
>   - Al registrar un Vuelo/Flight, se seteara un precio para el asiento, este se considerara el precio del asiento estandard, en caso de que el tipo de asiento sea "business" a la hora de ralizar una reserva, este tendra un incremento, siendo el doble del precio inicial. 
> - Precio habitacion Room:
>   - Al registrar una Habitacion/Room, se seteara un precio por noche, este precio se multiplicara por el numero de noches que se hospeden las personas.

## Documentación 
> [!NOTE]
> Se incluye documentacion con Swagger una vez arrancada la aplicacion en este endpoint: http://localhost:8080/doc/swagger-ui.html
> 
> Todos los endpoint utilizados se encuentran en la coleccion de postman: [Abrir Documento](https://github.com/jeremy-lorente/LorenteJeremy_pruebatec4/blob/main/src/main/resources/pruebaTec4.postman_collection.json)
>
> Los test realizados se encuentran en la siguiente ubicacion: [Abrir Documento](https://github.com/jeremy-lorente/LorenteJeremy_pruebatec4/tree/main/src/test/java/com/lorente/jeremy/LorenteJeremy_pruebatec4)

