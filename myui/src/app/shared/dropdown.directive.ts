import { Directive, HostBinding, ElementRef, Renderer2, HostListener } from '@angular/core';

@Directive({
  selector: '[appDropdown]'
})
export class DropdownDirective {

  @HostBinding('class.open') clicked = false;

  @HostListener('click') toggleOpen() {
    this.clicked = !this.clicked;
  }
}
