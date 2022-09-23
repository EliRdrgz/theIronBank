# IRON BANK 2.0 
#### By Elisabeth Rodriguez

## Introducción:

Iron Bank es una aplicación que permite a los usuarios crear cuentas bancarias y realizar transacciones entre ellas.
Está estructurada en diferentes paquetes, cada uno con su funcionalidad específica.

## Paquetes:

### 1. Controller:

Contiene las clases que se encargan de recibir las peticiones del cliente y enviarlas a los servicios correspondientes.

### 2. Model:

Contiene las clases que representan las entidades de la base de datos.

### 3. Repository:

Contiene las interfaces que extienden de JpaRepository, las cuales permiten realizar operaciones CRUD sobre las entidades de la base de datos.

### 4. Service:

Contiene las clases que se encargan de realizar las operaciones necesarias para cada petición del cliente.

### 5. Security:

Contiene las clases que se encargan de la autenticación y autorización de los usuarios.

## 2. FUNCIONALIDADES:

1. Para arrancar el programa es necesario ejecutar desde la terminal el comando para que se inicie Keycloak.
2. Una vez iniciado Keycloak, se debe ejecutar el comando para iniciar la aplicación.
3. A partir de este momento, se puede acceder a la aplicación desde el navegador en la dirección http://localhost:8090.
4. Puede entrar como admin sin necesidad de registrarse, esta funcionalidad simula que el cliente está presente en la oficina bancaria por lo que no de debe de identificar, ya que es el empleado que lo identifica y es éste el que realiza la operación.
5. Como admin puede:
   1. Crear usuarios.
   2. Crear cuentas.
   3. Realizar transferencias.
   4. Chequear el balance de la cuenta bancaria de cualquier usuario.
   5. Poner dinero en la cuenta de ahorro.
6. Como usuario registrado puede:
   1. Realizar transferencias.
   2. Chequear el balance de su cuenta bancaria.
   3. Poner dinero en la cuenta de ahorro.
   4. Retirar dinero de la cuenta de ahorro.
   5. Ver todas sus cuentas de crédito.
   6. Ver todas sus cuentas de ahorro.
   7. Ver todas sus cuentas de crédito.

## 3. DIAGRAMA DE CLASES:

![](src/main/resources/diagram.png)

