# Octoger

Sistema modular web básico para la gestión de mercancia, usuarios y contabilidad en una empresa de retail.

## Indice

- Propósito
- ¿Por qué Octoger?
- Instalación
- Uso
    - Postman
- Créditos

## Propósito

Imagina que eres un emprendedor que tiene o va a fundar una empresa de compra y venta de mercancia. Va a llegar un punto en donde esos documentos de excel y/o libretas físicas en donde llevas la logística y contabilidad serán tan complejos que vas a vivir estresado pensando ¿cómo hacer las cosas más facil?.

Octoger al ser un sistema web puede estar disponible 24/7 para ayudarte a registrar, organizar, contabilizar y predecir hacia donde está yendo tu negocio económicamente.

¿Quieres reportes?, ¿Quieres un sistema de usuarios?, ¿Quieres análisis de logística?. Si la respuesta es sí, entonces Octoger es una buena muy buena opción.

Este sistema está basado en Springboot 3 y Java 17.

## ¿Por qué Octoger?

Existen muy buenos sistemas web gratuitos y accesibles con una gran experiencia y calidad de soporte técnico. Sin embargo, Octoger tiene las siguientes cualidades:

1. Al ser un sistema modular basado en servicios, aumenta la escalabilidad debido a que cada función es totalmente independiente de las demás. Esto evita posibles problemas de disponibilidad y latencia, especialmente en temas de desarrollo como actualizaciones o bug fixes y demandas (peticiones al sistema).
2. El código fuente usado como base para la plataforma final es open source. Es decir, se encuentra abierto al público en este repositorio de github.
3. Al ser open source puede utilizarse como base para crear un sistema más adaptado a tus necesidades, sin necesidad de empezar de 0. De esta forma ahorras tiempo y esfuerzo.

## **Instalación**

Para probar los servicios es necesario:

- Tener una base de datos (se usó Postgres).
- Ejecutar el script sql que tiene las tablas del sistema ubicado en /database
- Tener Java17.
- Desplegar los 4 servicios web.
- Tener una herramienta para probar APIs (se usó Postman).

## **Uso**

Actualmente el sistema está basado en 4 servicios web. Próximamente se incluirá la plataforma.

### Base de Datos

- Nombre de base de datos de prueba: octoger
- Usuario usado: nmatute
- Clave: 12345

### Puertos

- Puerto de user-ws.jar → 9010
- Puerto de type-ws.jar → 9011
- Puerto de product-ws.jar → 9012
- Puerto de accounting-ws.jar → 9013

### Breve explicación de cada JAR
- user-ws.jar es el Web Service para manejar todo lo relacionado con usuarios y seguridad en el sistema.
- type-ws.jar es el Web Service para manejar todo lo relacionado con tipos en el sistema. Los tipos son una forma de clasificar y dar sentido a los datos guardados en la BBDD.
- product-ws.jar es el Web service para manejar todo lo relacionado con productos y paquetes/colecciones de productos en el sistema.
- accounting-ws.jar es el Web Service para manejar todo lo relacionado con operaciones contables en el sistema. Entre esto están las ventas, operaciones de producto (incremento o decremento de mercancía) y transacciones (salida o entrada de dinero).
  

### API

<aside>
💡 Los endpoint que incluyen /public no requieren tokens JWT para acceder.
</aside>

- Instrucciones
  - El script sql ya cuenta con 2 usuarios y 2 tipos creados por defecto.
  - Se debe autenticar un usuario con el endpoint /user/authenticate para obtener un JWT token.
  - Existen endpoints que solo son accesibles para usuarios ADMIN (Tipo USR00), como por ejemplo los endpoint /delete.

[![Run in Postman](https://run.pstmn.io/button.svg)](https://god.gw.postman.com/run-collection/26385543-74f3d062-681a-4d35-ba8c-bf0cf87dc7e8?action=collection%2Ffork&collection-url=entityId%3D26385543-74f3d062-681a-4d35-ba8c-bf0cf87dc7e8%26entityType%3Dcollection%26workspaceId%3D9cb634f8-3cec-4555-bc51-ddc9db87fc14)

## **Creditos**

Este proyecto fue desarrollado únicamente por NM4TT. Tiene muchos detalles que mejorar. Puedes colaborar y hacer sugerencias si lo deseas 🙂.