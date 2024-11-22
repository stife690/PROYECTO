import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerUsersComponent } from './manager-users.component';

describe('ManagerUsersComponent', () => {
  let component: ManagerUsersComponent;
  let fixture: ComponentFixture<ManagerUsersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ManagerUsersComponent]
    });
    fixture = TestBed.createComponent(ManagerUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
