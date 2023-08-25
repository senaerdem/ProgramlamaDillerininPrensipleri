/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 23 Mayıs 2023
* <p>
*  Rastgele bir tek sayı üreten bir taktik sınıfı.
* </p>
*/


package colony;
import java.util.Random;

class BTaktik implements Taktik {
    public int savas() {
        Random rand = new Random();
        int rastgele_sayi = (rand.nextInt(500) * 2 + 1); // Rastgele bir tek sayı üret, 1 ile 999 arasında
        return rastgele_sayi;
    }
}