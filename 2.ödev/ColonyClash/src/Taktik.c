/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 5 Mayıs 2023
*/

#include "Taktik.h"
#include <stdlib.h>

Taktik *taktik_olustur(int (*savas)(Taktik *taktik)) {
    Taktik *taktik = (Taktik *)malloc(sizeof(Taktik));
    taktik->savas = savas;
    return taktik;
}
