import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetGameEditComponent } from './get-game-edit.component';

describe('GetGameEditComponent', () => {
  let component: GetGameEditComponent;
  let fixture: ComponentFixture<GetGameEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetGameEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetGameEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
