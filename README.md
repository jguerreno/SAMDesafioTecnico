# SAMDesafioTecnico
Desafío técnico SAM Sistemas utilizando Selenium. 


## Proceso de Desarrollo
Primero se realizo una primera version que cumpla los requerimentos establecidos (v1.0), en esta todo el codigo se encontra en SAMAutomatedTest.

Despues se mejoro la propuesta a traves del patron POM (v2.0). De esta forma la propuesta se volvio mas mantenible y extensible. Ya que se abstrajo los comandos de Selenium, asi como tambien las acciones.

Basicamente la idea consta de tres capas. La capa base, que nos aislara de los comandos de Selenium. De esta forma si algun comando de Selenium cambia solo se tocara esta capa.
Despues tenemos la capa Page que hereda de la clase base y contiene las acciones de la pagina, asi como tambien los localizadores de los elementos.
Por ultimo se encuentra la capa test que sera la que usara los Page Objects.

## Despliegue
1. Clona este proyecto
2. Instala las dependencias
3. Corre los test de SAMAutomatedTest

## Especificacines
- Java 1.8.0_321
- Apache Maven 3.8.5
- Chrome Driver 108.0.5359.125
