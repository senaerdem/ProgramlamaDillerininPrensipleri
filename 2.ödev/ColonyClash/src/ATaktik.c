/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 5 Mayıs 2023
*/

#include "ATaktik.h"
#include <stdlib.h>

int ataktik_savas(Taktik *taktik) {
    int rastgele_sayi = (rand() % 501) * 2; // Rastgele bir çift sayı üret, 0 ile 1000 arasında
    return rastgele_sayi;
}

Taktik *ataktik_olustur() {
    return taktik_olustur(ataktik_savas);
}
