import { FormControl, ValidationErrors } from "@angular/forms";

export class Luv2ShopValidators {

    // VALIDACIÓN DE ESPACIOS EN BLANCO
    static notOnlyWhitespace(control: FormControl): ValidationErrors | null {

        // verificamos si la cadena solo contiene espacios en blanco
        if ((control.value != null) && (control.value.trim().length === 0)) {

            // inválido, retornamos este objeto de error
            return { notOnlyWhitespace: true };
        } else {
            // válido, retornamos null
            return null;
        }
    }
}
