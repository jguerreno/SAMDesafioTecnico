import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
    private By contactoLocator = By.linkText("Contacto");
    private By trabajoLocator = By.linkText("Trabaje con nosotros");
    private By buscarLocator = By.xpath("//*[@id=\"wpcf7-f4996-p101-o1\"]/form/p[4]/input");


}
