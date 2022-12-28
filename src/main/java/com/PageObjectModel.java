package com;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;


public class PageObjectModel {
    private WebDriver driver;
    private Actions action;

    public PageObjectModel(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Metodo para la coneccion con chrome
     * @return Instancia del navegador Chrome
     */
    public WebDriver chromeDriverConnection() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        action = new Actions(driver);
        return driver;
    }

    /**
     * Busca un webelement a traves de un locator
     * @param locator locator asi el webelement unico
     * @return devuelve el WebElement
     */
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Busca los webelements que concuerdan con el locator
     * @param locator
     * @return devuelve los webelements
     */
    public List<WebElement> findElements(By locator) {
        return driver.findElements(locator);
    }

    /**
     *
     * @return devuelve el titulo de la ventana
     */
    public String getTitle(){
        return driver.getTitle();
    }

    /**
     * Clickea un elemento a travez de un locator
     * @param locator locator que hace referencia al elemento unico
     */
    public void click(By locator) {
        driver.findElement(locator).click();
    }

    /**
     * Clickea un webelement
     * @param webElement
     */
    public void click(WebElement webElement) {
        webElement.click();
    }

    /**
     * Checkea que un elemento este habilitado
     * @param locator locator asi el elemento unico
     * @return devuelve true si el elemento esta habilitado, sino devuelve false
     */
    public Boolean isEnable(By locator) {
        return driver.findElement(locator).isEnabled();
    }

    /**
     * Realiza una peticion GET a la URL
     * @param URL
     */
    public void visit(String URL) {
        driver.get(URL);
    }

    /**
     * Espera n segundos
     * @param seconds cantidad de tiempo a esperar
     */
    public void waitSeconds(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }

    /**
     * Realiza una busqueda a travez de una barra de busqueda
     * @param inputText string a buscar
     * @param locator location de la barra
     */
    public void type(String inputText, By locator) {
        WebElement barraBusqueda = driver.findElement(locator);
        barraBusqueda.clear();
        barraBusqueda.sendKeys(inputText);
        barraBusqueda.submit();
    }

    /**
     * Realiza una action para moverse a un elemento especifico
     * @param locator
     */
    public void moveToElement(By locator) {
        WebElement contacto = findElement(locator);
        action.moveToElement(contacto).perform();
    }

    /**
     * Realiza una action para moverse a un elemento especifico y clickearlo
     * @param locator
     */
    public void moveToElementAndClick(By locator) {
        WebElement contacto = findElement(locator);
        action.moveToElement(contacto).click().perform();
    }

    /**
     * Se mueve al fondo de la pagina
     */
    public void moveDown() {
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    /**
     * Checkea que un elemento este visible
     * @param locator
     * @return True si el elemento esta visible, False si no lo esta
     */
    public Boolean isDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

    public void quit() {
        driver.quit();
    }
}
