import { Component, OnInit, OnDestroy } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { LoopbackService } from './shared/loopback.service';

@Component( {
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css'],
    providers: [LoopbackService]
} )
export class AppComponent implements OnInit, OnDestroy {

    public response = false;

    constructor( private loopbackService: LoopbackService ) { }

    ngOnInit(): void {
        console.log( 'checkBackendStatus()' );
        this.loopbackService
            .testRestInterfaceWithPolling( 'hello!' )
            .subscribe(( response: Response ) => {
                this.response = response.statusText === 'OK';
            } );
    }

    ngOnDestroy(): void {
    }

}
