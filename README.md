
Requerimientos para el despliegue:

Spring boot 2.0.2 o superior
Java 8 o superior

Se puede ejecutar desde maven con:
	Despliegue: mvn spring-boot:run
	Pruebas: mvn test -Dtest=NodeTests


Luego de desplegado, el API puede ser accedido desde la siguiente direccion:

http://localhost:8080/masv-asset

	Metodo GET: Lista todo el arbol
	Metodo POST: Crea un nodo en el arbol.
		Recibe lo siguiente:
		{
			"info": <valor>
		}
	
http://localhost:8080/masv-asset/nodes

	Metodo POST: Crea nodos a partir de un array
		Ejemplo
		[58,23,45,26,75,96]
	Metodo DELETE: Limpia el arbol.
	
http://localhost:8080/masv-asset/common-ancestor

		Metodo GET: Devuelve el ancestro comun de dos valores
			Recibe dos parametros por request
			Ejemplo http://localhost:8080/masv-asset/common-ancestor?nodeA=5&nodeB=8
	
	