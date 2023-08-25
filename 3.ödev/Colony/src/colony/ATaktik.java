/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 23 Mayıs 2023
* <p>
*  Rastgele bir çift sayı üreten bir taktik sınıfı.
* </p>
*/

package colony;
import java.util.Random;

//ATaktik sınıfı, Taktik arayüzünü uygular
class ATaktik implements Taktik {
    public int savas() {
        Random rand = new Random();
        int rastgele_sayi = (rand.nextInt(501)) * 2; // Rastgele bir çift sayı üret, 0 ile 1000 arasında
        return rastgele_sayi;
    }
}
