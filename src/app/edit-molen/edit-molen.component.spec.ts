import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditMolenComponent } from './edit-molen.component';

describe('EditMolenComponent', () => {
  let component: EditMolenComponent;
  let fixture: ComponentFixture<EditMolenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditMolenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditMolenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
