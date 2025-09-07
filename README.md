# Notes App



![Notes App GIF](/docs/app_gif.gif)

Aplicación de ejemplo para tomar notas, desarrollada nativamente para Android utilizando tecnologías modernas y buenas prácticas de desarrollo.

## Propósito

Esta aplicación permite a los usuarios crear, ver, editar y organizar notas de texto. Incluye funcionalidades para formato de texto enriquecido y persistencia local de datos.

## Características Principales

*   **Creación y Edición de Notas:** Interfaz intuitiva para escribir y modificar notas.
*   **Listado de Notas:** Visualización de todas las notas guardadas.
*   **Editor de Texto Enriquecido:** Soporte para aplicar formato básico al texto de las notas (negrita, cursiva, etc.), gracias a la librería `richeditor-compose`.
*   **Persistencia Local:** Las notas se guardan en el dispositivo utilizando una base de datos Room.
*   **Navegación Fluida:** Transiciones claras entre la lista de notas y la vista de edición/detalle de una nota individual.
*   **Diseño Moderno:** Interfaz de usuario construida con Jetpack Compose, siguiendo los lineamientos de Material Design y con soporte para *edge-to-edge*.

## Tecnologías y Librerías Utilizadas

*   **Lenguaje de Programación:** [Kotlin](https://kotlinlang.org/)
*   **UI Toolkit:** [Jetpack Compose](https://developer.android.com/jetpack/compose) para una interfaz de usuario declarativa y moderna.
    *   **Material Design 3:** Componentes y tematización.
    *   **Navigation Compose:** Para la gestión de la navegación dentro de la app.
*   **Persistencia de Datos:** [Room Persistence Library](https://developer.android.com/training/data-storage/room) para una base de datos SQLite local robusta.
*   **Inyección de Dependencias:** [Koin](https://insert-koin.io/) para gestionar las dependencias de la aplicación de forma sencilla y eficiente en un entorno Kotlin y Compose.
    *   `koin-android`
    *   `koin-androidx-compose`
    *   `koin-compose-viewmodel`
*   **Procesamiento de Anotaciones:** [KSP (Kotlin Symbol Processing)](https://kotlinlang.org/docs/ksp-overview.html) (utilizado por Room).
*   **Editor de Texto Enriquecido:** [Richeditor Compose](https://github.com/onebone/richeditor-compose) (o la librería específica que estés usando, si el nombre es diferente).
*   **AndroidX Libraries:**
    *   `core-ktx`: Extensiones de Kotlin para las APIs del framework de Android.
    *   `lifecycle-runtime-ktx`: Gestión del ciclo de vida.
    *   `activity-compose`: Integración de Compose en Activities.
*   **Sistema de Compilación:** [Gradle](https://gradle.org/)

## Arquitectura y Buenas Prácticas

*   **Arquitectura Limpia (Clean Architecture) / MVVM:** (Inferido)
    *   **Capa de Presentación (Presentation):** UI (Compose Screens), ViewModels. Interactúa con la capa de dominio.
    *   **Capa de Dominio (Domain):** Contiene la lógica de negocio pura y los casos de uso (Use Cases). Es independiente de Android y de la capa de datos.
    *   **Capa de Datos (Data):** Implementa la lógica de acceso a datos (Repositorios, DAOs, Base de datos Room, APIs).
*   **Single Source of Truth (SSOT):** Para los datos y el estado de la UI.
*   **Inyección de Dependencias:** Mejora la modularidad, el desacoplamiento y la testeabilidad.
*   **Navegación Declarativa:** Uso de `Navigation Compose` para una gestión clara de las rutas y el flujo de la aplicación.
    *   **Rutas Definidas:**
        *   `/notes`: Pantalla principal que muestra la lista de notas.
        *   `/note/{id}`: Pantalla para ver/editar una nota específica.
*   **UI Reactiva:** La interfaz de usuario reacciona a los cambios de estado.
*   **Tematización Personalizada:** Uso de `NotesTheme` para una apariencia consistente.
*   **Soporte Edge-to-Edge:** Para una experiencia de usuario inmersiva.
*   **Estructura de Proyecto Organizada:**
    *   `presentation`: Contiene la UI (Screens, ViewModels, Navigation, Components, Theme).
    *   `domain`: Contiene los casos de uso y modelos de dominio.
    *   `data`: Contiene las fuentes de datos (Database, DAOs, Repositories).
    *   `di`: Módulos de Koin para la inyección de dependencias.
    *   `utils`: Clases de utilidad, como `NavigationRoutes`.

## Cómo Empezar (Desarrollo)

1.  Clona el repositorio.
2.  Abre el proyecto en Android Studio (versión recomendada: [última versión estable]).
3.  Sincroniza el proyecto con los archivos Gradle.
4.  Ejecuta la aplicación en un emulador o dispositivo físico.

