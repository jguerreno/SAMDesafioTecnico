package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


/**
 * Clase encargada de realizar busquedas
 */
public class FindPage extends PageObjectModel {
    // Localizador Busqueda
    private By lupaLocator = By.xpath("//*[@id=\"menu-principal\"]/li[6]/a");
    private By barraBusquedaLocator = By.id("ocean-search-form-1");
    // Localizador Paginas de Busqueda
    private By paginasBusquedaLocator = By.xpath("//a[@class=\"page-numbers\"]");
    // Localizador Articulo
    private By articuloLocator = By.xpath("//article[contains(@class,'post-')]");
    private By linkArticuloLocator = By.xpath("//article/div/div[2]/header/h2/a");
    // Localizador Contacto
    private By contactoLocator = By.xpath("//a[@href=\"https://www.samsistemas.com.ar/contacto/\"][@class=\"menu-link\"]");
    private By trabajoLocator = By.xpath("//*[@id=\"menu-item-108\"]/a");
    private By buscarLocator = By.xpath("//*[@id=\"wpcf7-f4996-p101-o1\"]/form/p[4]/input");
    // Localizar Footer
    private By footerLocator = By.xpath("//img[@src=\"https://samsistemas.com.ar/wp-content/uploads/2021/01/sam-icono-site-1.svg\"]");


    public FindPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Realiza la busqueda de un string
     * @param palabra string a buscar
     */
    public void buscarPalabra(String palabra) {
        click(lupaLocator);
        type(palabra, barraBusquedaLocator);
    }

    /**
     * Cuenta la cantidad de elementos que tuvo una busqueda
     * @return retorna la cantidad de elementos de una busqueda
     */
    public int cantidadElementoBusqueda() {
        List<WebElement> articulos = findElements(articuloLocator);

        int cantidadPaginas = findElements(paginasBusquedaLocator).size();
        for(int i=0 ; i<cantidadPaginas ; i++) {
            WebElement paginaSiguiente = findElements(paginasBusquedaLocator).get(i);
            click(paginaSiguiente);
            articulos.addAll(findElements(articuloLocator));
        }

        return articulos.size();
    }

    /**
     * Se dirige al un articulo especifico de una pagina
     * @param page numero de pagina donde se encuentra el articulo
     * @param articleNumber numero de articulo relativo a la pagina
     */
    public void articlePage(int page, int articleNumber) {
        By pageLocator = By.linkText(String.valueOf(page));
        click(pageLocator);
        click(findElements(linkArticuloLocator).get(articleNumber-1));
    }

    /**
     * Checkea si existe una palabra
     * @param word palabra a buscar
     * @return devuelve true si la palabra existe, de otro modo false
     */
    public Boolean wordExists(String word) {
        //By TextLocator = By.xpath("//*[contains(string(),'Test Management')]");
        By TextLocator = By.xpath("//span[contains(string(),'"+ word +"')]");
        return findElements(TextLocator).size() != 0;
    }

    /**
     *  Se mueve al elemento trabaje con nosotros del header
     */
    public void trabajeConNosotros() {
        moveToElement(contactoLocator);
        waitSeconds(10);
        moveToElementAndClick(trabajoLocator);
    }

    /**
     * Checkea si el boton de envio del form de trabaje con nosotros esta habilitado
     * @return true si esta habilitado, false si no lo esta
     */
    public Boolean botonEnvioFormTrabajoDisponible() {
        return findElement(buscarLocator).isEnabled();
    }
}
