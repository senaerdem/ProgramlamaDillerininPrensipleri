/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 23 Mayıs 2023
* <p>
*  Rastgele bir tek sayı üreten bir üretim sınıfı
* </p>
*/
package colony;
import java.util.Random;

//AUretim sınıfı, Uretim arayüzünü uygular
class AUretim implements Uretim {
    public int uret() {
        Random rand = new Random();
        return 2 * (rand.nextInt(5)) + 1; // 1 ile 10 arası tek sayı üretir
    }
}
