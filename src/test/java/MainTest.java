import logic.Country;
import logic.Data;
import logic.Manufacturer;
import logic.Souvenir;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MainTest {

    List<Data> manufacturers;
    List<Data> souvenirs;

    @BeforeEach
    void setUp() {
         manufacturers = new Main().fillManufacturers();
         souvenirs = new Main().fillSouvenirs(manufacturers);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testPrintManufacturers() {
        manufacturers.stream().forEach(System.out::println);
    }

    @Test
    void testPrintsouvenirs() {
        souvenirs.stream().forEach(System.out::println);
    }
}