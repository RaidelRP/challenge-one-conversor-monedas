# challenge-one-conversor-monedas

Aplicación en Java para realizar cambios de moneda mediante el uso de la API de ExchangeRate

## Principales funcionalidades

La aplicación presenta un menú que permite al usuario introducir un número para seleccionar la acción que quiera
realizar. Las opciones son las siguientes:

1. Consultar un cambio
2. Consultar listado de monedas disponibles
3. Agregar nueva moneda para consultar cambio
4. Ver historial de conversiones

Además se muestra la opción 0 para finalizar el programa.

## Estructura del proyecto

El proyecto consta de la clase `Principal`, junto con `Monedas`, `Consulta` y el registro `Cambio`, que son de utilidad
para realizar las operaciones deseadas.

### Clase `Monedas`

Tiene una lista con valores iniciales de códigos de moneda para realizar conversión, incluyendo dólares, pesos chilenos,
argentinos, bolivianos, colombianos y reales brasileños. Los valores se agregan a la lista en el constructor

Es posible acceder a cada uno de los códigos y el número que los identifica en la lista medinte la
función `listarMonedas`

Para ver a qué moneda se asocia un índice de la lista, se incluye la función `obtenerMoneda`, pasándole como parámetro
un entero que es la posición o índice de la lista

La función `agregarMoneda` permite incluir nuevos códigos de moneda para consultar. Se le pasa como parámetro una cadena
que será el código de la nueva moneda.

### Clase `Consulta`

Se construye recibiendo los códigos de moneda que formarán parte de una conversión, se accede a la API mediante la key
que es obtenida desde `config.propeties` y se configuran las instancias de clases (`HttpClient`, `HttpRequest`
y `HttpResponse`) para realizar la consulta

Se incluye la función `realizarConsulta` que obtiene un objeto de tipo `Cambio` mediante la librería `Gson` que accede
al cuerpo de la respuesta HTTP

### Registro `Cambio`

Clase creada para almacenar los resultados de una consulta. Posee los atributos `result`, `base_code`, `target_code`
y `conversion_rate`

Incluye la función `convertir` que devuelve el producto de una cantidad pasada como parámetro por la tasa de conversión.

La función `resultadoDeConversion` formatea una cadena con información amigable al usuario del resultado de un cambio
entre monedas. Ejemplo de la ejecución de esta
función: `1,00 USD -> 1113,58 ARS. Tasa de conversión :  1 USD -> 1113,58 ARS`

## Funcionalidades de la clase `Principal`

Al ejecutar el programa, se muestra el menú con las posibles opciones a realizar

````chatinput
**********************************
Bienvenido al Conversor de monedas
**********************************
Digite una opción para continuar:
1. Consultar un cambio
2. Consultar listado de monedas disponibles
3. Agregar nueva moneda para consultar cambio
4. Ver historial de conversiones
0. Salir
````

El usuario interactúa introduciendo valores y la consola va mostrando los resultados de sus consultas

### 1. Consultar un cambio

Se muestran las opciones disponibles para seleccionar moneda de origen y destino para convertir, seguido de la cantidad.
Se muestra el resultado de la conversión e independientemente al usuario se agrega como contenido al archivo de
historial que se encuentra en la carpeta del proyecto. Al finalizar las operaciones se vuelve a mostrar el menú

````chatinput
Seleccione la moneda que quiere convertir: 
Esta es la lista de las monedas que se pueden elegir.
Digite el número correpondiente
1 -> USD
2 -> CLP
3 -> ARS
4 -> BOB
5 -> BRL
6 -> COP
1

Seleccione la moneda que quiere obtener: 
Esta es la lista de las monedas que se pueden elegir.
Digite el número correpondiente
1 -> USD
2 -> CLP
3 -> ARS
4 -> BOB
5 -> BRL
6 -> COP
2

Introduzca la cantidad de USD a convertir: 
1

1,00 USD -> 936,43 CLP. Tasa de conversión :  1 USD -> 936,43 CLP
````

### 2. Consultar listado de monedas disponibles

Esta opción hace uso de la función `listarMonedas` de la clase `Monedas` para recordarle al usuario cúales son las
monedas que puede consultar

````chatinput
Esta es la lista de las monedas disponibles.
1 -> USD
2 -> CLP
3 -> ARS
4 -> BOB
5 -> BRL
6 -> COP
````

### 3. Agregar nueva moneda para consultar cambio

El usuario ingresa el nuevo código de moneda y se le muestra la lista actualizada de opciones disponibles

````chatinput
Introduzca el código de la nueva moneda a convertir: 
MXN
**********************************
Esta es la lista de las monedas disponibles.
1 -> USD
2 -> CLP
3 -> ARS
4 -> BOB
5 -> BRL
6 -> COP
7 -> MXN
````

### 4. Ver historial de conversiones

El programa lee del archivo de historial todas las líneas que este tiene para mostrarlas en pantalla

````chatinput
Historial de conversiones
10-05-2025 15:48 => 1,00 USD -> 1113,58 ARS. Tasa de conversión :  1 USD -> 1113,58 ARS
13-05-2025 10:50 => 1,00 USD -> 936,43 CLP. Tasa de conversión :  1 USD -> 936,43 CLP
13-05-2025 11:17 => 1000,00 CLP -> 20,84 MXN. Tasa de conversión :  1 CLP -> 0,02 MXN
````

## Tratamiento de excepciones

En varias funcionalidades se hace uso de bloques `try/catch` para manejar excepciones relacionadas con su desempeño. Las
excepciones tratadas son:

* `InputMismatchException`, para entrada de datos en tipo o formato que no coincide con el esperado
* `IOException`, para errores al manipular o acceder a archivos
* `IndexOutOfBoundsException`, para índices en listas fuera de los límites

## Conexión mediante API

Para acceder a la API de ExchangeRate es necesario crear un archivo `config.properties` que contenga una clave
llamada `API_KEY` con el valor personal de la clave de la API de ExchangeRate

````properties
API_KEY=tuAPIkey1234456
````