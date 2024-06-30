# Plataforma Educativa

## Descripción del Proyecto

Este proyecto es una **Plataforma Educativa** diseñada para facilitar la gestión de cursos, estudiantes, cuestionarios y profesores. La plataforma permite a los estudiantes inscribirse en cursos, visualizar los detalles de los capítulos de cada curso, realizar cuestionarios y ver sus notas. Los profesores pueden crear cursos y capítulos, gestionar estudiantes inscritos y visualizar sus notas.
##Integrantes Grupo:

Alejandro Paqui, Naomi Lizano, Camila Torres , Sahian Davila Luis Tayupanta
## Tecnologías Utilizadas

- **Java**
- **Vaadin**: Framework para la construcción de interfaces de usuario modernas basadas en web.
- **Spring Boot**: Framework para la creación de aplicaciones Java.
- **MongoDB**: Base de datos NoSQL utilizada para el almacenamiento de datos.
- **Maven**: Herramienta de gestión y construcción de proyectos.

## Características Principales

### Para Estudiantes

- **Inscripción en Cursos**: Los estudiantes pueden buscar e inscribirse en cursos disponibles.
- **Visualización de Cursos Tomados**: Los estudiantes pueden ver una lista de los cursos en los que están inscritos.
- **Realización de Cuestionarios**: Cada capítulo de un curso puede tener un cuestionario asociado que los estudiantes deben completar.
- **Visualización de Notas**: Los estudiantes pueden ver sus notas obtenidas en los cuestionarios.

### Para Profesores

- **Gestión de Cursos**: Los profesores pueden crear, editar y eliminar cursos.
- **Gestión de Capítulos**: Cada curso puede tener múltiples capítulos, los cuales pueden ser gestionados por el profesor.
- **Visualización de Estudiantes Inscritos**: Los profesores pueden ver la lista de estudiantes inscritos en sus cursos y las notas obtenidas por ellos en los cuestionarios.

## Uso de Listas para la Capa de Negocio

Para mantener la integridad y eficiencia de la aplicación, utilizamos listas como capa de negocio para la gestión de datos. Las listas permiten un manejo dinámico y flexible de los datos, lo cual es crucial para las operaciones frecuentes de lectura y escritura en la plataforma.

### Ventajas del Uso de Listas

- **Eficiencia**: Las listas proporcionan una forma rápida y eficiente de gestionar colecciones de datos.
- **Flexibilidad**: Facilita la adición, eliminación y actualización de elementos sin necesidad de redefinir la estructura de datos.
- **Integración**: Permite una fácil integración con otros componentes del sistema, asegurando que los datos se mantengan sincronizados.


## Instalación y Configuración

### Prerrequisitos

- **Java 11** o superior
- **Maven**: Para la construcción y gestión del proyecto.
- **MongoDB**: Base de datos NoSQL.

### Instrucciones

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/alejandro001100/PlataformaEducativaFinal.git
   cd PlataformaEducativaFinal

