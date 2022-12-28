import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.List;

public class SAMAutomatedTest {
    private WebDriver driver;

    private String palabraBuscada = "devops";
    private int numeroArticulo = 4;

    // Localizador Busqueda
    private By lupaLocator = By.xpath("//*[@id=\"menu-principal\"]/li[6]/a");
    private By barraBusquedaLocator = By.id("ocean-search-form-1");
    // Localizador Paginas de Busqueda
    private By paginasBusquedaLocator = By.xpath("//a[@class=\"page-numbers\"]");
    private By pagina2Locator = By.linkText("2");
    // Localizador Articulo
    private By articuloLocator = By.xpath("//article[contains(@class,'post-')]");
    private By ingresoArticuloLocator = By.xpath("//article/div/div[2]/header/h2/a");
    // Localizador Busqueda
    private By TextLocator = By.xpath("//span[contains(string(),'Test Management')]");
    // Localizador Contacto
    private By contactoLocator = By.xpath("//a[@href=\"https://www.samsistemas.com.ar/contacto/\"][@class=\"menu-link\"]");
    private By trabajoLocator = By.linkText("Trabaje con nosotros");
    private By buscarLocator = By.xpath("//*[@id=\"wpcf7-f4996-p101-o1\"]/form/p[4]/input");
    // Localizar Footer
    private By footerLocator = By.xpath("//img[@src=\"https://samsistemas.com.ar/wp-content/uploads/2021/01/sam-icono-site-1.svg\"]");


    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.samsistemas.com.ar/");
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }


    @Test
    @DisplayName("Prueba automatizada Busqueda de Articulos")
    public void testSAMPage() {
        // Busqueda de Palabra
        driver.findElement(lupaLocator).click();

        WebElement barraBusqueda = driver.findElement(barraBusquedaLocator);
        barraBusqueda.clear();
        barraBusqueda.sendKeys(palabraBuscada);
        barraBusqueda.submit();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Assertions.assertEquals("Resultados de búsqueda para “devops” – SAM Sistemas", driver.getTitle());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        // Cantidad de Elementos arrojados por la busqueda
        List<WebElement> articulos = driver.findElements(articuloLocator);

        int cantidadPaginas = driver.findElements(paginasBusquedaLocator).size();
        for(int i=0 ; i<cantidadPaginas ; i++) {
            WebElement paginaSiguiente = driver.findElements(paginasBusquedaLocator).get(i);
            paginaSiguiente.click();
            articulos.addAll(driver.findElements(articuloLocator));
        }

        Assertions.assertEquals(articulos.size(), 17);
        System.out.println("Cantidad de Articulos: " + articulos.size());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        // Moverse a la página 2 del resultado de búsqueda y clickear sobre el cuarto artículo
        driver.findElement(pagina2Locator).click();
        driver.findElements(ingresoArticuloLocator).get(numeroArticulo-1).click();

        Assertions.assertEquals("Automatización de pruebas – SAM Sistemas", driver.getTitle());
        Assertions.assertTrue(driver.findElements(TextLocator).size() != 0);


        // Seleccionar -> Contacto -> “Trabaje con nosotros”
        Actions action = new Actions(driver);
        WebElement contacto = driver.findElement(contactoLocator);
        action.moveToElement(contacto).perform();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement trabajo = driver.findElement(trabajoLocator);
        action.moveToElement(trabajo).click().perform();
        Assertions.assertTrue(driver.findElement(buscarLocator).isEnabled());
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
        Assertions.assertTrue(driver.findElement(footerLocator).isDisplayed());
    }
}
