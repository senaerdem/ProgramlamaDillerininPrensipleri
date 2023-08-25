/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 5 Mayıs 2023
*/
#include "BUretim.h"
#include <stdlib.h>

int buretim_uret(Uretim *uretim) {
    return 2 * (rand() % 5) + 2; // 1 ile 10 arası çift sayı üretir
}

Uretim *buretim_olustur() {
    return uretim_olustur(buretim_uret);
}