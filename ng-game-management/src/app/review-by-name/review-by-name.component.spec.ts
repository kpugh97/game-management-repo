import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReviewByNameComponent } from './review-by-name.component';

describe('ReviewByNameComponent', () => {
  let component: ReviewByNameComponent;
  let fixture: ComponentFixture<ReviewByNameComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReviewByNameComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReviewByNameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
