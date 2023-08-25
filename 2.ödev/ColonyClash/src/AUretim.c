/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 5 Mayıs 2023
*/
#include "AUretim.h"
#include <stdlib.h>

int auretim_uret(Uretim *uretim) {
    return 2 * (rand() % 5) + 1; // 1 ile 10 arası tek sayı üretir
}

Uretim *auretim_olustur() {
    return uretim_olustur(auretim_uret);
}
