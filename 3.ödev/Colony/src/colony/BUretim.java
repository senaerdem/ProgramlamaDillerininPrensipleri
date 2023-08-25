/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 23 Mayıs 2023
* <p>
*  Rastgele bir çift sayı üreten bir üretim sınıfı
* </p>
*/
package colony;
import java.util.Random;

//BUretim sınıfı, Uretim arayüzünü uygular
class BUretim implements Uretim {
    public int uret() {
        Random rand = new Random();
        return 2 * (rand.nextInt(5)) + 2; // 1 ile 10 arası çift sayı üretir
    }
}