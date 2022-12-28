import com.FindPage;
import org.junit.jupiter.api.*;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;


public class SAMAutomatedTest {
    private WebDriver driver;
    private FindPage page;

    private final String URL = "https://www.samsistemas.com.ar/";
    private final String devops = "devops";
    private final String testManagement = "Test Management";
    private final int numeroPagina = 2;
    private final int numeroArticulo = 4;
    private final int tiempoEspera = 10;

    // Localizar Footer
    private By footerLocator = By.xpath("//img[@src=\"https://samsistemas.com.ar/wp-content/uploads/2021/01/sam-icono-site-1.svg\"]");


    @BeforeEach
    public void setUp() {
        page = new FindPage(driver);
        driver = page.chromeDriverConnection();
        page.visit(URL);
    }

    @AfterEach
    public void tearDown() {
        page.quit();
    }


    @Test
    @DisplayName("Prueba automatizada Busqueda de Articulos")
    public void testSAMPage() {
        // Busqueda de Palabra
        page.buscarPalabra(devops);
        page.waitSeconds(tiempoEspera);
        Assertions.assertEquals("Resultados de búsqueda para “devops” – SAM Sistemas", page.getTitle());

        // Cantidad de Elementos arrojados por la busqueda
        int cantidadElementoBusqueda = page.cantidadElementoBusqueda();
        Assertions.assertEquals(cantidadElementoBusqueda, 17);
        System.out.println("Cantidad de Articulos: " + cantidadElementoBusqueda);
        page.waitSeconds(tiempoEspera);

        // Moverse a la página 2 del resultado de búsqueda y clickear sobre el cuarto artículo
        page.articlePage(numeroPagina, numeroArticulo);
        Assertions.assertEquals("Automatización de pruebas – SAM Sistemas", page.getTitle());
        Assertions.assertTrue(page.wordExists(testManagement));

        // Seleccionar -> Contacto -> “Trabaje con nosotros”
        page.trabajeConNosotros();
        Assertions.assertTrue(page.botonEnvioFormTrabajoDisponible());

        // Voy al footer
        page.moveDown();
        Assertions.assertTrue(page.isDisplayed(footerLocator));
    }
}
