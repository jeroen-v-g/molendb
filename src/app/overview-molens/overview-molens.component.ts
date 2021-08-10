import {
  Component,
  OnInit,
  ViewChild,
  ViewChildren,
  QueryList,
} from '@angular/core';
import { Molen } from '../molen.model';
import { MolenService } from '../molen.service';
import { NgxMasonryComponent } from 'ngx-masonry';
import { LoginService } from '../login.service';
import { Observer } from 'rxjs';
import { MessageService } from 'primeng/api';
import { EditMolenComponent } from '../edit-molen/edit-molen.component';

@Component({
  selector: 'app-overview-molens',
  templateUrl: './overview-molens.component.html',
  styleUrls: ['./overview-molens.component.css'],
  providers: [MessageService],
})
export class OverviewMolensComponent implements OnInit {
  throttle = 0;
  distance = 2;
  page = 0;
  molens: Molen[] | any[] = [];
  editMolen: Molen | any;
  editMolenBuffer: Molen | any;
  title = 'molens';
  currentQueryString?: string;
  loggedIn: boolean = false;
  requestId: string = '';
  searchFieldText?: string;

  @ViewChild(NgxMasonryComponent) masonry!: NgxMasonryComponent;
  // @ViewChild(FileUpload) uploader!: FileUpload;
  // @ViewChildren("molenImage") molenImages!: QueryList<HTMLImageElement>;
  @ViewChild(EditMolenComponent) editMolenComponent: EditMolenComponent;

  constructor(
    private molenService: MolenService,
    private loginService: LoginService,
    private messageService: MessageService
  ) {
    const loginObserver: Observer<boolean> = {
      next: (value: boolean) => {
        this.loggedIn = value;
      },
      error() {},
      complete() {},
    };
    loginService.addSubscriber(loginObserver);
    loginService.sendUpdate();
  }

  ngOnInit(): void {
    this.page = 0;
    if (this.currentQueryString)
      this.molenService
        .getMolensSearch(this.page, this.currentQueryString)
        .subscribe((molens: Molen[]) => {
          this.molens = molens;
        });
    else
      this.molenService.getMolens(this.page).subscribe((molens: Molen[]) => {
        this.molens = molens;
      });
  }

  onDoSearch(): void {
    if (this.searchFieldText && this.searchFieldText.length >= 1)
      this.currentQueryString = this.searchFieldText;
    else this.currentQueryString = undefined;
    this.ngOnInit();
  }

  onDoClear(): void {
    this.searchFieldText = this.currentQueryString = undefined;
    this.ngOnInit();
  }

  onScroll(): void {
    if (this.currentQueryString)
      this.molenService
        .getMolensSearch(++this.page, this.currentQueryString)
        .subscribe((molens: Molen[]) => {
          this.molens.push(...molens);
        });
    else
      this.molenService.getMolens(++this.page).subscribe((molens: Molen[]) => {
        this.molens.push(...molens);
      });
  }

  onEdit(molen: Molen): void {
    for (let _molen of this.molens) {
      _molen.isBeingModified = false;
    }
    this.editMolen = molen;
    this.editMolenBuffer = Object.assign({}, this.editMolen);
    molen.isBeingModified = true;
    this.masonry.reloadItems();
    this.masonry.layout();
    this.requestId = this.molenService.guidGenerator();
  }

  onSave(molen: Molen): void {
    let molenForm = this.editMolenComponent.form;
    if (molenForm.valid) {
      this.molenService
        .updateMolen(molenForm.value, this.requestId)
        .subscribe((response) => {
          if (response.status == 'OK') {
            molen.isBeingModified = false;
            this.copyMolen(response.molen, molen);
            molen.postPhotoUrl = new Date().toISOString();
            this.messageService.add({key: 'tl', severity:'info', summary: 'Info', detail: 'Molen opgeslagen'});
            this.masonry.reloadItems();
            this.masonry.layout();
          } else {
            this.messageService.add({key: 'tl', severity:'error', summary: 'Waarschuwing', detail: 'Fout tijdens opslaan molen'});
          }
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

  onDelete(molen: Molen): void {
    this.molenService.deleteMolen(molen).subscribe((response) => {
      if (response.status == 'OK') {
        const index = this.molens.indexOf(molen, 0);
        if (index > -1) {
          this.molens.splice(index, 1);
        }
        this.masonry.reloadItems();
        this.masonry.layout();
      } else {
        this.messageService.add({key: 'tl', severity:'error', summary: 'Waarschuwing', detail: 'Fout tijdens verwijderen molen'});
      }
    });
  }

  onCancel(molen: Molen): void {
    this.copyMolen(this.editMolenBuffer, this.editMolen);
    this.copyMolen(this.editMolenBuffer, molen);
    this.masonry.reloadItems();
    this.masonry.layout();
  }

  /* onUpload(event: any) {
    for (let file of event.files) {
      this.molenService.uploadFoto(file, this.requestId).subscribe(response=>{
        if (response.status!='OK')
        alert('Fout tijdens verzenden foto');
        else
        {
          this.messageService.add({key: 'tl', severity:'info', summary: 'Info', detail: 'Afbeelding ontvangen'});
          this.uploader.clear();
        }
      })
    }
  }*/

  getMolenUrl(molen: Molen): string {
    let refreshValue = molen.postPhotoUrl ? molen.postPhotoUrl : 'default';
    return (
      '/fotos/molenfoto/' + molen.molenId + '?refreshValue=' + refreshValue
    );
  }

  private copyMolen(molenFrom: Molen, molenTo: Molen) {
    molenTo.adres = molenFrom.adres;
    molenTo.bouwjaar = molenFrom.bouwjaar;
    molenTo.eigenaar = molenFrom.eigenaar;
    molenTo.functie = molenFrom.functie;
    molenTo.isBeingModified = molenFrom.isBeingModified;
    molenTo.kenmerken = molenFrom.kenmerken;
    molenTo.molenaar = molenFrom.molenaar;
    molenTo.molenId = molenFrom.molenId;
    molenTo.naam = molenFrom.naam;
    molenTo.plaats = molenFrom.plaats;
    molenTo.type = molenFrom.type;
    molenTo.website = molenFrom.website;
    molenTo.fotoWidth = molenFrom.fotoWidth;
    molenTo.fotoHeight = molenFrom.fotoHeight;
  }
}
