import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CloseSesionComponent } from './close-sesion.component';

describe('CloseSesionComponent', () => {
  let component: CloseSesionComponent;
  let fixture: ComponentFixture<CloseSesionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CloseSesionComponent]
    });
    fixture = TestBed.createComponent(CloseSesionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
