import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IconManagerUsersComponent } from './icon-manager-users.component';

describe('IconManagerUsersComponent', () => {
  let component: IconManagerUsersComponent;
  let fixture: ComponentFixture<IconManagerUsersComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [IconManagerUsersComponent]
    });
    fixture = TestBed.createComponent(IconManagerUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
