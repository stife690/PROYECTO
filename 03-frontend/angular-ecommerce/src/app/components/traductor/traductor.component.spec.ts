import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TraductorComponent } from './traductor.component';

describe('TraductorComponent', () => {
  let component: TraductorComponent;
  let fixture: ComponentFixture<TraductorComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TraductorComponent]
    });
    fixture = TestBed.createComponent(TraductorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
