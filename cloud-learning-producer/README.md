# Cloud Learning Platform

Aplicación desarrollada con Spring Boot para la gestión de cursos e inscripciones en una plataforma educativa virtual.

El proyecto implementa:

* API REST con Spring Boot.
* Persistencia en Oracle Database.
* Dockerización de la aplicación.
* Automatización CI/CD con GitHub Actions.
* Despliegue en AWS EC2.

## Tecnologías utilizadas

* Java 22
* Spring Boot 3
* Spring Data JPA
* Maven
* Oracle Database
* Docker
* GitHub Actions
* AWS EC2

## Endpoints principales
Cursos:

| Método|	Endpoint|
|---|---|
| GET|	/api/cursos|
| GET|	/api/cursos/{id}|
| POST|	/api/cursos|
| PUT|	/api/cursos/{id}|
| DELETE|	/api/cursos/{id}|

Usuarios:

| Método| 	Endpoint |
|---|---|
| GET| 	/api/usuarios |
| GET	| /api/usuarios/{id} |
| POST	| /api/usuarios |
| PUT	| /api/usuarios/{id} |
| DELETE| 	/api/usuarios/{id} |

Inscripciones

| Método | Endpoint |
|--------|----------|
| GET    | /api/inscripciones/curso/{id} |
| GET    | /api/inscripciones/estudiante/{id} |
| POST   | /api/inscripciones |
| DELETE | /api/inscripciones/{id} |

Evaluaciones

 | Método |	Endpoint | 
 |--------|----------|
 | GET |	/api/evaluaciones | 
 | GET |	/api/evaluaciones/{id} | 
 | GET |	/api/evaluaciones/curso/{id} | 
 | POST |	/api/evaluaciones | 
 | PUT |	/api/evaluaciones/{id} | 
 | DELETE |	/api/evaluaciones/{id} | 

## Ejemplo de respuesta

Endpoint:
```bash
GET /api/inscripciones/estudiante/3
```

Respuesta:
```json
{
"estudiante": "Pedro Gomez",
"cursos": [
{
"nombre": "Spring Boot",
"costo": 40000.0
},
{
"nombre": "Base de Datos",
"costo": 65000.0
}
],
"total": 105000.0
}
```

## Ejecución local

Clonar repositorio
```bash
git clone https://github.com/CristianJTM/Exp1_S1_Grupo14_DESARROLLO-CLOUD-NATIVE_002A.git
```

Ejecutar aplicación
```bash
./mvnw spring-boot:run
```

## Docker

Construir imagen:

```bash
docker build -t cloud-learning-platform .
```

Ejecutar contenedor:

```bash
docker run -p 8080:8080 cloud-learning-platform
```