import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddMolenComponent } from './add-molen.component';

describe('AddMolenComponent', () => {
  let component: AddMolenComponent;
  let fixture: ComponentFixture<AddMolenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddMolenComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddMolenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
