/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 5 Mayıs 2023
*/
#include "BTaktik.h"
#include <stdlib.h>

int btaktik_savas(Taktik *taktik) {
    int rastgele_sayi = (rand() % 500) * 2 + 1; // Rastgele bir tek sayı üret, 1 ile 999 arasında
    return rastgele_sayi;
}

Taktik *btaktik_olustur() {
    return taktik_olustur(btaktik_savas);
}