# Heading level 1 Dummy Data Creator Programa

El programa es utilizado para la creación automatizada de dummy data. El programa reproduce data en siguiente formato:
	variables independientes = Numéricas
	variable dependiente = clarificativa (binaria)

El programa permite a los usuarios seleccionar la cantidad de variables independientes como también sus rangos correspondientes y la importancia que cada variable juega en el resultado.

La data creada no es randomized si no que que cada variable es asignada una importancia hacia el resultado final. De ser así, esta data representa un patron el cual puede ser luego descifrado por distintos algoritmos de machine learning/Artificial Intelligence, como una red artificial de neuronas.   

# Heading level 1 Como Utilizar el Programa

La carpeta ademas de este archivo (readme) contiene el programa(DummyDataAlgo.java) y dos archivos de formato csv(dataSettings y dummyData).

La producción de data en este programa esta divido en tres etapas:
	Primera etapa: Configurar los settings del programa.
	Segunda etapa:  Correr el programa.
	Tercer etapa:  Obtener resultaos.


# Heading level 3 Primera Etapa

La primera etapa consiste en configurar los settings que se encuentran en el cdv dataSettings. En este archivo podemos modificar las siguientes variables: nombre de variables independientes, tamaño de sample, cantidad de variables independientes, nivel de randomization, valor mínimo de variables, valor máximo de variables, punto de activación de variables, dirección de punto de activación de variables, peso de variables, threshold de resultado.

El archivo consiste de nueve lineas:

	N1 Linea: Nombre de variables independientes separadas por comas.
	N2 Linea: Un único valor integro del tamaño del data set.
	N3 Linea: Un único valor integro de la cantidad de variables independientes.
	N4 Linea: Un unico valor integro del 0 al 9 que corresponde a el nivel de aleatorización implementado apra la creazione e data.
	N5 Linea: Una serie de íntegros separados con coma que corresponden al posible valor mínimo de cada variable correspondiente.
	N6 Linea: Una serie de íntegros separados con coma que corresponden al posible valor máximo de cada variable correspondiente.
	N7 Linea: Una serie de íntegros separados con coma que corresponden al posible valor de activación (“threshold”) para cada variable correspondiente. Es decir el valor que indique que cuanto una data de esta variable este por encima o por debajo de este valor generara una influencia en la variable dependiente.
	N8 Linea: Una serie de íntegros binarios separados con coma que corresponden a si el valor se activa por debajo del valor de activación (numero 0) o por encima (numero 1)
	N9 Linea: Una serie de íntegros del 1 al 10 separados con coma que corresponden al peso que cada variable influye sobre el resultado.
	N10 Linea: UN unico valor decimal del 0 al 1 que representa el threshold de activation para el resultado.

# Heading level 3 Segunda Etapa

Una vez que los settings ya estén configurados se corre el programa java el cual generara el dummy data.

# Heading level 3 Tercera Etapa

Ingresar al archivo csv dummyData para obtener los resultados del dummy data generado. 
