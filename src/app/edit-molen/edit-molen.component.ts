import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { MolenService } from '../molen.service';
import { Molen } from '../molen.model';
import { MessageService } from 'primeng/api';
import { FileUpload } from 'primeng/fileupload';

@Component({
  selector: 'app-edit-molen',
  templateUrl: './edit-molen.component.html',
  styleUrls: ['./edit-molen.component.css']
})
export class EditMolenComponent implements OnInit {

  @Input() requestId: string;

  @Input() molen: Molen;

  @Output() molenForm: FormGroup;

  constructor(private molenService: MolenService,
    private messageService: MessageService)
  {}

  ngOnInit(): void {
    this.molenForm = new FormGroup({
      molenId: new FormControl(this.molen.molenId),
      naam: new FormControl(this.molen.naam, [
        Validators.required,
        Validators.minLength(4),
      ]),
      plaats: new FormControl(this.molen.plaats, [
        Validators.required,
        Validators.minLength(4),
      ]),
      type: new FormControl(this.molen.type, Validators.required),
      kenmerken: new FormControl(this.molen.kenmerken, Validators.required),
      functie: new FormControl(this.molen.functie),
      bouwjaar: new FormControl(this.molen.bouwjaar),
      adres: new FormControl(this.molen.adres),
      molenaar: new FormControl(this.molen.molenaar),
      eigenaar: new FormControl(this.molen.eigenaar),
      website:  new FormControl(this.molen.website),
    });
  }

  get naam() { return this.molenForm.get('naam'); }
  get plaats() { return this.molenForm.get('plaats'); }
  get type() { return this.molenForm.get('type'); }
  get kenmerken() { return this.molenForm.get('kenmerken'); }
  get functie() { return this.molenForm.get('functie'); }
  get bouwjaar() { return this.molenForm.get('bouwjaar'); }
  get adres() { return this.molenForm.get('adres'); }
  get molenaar() { return this.molenForm.get('miller'); }
  get eigenaar() { return this.molenForm.get('owner'); }
  get website() { return this.molenForm.get('website'); }

  onUpload(event: any, uploadForm: FileUpload) {
    for (let file of event.files) {
      this.molenService.uploadFoto(file,this.requestId).subscribe(response=>{
        if (response.status=='OK')
        {
          this.messageService.add({key: 'tl', severity:'info', summary: 'Info', detail: 'Afbeelding ontvangen'});
          uploadForm.clear();
        }
        else
        this.messageService.add({key: 'tl', severity:'error', summary: 'Waarschuwing', detail: 'Fout tijdens verzenden afbeelding'});
      })
    }
  }

  get form() { return this.molenForm; }


}
