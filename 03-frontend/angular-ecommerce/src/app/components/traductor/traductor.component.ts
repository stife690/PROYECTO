declare var google: any;

import { Component, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-traductor',
  templateUrl: './traductor.component.html',
  styleUrls: ['./traductor.component.css']
})
export class TraductorComponent implements AfterViewInit {

  ngAfterViewInit() {
    const checkGoogleTranslate = () => {
      if (typeof google !== 'undefined' && google.translate) {
        new google.translate.TranslateElement(
          { 
            pageLanguage: 'es',
            includedLanguages: 'es,en,pt', // Idiomas disponibles: Español, Inglés, Portugués
            layout: google.translate.TranslateElement.InlineLayout.SIMPLE, // Diseño simple
          },
          'google_translate_element'
        );

        // Prevenir la redirección al hacer clic en el enlace de idioma
        const interval = setInterval(() => {
          const languageLink = document.querySelector('.VIpgJd-ZVi9od-xl07Ob-lTBxed');
          if (languageLink) {
            languageLink.addEventListener('click', (event) => {
              event.preventDefault();  // Prevenir la redirección al hacer clic
            });
            clearInterval(interval); // Detener el intervalo después de agregar el listener
          }
          const loadingDiv = document.querySelector('.VIpgJd-ZVi9od-aZ2wEe-wOHMyf-ti6hGc') as HTMLElement;
          if (loadingDiv) {
                loadingDiv.style.display = 'none'; // Ocultar el div
            }
          
          // Eliminar el contenido de .indicator (el >)
          const indicatorElements = document.querySelectorAll('.indicator');
          if (indicatorElements.length) {
            indicatorElements.forEach((el) => {
              el.textContent = ''; // Eliminar el contenido de la clase .indicator (›)
            });
            
          }
        }, 500);

        

        // Aplicar estilos personalizados
        const combinedStyleInterval = setInterval(() => {
          // Estilo para .goog-te-gadget
          const gadgetElement = document.querySelector('.goog-te-gadget') as HTMLElement;
          if (gadgetElement) {
            gadgetElement.style.fontFamily = 'none';
            gadgetElement.style.fontSize = '0px';

            // Estilo para las imágenes dentro del widget
            const gadgetImages = document.querySelectorAll('.goog-te-gadget img') as NodeListOf<HTMLImageElement>;
            gadgetImages.forEach(img => {
              img.style.verticalAlign = 'middle';
              img.style.border = 'none';
              img.style.width = '0px'; // Ocultar las imágenes
            });
            
          }
          


          const gadgetSimple = document.querySelector('.goog-te-gadget-simple') as HTMLElement;
          if (gadgetSimple) {
            gadgetSimple.style.backgroundColor = '#ffffff';
            gadgetSimple.style.borderLeft = '1px solid #D5D5D5';
            gadgetSimple.style.borderTop = '1px solid #9B9B9B';
            gadgetSimple.style.borderBottom = '1px solid #9B9B9B';
            gadgetSimple.style.borderRight = '1px solid #9B9B9B';
            gadgetSimple.style.fontSize = '12pt';
            gadgetSimple.style.borderRadius = '15px';
            gadgetSimple.style.display = 'inline-block';
            gadgetSimple.style.paddingTop = '1px';
            gadgetSimple.style.paddingBottom = '2px';
            gadgetSimple.style.cursor = 'pointer';
          }
          
          

          // Estilo para enlaces y textos relacionados
          const targetElements = document.querySelectorAll(
            '.VIpgJd-ZVi9od-l4eHX-hSRGPd, .VIpgJd-ZVi9od-l4eHX-hSRGPd:link, .VIpgJd-ZVi9od-l4eHX-hSRGPd:visited, .VIpgJd-ZVi9od-l4eHX-hSRGPd:hover, .VIpgJd-ZVi9od-l4eHX-hSRGPd:active'
          ) as NodeListOf<HTMLElement>;

          if (targetElements.length > 0) {
            targetElements.forEach(element => {
              element.style.fontSize = '0px';
              element.style.fontWeight = 'bold';
              element.style.color = '#444';
              element.style.textDecoration = 'none';
            });

            const divElements = document.querySelectorAll('.VIpgJd-ZVi9od-vH1Gmf-ibnC6b div') as NodeListOf<HTMLElement>;
            divElements.forEach((element) => {
              element.style.color = '#000000';
              element.style.background = '#FFF';
            });
            

            // Ocultar el iframe específico de manera persistente usando MutationObserver
            const iframeElement = document.querySelector('.VIpgJd-ZVi9od-ORHb-OEVmcd') as HTMLElement;
            if (iframeElement) {
              iframeElement.style.visibility = 'hidden'; // Cambiar a display: none si lo prefieres
              iframeElement.style.display = 'none'; // Elimina completamente el iframe del layout
            }

            // Detener el intervalo una vez que todos los estilos se hayan aplicado
            clearInterval(combinedStyleInterval);
          }

          // Usamos MutationObserver para asegurarnos de que se prevenga la redirección siempre que cambien los enlaces
          const observer = new MutationObserver(() => {
            // Seleccionamos el enlace del menú para evitar la redirección
            const languageLink = document.querySelector('.VIpgJd-ZVi9od-xl07Ob-lTBxed');
            if (languageLink) {
              languageLink.addEventListener('click', (event) => {
                event.preventDefault();  // Prevenir la redirección al hacer clic
              });
            }
          });

          // Eliminar el contenido de .indicator (el >)
          const indicatorElements = document.querySelectorAll('.indicator');
          if (indicatorElements.length) {
            indicatorElements.forEach((el) => {
              el.textContent = ''; // Eliminar el contenido de la clase .indicator (›)
            });
            
          }

          // Observar cambios en el DOM dentro del enlace de idioma
          const menuElement = document.querySelector('.goog-te-menu2');
          if (menuElement) {
            observer.observe(menuElement, {
              childList: true, // Observar cambios en los hijos (elementos del menú)
              subtree: true     // Observar también los cambios en los sub-elementos
            });
          }



        }, 500);

        // Usar un MutationObserver para aplicar el estilo al iframe cada vez que se actualiza
        const iframeObserver = new MutationObserver(() => {
          const iframeElement = document.querySelector('.VIpgJd-ZVi9od-ORHb-OEVmcd') as HTMLElement;
          if (iframeElement) {
            iframeElement.style.visibility = 'hidden'; // Cambiar a display: none si lo prefieres
            iframeElement.style.display = 'none'; // Elimina completamente el iframe del layout
          }
        });

        // Eliminar el contenido de .indicator (el >)
        const indicatorElements = document.querySelectorAll('.indicator');
        if (indicatorElements.length) {
          indicatorElements.forEach((el) => {
            el.textContent = ''; // Eliminar el contenido de la clase .indicator (›)
          });
          
        }

        // Configura el observer para observar cambios en el DOM del widget
        iframeObserver.observe(document.body, {
          childList: true,
          subtree: true
        });

      } else {
        setTimeout(checkGoogleTranslate, 500);
      }
    };
    checkGoogleTranslate();
  }
}
