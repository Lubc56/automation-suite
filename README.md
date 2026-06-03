# 🧪 Automation Suite

Plantilla base de automatización de pruebas en Java que cubre **UI (front-end)** y **API (back-end)** bajo un mismo framework, con reportes unificados a través de Serenity BDD.

---

## 🛠️ Stack tecnológico

| Tecnología | Versión | Rol |
|---|---|---|
| Java | 21 | Lenguaje base |
| Maven | 3.9+ | Gestión de dependencias y ciclo de vida |
| Cucumber | 7.15 | BDD / Gherkin |
| Serenity BDD | 4.1.20 | Orquestación + reportes |
| Selenium | 4.21 (transitivo) | Automatización UI |
| WebDriverManager | 5.8 | Gestión automática de ChromeDriver |
| RestAssured | 5.4 | Pruebas de API REST |
| AssertJ | 3.25 | Aserciones fluentes |

---

## 📁 Estructura del proyecto

```
src/test/
├── java/com/automation/
│   ├── MasterRunner.java              ← Ejecuta todo en paralelo (4 threads)
│   ├── hooks/
│   │   └── Hooks.java                ← Lifecycle @Before/@After por tag
│   ├── ui/
│   │   ├── pages/                    ← Page Objects (LoginPage, InventoryPage…)
│   │   ├── steps/                    ← Step Definitions UI
│   │   └── runners/
│   │       ├── LoginRunner.java      ← Runner aislado @login
│   │       └── ProductRunner.java    ← Runner aislado @product
│   ├── api/
│   │   ├── model/                    ← POJOs de request/response
│   │   ├── steps/                    ← Step Definitions API
│   │   └── runners/
│   │       └── PetstoreRunner.java   ← Runner aislado @petstore
│   └── utils/
│       ├── ApiConfig.java            ← Configuración base RestAssured
│       └── ChromeDriverSource.java   ← Driver provider con WebDriverManager
└── resources/
    ├── serenity.conf                 ← Configuración Serenity + URLs base
    ├── cucumber.properties
    ├── logback-test.xml
    └── features/
        ├── ui/
        │   ├── login.feature
        │   └── product.feature
        └── api/
            └── petstore.feature
```

---

## ⚡ Inicio rápido

### Pre-requisitos

- Java 21+
- Maven 3.9+
- Google Chrome instalado
- Conexión a internet (WebDriverManager descarga el driver automáticamente)

### Clonar la plantilla

```bash
# Usando GitHub Template (recomendado)
gh repo create mi-nuevo-proyecto --template tu-usuario/automation-suite --clone

# O clonando manualmente
git clone https://github.com/tu-usuario/automation-suite.git mi-nuevo-proyecto
cd mi-nuevo-proyecto
```

### Ejecutar todas las pruebas

```bash
mvn verify
```

### Ejecutar por módulo

```bash
# Solo pruebas de login (UI)
mvn verify -Dit.test=LoginRunner

# Solo pruebas de producto (UI)
mvn verify -Dit.test=ProductRunner

# Solo pruebas de API
mvn verify -Dit.test=PetstoreRunner
```

### Ver el reporte Serenity

```bash
# El reporte se genera automáticamente al finalizar mvn verify
open target/site/serenity/index.html       # Mac
start target/site/serenity/index.html      # Windows
xdg-open target/site/serenity/index.html  # Linux
```

---

## ⚙️ Configuración al usar como plantilla

Al crear un nuevo proyecto desde esta plantilla, actualiza los siguientes archivos:

### `pom.xml`
```xml
<groupId>com.tuempresa</groupId>
<artifactId>nombre-del-proyecto</artifactId>
```

### `src/test/resources/serenity.conf`
```hocon
serenity {
  project.name = "Nombre de tu proyecto"
}

environments {
  default {
    base.url = "https://tu-aplicacion.com"
  }
}
```

### `src/test/java/com/automation/utils/ApiConfig.java`
```java
public static final String BASE_URL = "https://tu-api.com";
```

---

## 🔀 Paralelismo

El framework soporta ejecución en paralelo a dos niveles:

| Nivel | Configuración | Archivo |
|---|---|---|
| Scenarios | 4 threads simultáneos | `MasterRunner.java` |
| Forks JVM | 2 forks independientes | `pom.xml` → Failsafe |

Para ajustar el paralelismo:

```java
// MasterRunner.java
@ConfigurationParameter(key = PARALLEL_CONFIG_FIXED_PARALLELISM_PROPERTY_NAME, value = "4")
```

```xml
<!-- pom.xml -->
<forkCount>2</forkCount>
<threadCount>2</threadCount>
```

---

## 🏷️ Etiquetas disponibles

| Tag | Descripción |
|---|---|
| `@ui` | Todas las pruebas de interfaz |
| `@api` | Todas las pruebas de API |
| `@login` | Flujo de autenticación |
| `@product` | Gestión de productos |
| `@petstore` | CRUD Petstore API |

Ejecutar por tag desde Maven:

```bash
mvn verify -Dcucumber.filter.tags="@login"
mvn verify -Dcucumber.filter.tags="@ui and not @product"
```

---

## 📊 Reportes

Serenity genera automáticamente al ejecutar `mvn verify`:

- **Reporte HTML completo** → `target/site/serenity/index.html`
- **Resumen de features** → con estado por escenario (passed / failed / pending)
- **Screenshots** → capturados automáticamente en caso de fallo (UI)
- **Logs de peticiones** → request/response completos en pruebas API

---

## 🌐 URLs de ejemplo incluidas

| Módulo | URL |
|---|---|
| UI | [saucedemo.com](https://www.saucedemo.com) |
| API | [petstore.swagger.io/v2](https://petstore.swagger.io/v2) |

---

## 📝 Convenciones

- Los **Page Objects** se inyectan sin `@Steps` (Serenity los gestiona automáticamente)
- Las **step libraries** reutilizables usan `@Steps`
- Los **features** se organizan por módulo: `features/ui/` y `features/api/`
- Cada módulo tiene su propio **Runner** aislado para poder ejecutarse de forma independiente

---

## 🤝 Contribuir

1. Crea un branch desde `main`: `git checkout -b feat/nueva-funcionalidad`
2. Agrega tus cambios con tests
3. Ejecuta `mvn verify` y valida que el reporte esté en verde
4. Abre un Pull Request
