import { Component, OnInit, ViewChild } from '@angular/core';
import { MolenService } from '../molen.service';
import { LoginService } from '../login.service';
import { Molen } from '../molen.model';
import { MessageService } from 'primeng/api';
import { EditMolenComponent } from '../edit-molen/edit-molen.component';


@Component({
  selector: 'app-add-molen',
  templateUrl: './add-molen.component.html',
  styleUrls: ['./add-molen.component.css'],
  providers: [MessageService],
})
export class AddMolenComponent implements OnInit {

  @ViewChild(EditMolenComponent) editMolen: EditMolenComponent;
  requestId: string;

  molen: Molen = {
    naam: '',
    adres: '',
    eigenaar: '',
    functie: '',
    kenmerken: '',
    molenaar: '',
    plaats: '',
    type: '',
    website: '',
  };

  constructor(
    private molenService: MolenService,
    private loginService: LoginService,
    private messageService: MessageService
  ) {
    this.requestId = molenService.guidGenerator();
  }

 ngOnInit(){

 }

  onAddMolen() {
    let molenForm = this.editMolen.form
    if (molenForm.valid)
    {
      let molen=molenForm.value;
      this.molenService.addMolen(molen,this.requestId).subscribe((value) => {
        this.messageService.add({key: 'tl', severity:'info', summary: 'Info', detail: 'Molen opgeslagen'});
        molenForm.reset();
      },(error)=>{
        if (error.url.endsWith('login'))
        {
          molen.isBeingModified = false;
          this.messageService.add({key: 'tl', severity:'error', summary: 'Waarschuwing', detail: 'Sessie verlopen'});
          this.loginService.logOut()
        }
      });
    }
    else
      this.messageService.add({key: 'tl', severity:'error', summary: 'Waarschuwing', detail: 'Ongeldige waardes'});
  }


}
