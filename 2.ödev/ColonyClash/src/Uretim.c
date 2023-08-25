/**
*
* @author Sena Nur Erdem     sena.erdem1@ogr.sakarya.edu.tr
* @since 5 Mayıs 2023
*/

#include "Uretim.h"
#include <stdlib.h>

Uretim *uretim_olustur(int (*uret)(Uretim *uretim)) {
    Uretim *uretim = (Uretim *)malloc(sizeof(Uretim));
    uretim->uret = uret;
    return uretim;
}
