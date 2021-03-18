import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAddGameComponent } from './user-add-game.component';

describe('UserAddGameComponent', () => {
  let component: UserAddGameComponent;
  let fixture: ComponentFixture<UserAddGameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserAddGameComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserAddGameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
