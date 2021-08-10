import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OverviewMolensComponent } from './overview-molens.component';

describe('OverviewMolensComponent', () => {
  let component: OverviewMolensComponent;
  let fixture: ComponentFixture<OverviewMolensComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OverviewMolensComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OverviewMolensComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
