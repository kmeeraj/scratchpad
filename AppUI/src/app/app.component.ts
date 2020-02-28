import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MatIconRegistry } from '@angular/material/icon';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'AppUI';
  
  constructor(
    private iconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer,
  ) {

  this.iconRegistry.addSvgIconInNamespace('assets', 'teradata',
  this.domSanitizer.bypassSecurityTrustResourceUrl('assets/icons/teradata.svg'));
  this.iconRegistry.addSvgIconInNamespace('assets', 'teradata-dark',
  this.domSanitizer.bypassSecurityTrustResourceUrl('assets/icons/teradata-dark.svg'));
  }
}
