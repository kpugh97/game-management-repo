import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserEditGameComponent } from './user-edit-game.component';

describe('UserEditGameComponent', () => {
  let component: UserEditGameComponent;
  let fixture: ComponentFixture<UserEditGameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserEditGameComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserEditGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
