import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserInputBoxComponent } from './user-input-box.component';

describe('UserInputBoxComponent', () => {
  let component: UserInputBoxComponent;
  let fixture: ComponentFixture<UserInputBoxComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserInputBoxComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserInputBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
