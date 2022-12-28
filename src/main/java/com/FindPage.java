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
    private By trabajoLocator = By.linkText("Trabaje con nosotros");
    private By buscarLocator = By.xpath("//*[@id=\"wpcf7-f4996-p101-o1\"]/form/p[4]/input");
    // Localizar Footer
    private By footerLocator = By.xpath("//img[@src=\"https://samsistemas.com.ar/wp-content/uploads/2021/01/sam-icono-site-1.svg\"]");


    public FindPage(WebDriver driver) {
        super(driver);
    }

    /**
     *
     * @param palabra
     */
    public void buscarPalabra(String palabra) {
        click(lupaLocator);
        type(palabra, barraBusquedaLocator);
    }

    /**
     *
     * @return
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
     *
     * @param page
     * @param articleNumber
     */
    public void articlePage(int page, int articleNumber) {
        By pageLocator = By.linkText(String.valueOf(page));
        click(pageLocator);
        click(findElements(linkArticuloLocator).get(articleNumber-1));
    }

    /**
     *
     * @param word
     * @return
     */
    public Boolean wordExists(String word) {
        //By TextLocator = By.xpath("//*[contains(string(),'Test Management')]");
        By TextLocator = By.xpath("//span[contains(string(),'"+ word +"')]");
        return findElements(TextLocator).size() != 0;
    }

    /**
     *
     */
    public void trabajeConNosotros() {
        moveToElement(contactoLocator);
        waitSeconds(10);
        moveToElementAndClick(trabajoLocator);
    }
}
