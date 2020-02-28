import { Component } from "@angular/core";

@Component({
selector:'main',
templateUrl:'./main.component.html',
styleUrls:['./main.component.scss']
})
export class MainComponent {
    app_title="Scratchpad";
    opened=true;
    toggle(){
           this.opened = !this.opened;
    }
}