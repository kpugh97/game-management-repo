import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserRemoveGameComponent } from './user-remove-game.component';

describe('UserRemoveGameComponent', () => {
  let component: UserRemoveGameComponent;
  let fixture: ComponentFixture<UserRemoveGameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserRemoveGameComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserRemoveGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
